package fr.volax.valkyatournoi.tournoi;

import fr.volax.valkyatournoi.ValkyaTournoi;
import fr.volax.valkyatournoi.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TournoiCommand implements CommandExecutor {
    public TournoiCommand(String string) {
        ValkyaTournoi.getInstance().getCommand(string).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0){
            helpMessage(sender);
            return false;
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("tp")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cVous devez être un joueur pour executer cette commande !");
                    return false;
                }

                Player player = (Player) sender;
                player.teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
            }else if(args[0].equalsIgnoreCase("join")){
                if(!(sender instanceof Player)) {
                    sender.sendMessage("§cVous devez être un joueur pour executer cette commande !");
                    return false;
                }
                Player player = (Player)sender;

                if(ValkyaTournoi.getInstance().getTournoiPlayersManager().doesPlayerExist(player)){
                    MessageUtil.senderMessage(player, "§eVous êtes déjà dans le tournoi !");
                    return false;
                }

                if(!TournoiState.isState(TournoiState.INVITATION)){
                   MessageUtil.senderMessage(player, "§eVous ne pouvez pas rejoindre le tournoi !");
                   return false;
                }
                ValkyaTournoi.getInstance().getTournoiPlayersManager().newTournoiPlayer(player);
                TournoiPlayer tournoiPlayer = ValkyaTournoi.getInstance().getTournoiPlayersManager().getTournoiPlayer(player);
                MessageUtil.senderMessage(sender, "§eVous venez de rejoindre le tournoi, vous êtes le §6" + tournoiPlayer.getIdCurrentPlace() + "§eeme participant !");
            }else if(args[0].equalsIgnoreCase("quit")){
                if(!(sender instanceof Player))
                    sender.sendMessage("§cVous devez être un joueur pour executer cette commande !");

                Player player = (Player)sender;

                if(!ValkyaTournoi.getInstance().getTournoiPlayersManager().doesPlayerExist(player)){
                    MessageUtil.senderMessage(player, "§eVous n'êtes pas dans le tournoi !");
                    return false;
                }

                TournoiPlayer tournoiPlayer = ValkyaTournoi.getInstance().getTournoiPlayersManager().getTournoiPlayer(player);

                if(tournoiPlayer.isOnFight()){
                    MessageUtil.senderMessage(sender, "§eVous ne pouvez quitter le tournoi en fight !");
                }
                ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(tournoiPlayer);
                MessageUtil.senderMessage(sender, "§eVous venez de quitter le tournoi !");

            }else if(args[0].equalsIgnoreCase("start")){
                if(!TournoiState.isState(TournoiState.NONE)){
                    MessageUtil.senderMessage(sender, "§eErreur dans l'execution de cette commande §cTournoi déjà start");
                    return false;
                }
                if(!sender.isOp()) return false;
                TournoiState.setState(TournoiState.INVITATION);
                ValkyaTournoi.getInstance().getTournoi().startTournoi();
                MessageUtil.broadcastMessage("§eLe Tournoi PvP vient de commencer ! /tournoi join pour le rejoindre !");
                MessageUtil.debugMesssage("Tournoi started");
            }else if(args[0].equalsIgnoreCase("stop")){
                if(!ValkyaTournoi.getInstance().getTournoi().doesTournoiIsStart()){
                    MessageUtil.senderMessage(sender, "§eErreur dans l'execution de cette commande §cTournoi non start");
                    return false;
                }
                if(!sender.isOp()) return false;
                ValkyaTournoi.getInstance().getTournoi().stopTournoi();
                MessageUtil.debugMesssage("Tournoi stoped");
            }else{
                helpMessage(sender);
                return false;
            }
        }

        return false;
    }

    private void helpMessage(CommandSender sender){
        sender.sendMessage(ValkyaTournoi.getPrefix() + " §e/tournoi <start|stop>");
        sender.sendMessage(ValkyaTournoi.getPrefix() + " §e/tournoi <join|quit>");
        sender.sendMessage(ValkyaTournoi.getPrefix() + " §e/tournoi tp");
        sender.sendMessage(ValkyaTournoi.getPrefix() + " §e/tournoi kick <Player>");
    }
}

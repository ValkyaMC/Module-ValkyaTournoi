package fr.volax.valkyatournoi.tournoi;

import fr.volax.valkyatournoi.ValkyaTournoi;
import fr.volax.valkyatournoi.util.MessageUtil;
import fr.volax.volaxapi.tool.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.List;

public class TournoiGame {
    public List<TournoiPlayer> players;
    public int task, timer;

    public TournoiGame(List<TournoiPlayer> players) {
        this.players = players;
    }

    public void startFight() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(ValkyaTournoi.getInstance(), new Runnable() {
            @Override
            public void run() {
                timer++;
                TournoiPlayer player0 = players.get(0);
                TournoiPlayer player1 = players.get(1);

                if(timer == 1){
                    Bukkit.broadcastMessage(ValkyaTournoi.getPrefix() + " §eDébut du match entre §6" + players.get(0).getPlayer().getName() + " §eet §6" + players.get(1).getPlayer().getName() + " !");
                    Bukkit.broadcastMessage(ValkyaTournoi.getPrefix() + " §eVous avez 5 minutes pour vous tuer sinon vous êtes disqualifiés ! Bon fight !");

                    TournoiInventory inv1 = new TournoiInventory(player0.getPlayer());
                    inv1.save();
                    ValkyaTournoi.getInstance().mode.put(player0.getPlayer(), inv1);

                    TournoiInventory inv2 = new TournoiInventory(player1.getPlayer());
                    inv2.save();
                    ValkyaTournoi.getInstance().mode.put(player1.getPlayer(), inv2);

                    givePvPStuff(player0.getPlayer());
                    givePvPStuff(player1.getPlayer());

                    player0.setOnFight(true);
                    player1.setOnFight(true);

                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1125, 7, -851));
                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1125, 7, -791));

                    player0.getPlayer().setGameMode(GameMode.ADVENTURE);
                    player1.getPlayer().setGameMode(GameMode.ADVENTURE);
                }

                if (timer == 300) {
                    Bukkit.broadcastMessage(ValkyaTournoi.getPrefix() + " §eVous avez 5 minutes pour vous tuer sinon vous êtes disqualifiés ! Bon fight !");
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(players.get(0));
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(players.get(1));
                }

                if(player0.isDead()){
                    player1.setWaiting(false);
                    player1.setOnFight(false);
                    MessageUtil.broadcastMessage("§eFin du Match ! Gagnant §6" + players.get(1).getPlayer().getName() + " §ePerdant §6" + players.get(0).getPlayer().getName() + " §e!");

                    reGiveInventory(player0.getPlayer());
                    reGiveInventory(player1.getPlayer());
                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(player0);
                    ValkyaTournoi.getInstance().getTournoi().setTournoiGame(null);
                    Bukkit.getScheduler().cancelTask(task);
                    return;
                }

                if(player1.isDead()){
                    player0.setWaiting(false);
                    player0.setOnFight(false);
                    MessageUtil.broadcastMessage("§eFin du Match ! Gagnant §6" + players.get(0).getPlayer().getName() + " §ePerdant §6" + players.get(1).getPlayer().getName() + " §e!");

                    reGiveInventory(player0.getPlayer());
                    reGiveInventory(player1.getPlayer());
                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(player1);
                    ValkyaTournoi.getInstance().getTournoi().setTournoiGame(null);
                    Bukkit.getScheduler().cancelTask(task);
                    return;
                }

                if(Bukkit.getPlayer(player0.getPlayer().getName()) == null){
                    player1.setWaiting(false);
                    player1.setOnFight(false);
                    MessageUtil.broadcastMessage("§eFin du Match ! Gagnant §6" + players.get(1).getPlayer().getName() + " §ePerdant §6" + players.get(0).getPlayer().getName() + " §e!");
                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    reGiveInventory(player0.getPlayer());
                    reGiveInventory(player1.getPlayer());

                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -400, 200, 601));
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(player0);
                    ValkyaTournoi.getInstance().getTournoi().setTournoiGame(null);
                    Bukkit.getScheduler().cancelTask(task);
                    return;
                }

                if(Bukkit.getPlayer(player1.getPlayer().getName()) == null){
                    player1.setWaiting(false);
                    player1.setOnFight(false);
                    MessageUtil.broadcastMessage("§eFin du Match ! Gagnant §6" + players.get(1).getPlayer().getName() + " §ePerdant §6" + players.get(0).getPlayer().getName() + " §e!");
                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    player1.getPlayer().teleport(new Location(Bukkit.getWorld("event"), 1103, 18, -821));
                    reGiveInventory(player0.getPlayer());
                    reGiveInventory(player1.getPlayer());
                    player0.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -400, 200, 601));
                    ValkyaTournoi.getInstance().getTournoiPlayersManager().remove(player1);
                    ValkyaTournoi.getInstance().getTournoi().setTournoiGame(null);
                    Bukkit.getScheduler().cancelTask(task);
                    return;
                }
            }
        }, 20, 20);
    }

    public void reGiveInventory(Player player){
        ValkyaTournoi.getInstance().mode.get(player).give();
    }

    public static void givePvPStuff(Player player){
        player.getInventory().clear();
        ItemStack casque = new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack plastron = new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack leggings = new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack boots = new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack();
        ItemStack apples = new ItemBuilder(Material.GOLDEN_APPLE, 3).toItemStack();
        Potion potionSpeed = new Potion(PotionType.SPEED, 2);
        Potion potionForce = new Potion(PotionType.STRENGTH, 1);

        player.getInventory().setHelmet(casque);
        player.getInventory().setChestplate(plastron);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, apples);
        player.getInventory().setItem(2, potionSpeed.toItemStack(1));
        player.getInventory().setItem(3, potionForce.toItemStack(1));

        for(int i = 0; i < 32; i++){
            Potion p = new Potion(PotionType.INSTANT_HEAL, 2);
            p.setSplash(true);
            ItemStack item = p.toItemStack(1);
            player.getInventory().addItem(item);
        }
    }
}

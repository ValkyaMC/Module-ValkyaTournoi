package fr.volax.valkyatournoi;

import fr.volax.valkyatournoi.tournoi.TournoiGame;
import fr.volax.valkyatournoi.tournoi.TournoiPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(!ValkyaTournoi.getInstance().getTournoiPlayersManager().doesPlayerExist(player)) return;
        TournoiPlayer tournoiPlayer = ValkyaTournoi.getInstance().getTournoiPlayersManager().getTournoiPlayer(player);
        if(tournoiPlayer.isOnFight()) {
            tournoiPlayer.setDead(true);
            event.setKeepInventory(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(!ValkyaTournoi.getInstance().getTournoiPlayersManager().doesPlayerExist(player)) return;
        TournoiPlayer tournoiPlayer = ValkyaTournoi.getInstance().getTournoiPlayersManager().getTournoiPlayer(player);
        if(tournoiPlayer.isOnFight()) {
            tournoiPlayer.setDead(true);
            TournoiGame.givePvPStuff(tournoiPlayer.getPlayer());
        }
    }
}

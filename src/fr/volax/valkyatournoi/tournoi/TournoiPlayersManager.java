package fr.volax.valkyatournoi.tournoi;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TournoiPlayersManager {
    private List<TournoiPlayer> players;

    public TournoiPlayersManager() {
        players = Collections.synchronizedList(new ArrayList<>());
    }

    public boolean doesPlayerExist(Player player){
        return getTournoiPlayer(player) != null;
    }

    public synchronized TournoiPlayer newTournoiPlayer(Player player){
        TournoiPlayer newPlayer = new TournoiPlayer(player);
        getPlayersList().add(newPlayer);
        return newPlayer;
    }

    public synchronized List<TournoiPlayer> getPlayersList(){
        return players;
    }

    public synchronized List<TournoiPlayer> getWaitingPlayersList(){
        List<TournoiPlayer> waitingPlayers = new ArrayList<>();
        for(TournoiPlayer tournoiPlayer : players){
            if(tournoiPlayer.isWaiting())
                waitingPlayers.add(tournoiPlayer);
        }
        return waitingPlayers;
    }

    public synchronized List<TournoiPlayer> getNonDeadPlayers(){
        List<TournoiPlayer> nonDeadPlayers = new ArrayList<>();
        for(TournoiPlayer tournoiPlayer : players){
            if(!tournoiPlayer.isDead())
                nonDeadPlayers.add(tournoiPlayer);
        }
        return nonDeadPlayers;
    }

    public TournoiPlayer getTournoiPlayer(Player player){
        for(TournoiPlayer tournoiPlayer : getPlayersList()){
            if(tournoiPlayer.getPlayer().equals(player))
                return tournoiPlayer;
        }
        return null;
    }

    public void remove(TournoiPlayer uhcPlayer){
        getPlayersList().remove(uhcPlayer);
    }
}

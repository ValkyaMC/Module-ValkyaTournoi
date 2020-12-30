package fr.volax.valkyatournoi.tournoi;

import fr.volax.valkyatournoi.ValkyaTournoi;
import org.bukkit.entity.Player;

public class TournoiPlayer {
    private Player player;
    private int idCurrentPlace, idDeathPlace;
    private boolean isDead, isOnFight, isWaiting;

    public TournoiPlayer(Player player) {
        this.player = player;
        this.isDead = false;
        this.isOnFight = false;
        this.isWaiting = true;
        this.idCurrentPlace = ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() + 1;
        this.idDeathPlace = 0;
    }

    public int getIdCurrentPlace() {
        return idCurrentPlace;
    }

    public void setIdCurrentPlace(int idCurrentPlace) {
        this.idCurrentPlace = idCurrentPlace;
    }

    public int getIdDeathPlace() {
        return idDeathPlace;
    }

    public void setIdDeathPlace(int idDeathPlace) {
        this.idDeathPlace = idDeathPlace;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isOnFight() {
        return isOnFight;
    }

    public void setOnFight(boolean onFight) {
        isOnFight = onFight;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public Player getPlayer() {
        return player;
    }
}

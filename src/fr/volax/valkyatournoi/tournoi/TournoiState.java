package fr.volax.valkyatournoi.tournoi;

import java.util.HashMap;

public enum TournoiState {
    NONE("none", -1, 0),
    INVITATION("invitation", 0, 128),
    POULE("poule", 1, 128),
    TRENTE_DEUX_EME("32ème", 2, 64),
    SEIZE_EME("16ème", 3, 32),
    HUIT_EME("8ème", 4, 16),
    QUART("Quart de Final", 5, 8),
    DEMI("Demi Final", 6, 4),
    FINAL("Final", 7, 2);

    public String displayName;
    public int id, maxPlayers;
    private static TournoiState currentState;

    private static HashMap<Integer, TournoiState> ID_GAME = new HashMap();

    TournoiState(String displayName, int id, int maxPlayers) {
        this.displayName = displayName;
        this.id = id;
        this.maxPlayers = maxPlayers;
    }

    public static TournoiState getFromID(Integer id) {
        return ID_GAME.get(id);
    }
    public String getDisplayName() {
        return this.displayName;
    }

    public int getId() {
        return id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    static {
        TournoiState[] tournoiEnums = values();
        int var1 = tournoiEnums.length;

        for(int i = 0; i < var1; ++i) {
            TournoiState units = tournoiEnums[i];
            ID_GAME.put(units.id, units);
        }
    }

    public static void setState(TournoiState state){
        TournoiState.currentState = state;
    }
    public static boolean isState(TournoiState state){
        return TournoiState.currentState == state;
    }
    public static TournoiState getState(){
        return currentState;
    }
}

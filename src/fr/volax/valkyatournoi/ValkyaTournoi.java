package fr.volax.valkyatournoi;

import fr.volax.valkyatournoi.tournoi.*;
import fr.volax.volaxapi.VolaxAPI;
import fr.volax.volaxapi.tool.config.ConfigBuilder;
import fr.volax.volaxapi.tool.config.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ValkyaTournoi extends JavaPlugin {
    private static ValkyaTournoi instance;
    private static String prefix = "§6Valkya »";
    private ConfigBuilder configBuilder;
    private TournoiPlayersManager tournoiPlayersManager;
    private Tournoi tournoi;
    public Map<Player, TournoiInventory> mode;
    @Override
    public void onEnable() {
        instance = this;
        VolaxAPI.setInstance(instance);
        configBuilder = new ConfigBuilder(new FileManager(instance));
        tournoiPlayersManager = new TournoiPlayersManager();
        TournoiState.setState(TournoiState.NONE);
        tournoi = new Tournoi();
        mode = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(new Events(), this);

        new TournoiCommand("tournoi");
    }

    public static ValkyaTournoi getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }

    public TournoiPlayersManager getTournoiPlayersManager() {
        return tournoiPlayersManager;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }
}

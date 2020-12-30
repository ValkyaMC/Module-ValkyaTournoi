package fr.volax.valkyatournoi.util;

import fr.volax.valkyatournoi.ValkyaTournoi;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class MessageUtil {
    public static void debugMesssage(String... message){
        for(String messages : message) {
            System.out.println("[TOURNOI] " + messages);
        }
    }

    public static void broadcastMessage(String... message){
        for(String messages : message)
            Bukkit.broadcastMessage(ValkyaTournoi.getPrefix() + " " + messages);
    }

    public static void senderMessage(CommandSender sender, String... message){
        for(String messages : message)
            sender.sendMessage(ValkyaTournoi.getPrefix() + " " + messages);
    }
}

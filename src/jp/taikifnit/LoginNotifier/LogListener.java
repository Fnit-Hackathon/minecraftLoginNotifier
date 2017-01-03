package jp.taikifnit.LoginNotifier;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

/**
 * Created by taikifnit on 2017/01/02.
 */

public class LogListener implements Listener{

    FileConfiguration config;

    public LogListener(FileConfiguration c) {
        this.config = c;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {

        String message = event.getPlayer().getName() + " joins " + Bukkit.getServerName();

        preparePost(message);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws IOException {

        String message = event.getPlayer().getName() + " leaves " + Bukkit.getServerName();

        preparePost(message);
    }

    void preparePost(String message) throws IOException {
        String urlStr = config.getString("url");
        String channel = config.getString("channel");
        String token = config.getString("token");
        String username = config.getString("bot_name");

        HttpClientConnection hcc = new HttpClientConnection(urlStr, token, channel, username);
        hcc.post(message);
    }
}

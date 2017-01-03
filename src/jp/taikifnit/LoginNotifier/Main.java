package jp.taikifnit.LoginNotifier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by taikifnit on 2017/01/02.
 */

public class Main extends JavaPlugin{

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("url", "https://slack.com/api/chat.postMessage");
        config.addDefault("token", "token_is_here");
        config.addDefault("channel", "minecraft_login_alert");
        config.addDefault("bot_name", "MC_LNbot");
        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new LogListener(config), this);
    }

    @Override
    public void onDisable() {

    }
}

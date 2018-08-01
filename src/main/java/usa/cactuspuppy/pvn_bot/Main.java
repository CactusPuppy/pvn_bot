package usa.cactuspuppy.pvn_bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.bukkit.plugin.java.JavaPlugin;
import usa.cactuspuppy.pvn_bot.discord.JDAHandler;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.sql.Statement;

public class Main extends JavaPlugin {
    private static Main instance;
    private JDA jda;
    private Statement sql;

    @Override
    public void onEnable() {
        createConfig();
        initDiscord();
        //init database
    }

    @Override
    public void onDisable() {

    }

    private void createConfig() {
        if (!getDataFolder().exists()) {
            getLogger().info("Data folder not found, creating now...");
            if (!getDataFolder().mkdirs()) {
                getLogger().severe("Could not create data folder!");
            }
        }
        File config = new File(getDataFolder(), "config.yml");
        if (config.exists()) {
            getLogger().info("Loading config.yml...");
        } else {
            getLogger().info("config.yml not found, creating now...");
            saveDefaultConfig();
        }
    }

    private void initDiscord() {
        JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
        jdaBuilder.setToken(getConfig().getString("bot_token"));
        jdaBuilder.addEventListener(new JDAHandler());
        try {
            jda = jdaBuilder.buildAsync();
        } catch (LoginException e) {
            getLogger().severe("Unable to initiate Discord integration!");
            e.printStackTrace();
        }
    }

    /*
    private void registerCommands() {
        getCommand("pvn").setExecutor();
        getCommand("group").setExecutor();
        getCommand("t").setExecutor();
    }*/

    public static Main getInstance() {
        return instance;
    }

    public JDA getJda() {
        return jda;
    }
}

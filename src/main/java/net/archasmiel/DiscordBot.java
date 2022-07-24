package net.archasmiel;

import net.archasmiel.listeners.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.security.auth.login.LoginException;
import java.io.InputStream;

public class DiscordBot {

    public static String TOKEN = readToken();


    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA bot = JDABuilder.createDefault(TOKEN)
                .addEventListeners(new BotCommands())
                .setActivity(Activity.playing("ROBLOX"))
                .build().awaitReady();

    }





    private static String readToken() {
        InputStream is = DiscordBot.class.getResourceAsStream("/token.json");
        if (is == null) throw new IllegalStateException("Token read exception!");

        return new JSONObject(new JSONTokener(is)).getString("token");
    }

}

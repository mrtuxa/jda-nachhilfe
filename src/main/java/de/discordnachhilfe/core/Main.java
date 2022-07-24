package de.discordnachhilfe.core;

import de.discordnachhilfe.database.DatabaseImpl;
import de.discordnachhilfe.events.guild.MemberJoin;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static Dotenv dotenv = Dotenv.load();

    public void Main() throws LoginException {
        List<GatewayIntent> intents = new ArrayList<>(
                Arrays.asList(GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_BANS,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_INVITES,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.MESSAGE_CONTENT)
        );

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(dotenv.get("TOKEN"));

        builder.setActivity(Activity.listening("Spotify"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(intents);
        builder.enableCache(CacheFlag.VOICE_STATE);
        shardManager = builder.build();
        shardManager.addEventListener(new MemberJoin(DatabaseImpl.getDatabase()));
    }
    private static ShardManager shardManager;

    public static ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) throws LoginException {
       Main api = new Main();
    }
}

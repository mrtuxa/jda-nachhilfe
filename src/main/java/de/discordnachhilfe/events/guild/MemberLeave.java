package de.discordnachhilfe.events.guild;

import de.discordnachhilfe.database.Database;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberLeave extends ListenerAdapter {

    private Database database;

    public MemberLeave(Database database){
        this.database = database;
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        if (event.getUser().isBot()) {
            return;
        }

        database.deleteUser(event.getGuild().getId(), event.getUser().getId());


    }
}

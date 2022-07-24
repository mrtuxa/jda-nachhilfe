package de.discordnachhilfe.events.guild;

import de.discordnachhilfe.database.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MemberJoin extends ListenerAdapter {

    private Database database;

    public MemberJoin(Database database){
        this.database = database;
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        if (event.getMember().getUser().isBot()) {
            return;
        }

        database.createUser(event.getGuild().getId(), event.getMember().getId());

        EmbedBuilder loadUser = new EmbedBuilder();

        loadUser.setTitle("__Nachhilfe Discord__");
        loadUser.setDescription("Willkommen auf dem Server von unserem Nachhilfe Discord");
        loadUser.addField("Willkommen "  + event.getMember().getAsMention(), "<a:hey:834755557536956437> Wir wünschen dir eine angenehme Zeit auf unserem einzigartigen Nachhilfe Discord", true);
        loadUser.setFooter("Nachhilfe Discord", event.getJDA().getSelfUser().getAvatarUrl());
        loadUser.setColor(Color.WHITE);

        TextChannel channel = event.getGuild().getTextChannelById("1000820254836019233");

        if (!event.getMember().getRoles().contains("Schüler:in")) {
            String member = event.getMember().getId();
            Guild guild = event.getGuild();


            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("826922358090498048")).queue();
            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("827219457209794601")).queue();
            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("838704685741375498")).queue();
            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("827230497419034624")).queue();
            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("827360755766394910")).queue();
            event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getUser().getId()), event.getJDA().getRoleById("827364503691329557")).queue();

            TextChannel logs = event.getGuild().getTextChannelById("logs");

            EmbedBuilder log = new EmbedBuilder();
            log.setTitle("__Nachhilfe Discord__");
            log.addField("Neuer User", event.getMember().getAsMention() + "Basis Rollen wurden dem User hinzugefügt", true);
            log.setFooter("Nachhilfe Discord", event.getJDA().getSelfUser().getAvatarUrl());
            log.setColor(Color.WHITE);

            logs.sendMessageEmbeds(log.build()).queue();
        }

        channel.sendMessageEmbeds(loadUser.build()).queue();
    }
}

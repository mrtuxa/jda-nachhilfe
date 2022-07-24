package de.discordnachhilfe.events.interaction;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("__Nachhilfe Discord__");
            help.setDescription("Willkommen auf dem Server von unserem Nachhilfe Discord");
            help.addField("Willkommen "  + event.getMember().getAsMention(), "<a:hey:834755557536956437> Wir wünschen dir eine angenehme Zeit auf unserem einzigartigen Nachhilfe Discord", true);
            help.setFooter("Nachhilfe Discord", event.getJDA().getSelfUser().getAvatarUrl());
            help.setColor(Color.WHITE);

            event.replyEmbeds(help.build()).setEphemeral(true);
        }
    }
}

package de.discordnachhilfe.util;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GetSpotifySong extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().equals("spotify")) {
            Member member = event.getMember();

           if (member.getActivities().equals(Activity.listening("Spotify"))) {
               String song = Objects.requireNonNull(member.getActivities().get(0).asRichPresence()).getState();
               String artist = Objects.requireNonNull(member.getActivities().get(0).asRichPresence()).getDetails();

               event.getChannel().sendMessage("**" + member.getEffectiveName() + "** ist gerade " + song + " von " + artist + ".").queue();
            }
        }
    }
}

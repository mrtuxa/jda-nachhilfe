package de.discordnachhilfe.events.ticketModal;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class CreateTicket extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("ticket")) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            String category = event.getValue("kategorie").getAsString();
            String subject = event.getValue("betreff").getAsString();

            if (event.getMember().getRoles().contains("Ticket-Sperre")) {
                event.reply("Du hast eine Ticket-Sperre. Bitte warte noch ein paar Tage.").queue();
                return;
            } else {
                guild.createTextChannel("ticket-" + subject)
                        .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null).queue();
            }
        }
    }
}

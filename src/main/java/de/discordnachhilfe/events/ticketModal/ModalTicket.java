package de.discordnachhilfe.events.ticketModal;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class ModalTicket extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        SelectMenu selectMenu = SelectMenu.create("Kategorie")
                    .addOption("support", "Support", "Support")
                    .addOptions(SelectOption.of("unban", "Unban")
                            .withDescription("Unban")
                            .withEmoji(Emoji.fromUnicode("\uD83D\uDEAB"))
                            .withDefault(true))
                    .build();

            TextInput textInput = TextInput.create("betreff", "Betreff", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Betreff")
                    .setMinLength(10)
                    .build();

            Modal modal = Modal.create("ticket", "Ticket")
                    .addActionRows(ActionRow.of(selectMenu), ActionRow.of(textInput))
                    .build();

            event.replyModal(modal).queue();
        }
}



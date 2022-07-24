package de.discordnachhilfe.logs;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class LogChannel extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        TextChannel channel = event.getGuild().getTextChannelById("");

        MessageHistory history = MessageHistory.getHistoryFromBeginning(channel).complete();
        List<Message> messages = history.getRetrievedHistory();


        Path path = Paths.get("log-" + channel.getName()  + LocalDate.now() + ".log");
        String content = String.valueOf(messages);

        try {
            Files.writeString(path, content, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}

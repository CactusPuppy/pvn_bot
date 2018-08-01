package usa.cactuspuppy.pvn_bot.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class JDAHandler extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) { return; }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        if (content.startsWith("!poke ")) {
            channel.sendMessage("Stop it I'm sleeping").queue();
        } else if (content.equals("!ping ")) {
            channel.sendMessage("Pong!").queue();
        } else if (content.startsWith("!echo ")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Echoing " + event.getAuthor().getName() + "'s Message:");
            builder.setColor(new Color(0x20D0F4));
            content = content.substring("!echo ".length());
            builder.addField("Message:", content, false);
            channel.sendMessage(builder.build()).queue();
        }
    }
}

package net.savagellc.savagecore.listener;

import net.savagellc.savagecore.persist.enums.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DenyIPPostListener implements Listener {

    @EventHandler
    public void onIPPost(AsyncPlayerChatEvent event) {
        String[] parts  = event.getMessage().split(" ");
        for (String part : parts) {
            if (this.isValidIP1(part) || this.isValidIP2(part) || this.isValidIP3(part)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(Messages.ANTI_POST_IP.getMessage());
            }
        }
    }

    private boolean isValidIP1(String ip) {
        Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    private boolean isValidIP2(String ip) {
        Pattern pattern = Pattern.compile("^(\\d{1,3})\\,(\\d{1,3})\\,(\\d{1,3})\\,(\\d{1,3})$");
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    private boolean isValidIP3(String ip) {
        Pattern pattern = Pattern.compile("^(\\d{1,3})\\*(\\d{1,3})\\*(\\d{1,3})\\*(\\d{1,3})$");
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }
}
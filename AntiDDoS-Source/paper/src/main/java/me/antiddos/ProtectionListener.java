package me.antiddos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public final class ProtectionListener implements Listener {
    private final AntiDDoSPlugin plugin;
    public ProtectionListener(AntiDDoSPlugin plugin) { this.plugin = plugin; }
    @EventHandler public void login(AsyncPlayerPreLoginEvent e) {
        if (!plugin.allowConnection(e.getAddress().getHostAddress())) e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Too many connections. Try again later.");
    }
    @EventHandler public void command(PlayerCommandPreprocessEvent e) { if (!plugin.allowPlayer(e.getPlayer().getUniqueId())) e.setCancelled(true); }
    @EventHandler public void chat(AsyncPlayerChatEvent e) { if (!plugin.allowPlayer(e.getPlayer().getUniqueId())) e.setCancelled(true); }
}

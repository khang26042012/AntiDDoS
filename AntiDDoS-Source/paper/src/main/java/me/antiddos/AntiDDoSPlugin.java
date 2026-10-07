package me.antiddos;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AntiDDoSPlugin extends JavaPlugin {
    private final Map<String, RateLimit> connections = new ConcurrentHashMap<>();
    private final Map<UUID, RateLimit> players = new ConcurrentHashMap<>();
    public boolean allowConnection(String ip) { return connections.computeIfAbsent(ip, k -> new RateLimit(5, 10_000)).tryAcquire(); }
    public boolean allowPlayer(UUID id) { return players.computeIfAbsent(id, k -> new RateLimit(100, 1_000)).tryAcquire(); }
    @Override public void onEnable() { saveDefaultConfig(); getServer().getPluginManager().registerEvents(new ProtectionListener(this), this); getLogger().info("AntiDDoS enabled"); }
}

package com.alihaine.kralipacket;

import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class KraliPacket extends JavaPlugin {
    private static KraliPacket kraliPacket;
    private final List<KPacket> registeredPackets = new ArrayList<>();

    public void onEnable() {
        kraliPacket = this;
        getServer().getPluginManager().registerEvents(new onPlayerConnection(), this);
        getLogger().info("KraliPacket enabled");
    }

    public void onDisable() {
        getLogger().info("KraliPacket disabled");
    }

    public void sendConnexionPackets(Player player) {
        for (KPacket packet : registeredPackets) {
            if (packet.getConnexionPacket() == null) continue;
            packet.sendConnexionPackets(player);
        }
    }

    /*
     * Registers a new custom plugin message channel and its associated packet.
     *
     * If another plugin is already using the same channel name,
     * no exception will be thrown, but a warning will appear in the console.
     *
     * You can optionally provide a 'connexion packet', which will be automatically
     * sent to the player when he joins. This can be used to initialize client-side
     * configuration, cache data, or any other setup that you want to do once.
     *
     * Convention: the connexion packet should always have an action ID of 0, or null if you don't want to use.
     */
    public KPacket registerPacket(final String channel, final ByteArrayDataOutput connexionPacket) {
        if (this.channelAlreadyListened(channel))
            getLogger().warning("[KraliPacket] Channel " + channel + " is already listened, are you sure about what you are doing ?");
        final KPacket packet = new KPacket(channel, connexionPacket);
        registeredPackets.add(packet);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, channel);
        getLogger().info("[KraliPacket] Registered a new packet on the channel " + channel);
        return packet;
    }

    public boolean channelAlreadyListened(final String channel) {
        for (KPacket packet : registeredPackets)
            if (packet.getChannel().equals(channel)) return true;
        return false;
    }

    public static KraliPacket getKraliPacket() { return kraliPacket; }
}

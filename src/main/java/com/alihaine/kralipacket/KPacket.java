package com.alihaine.kralipacket;

import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KPacket {
    private final String channel;
    private final ByteArrayDataOutput connexionPacket;

    public KPacket(final String channel, final ByteArrayDataOutput connexionPacket) {
        this.channel = channel;
        this.connexionPacket = connexionPacket;
    }

    public void sendPacket(final Player player, final ByteArrayDataOutput outputData) {
        if (KraliPacket.getKraliPacket().isDeepLog())
            Bukkit.getLogger().info("[KPacket] Sending packet player: " + player.getName() + " channel: " + this.channel);
        player.sendPluginMessage(
                KraliPacket.getKraliPacket(),
                this.channel,
                outputData.toByteArray()
        );
    }

    public void sendConnexionPackets(final Player player) {
        if (KraliPacket.getKraliPacket().isDeepLog())
            Bukkit.getLogger().info("[KPacket] Connexion packet player: " + player.getName() + " channel: " + this.channel);
        this.sendPacket(player, this.connexionPacket);
    }

    public String getChannel() {
        return this.channel;
    }

    public ByteArrayDataOutput getConnexionPacket() {
        return this.connexionPacket;
    }
}

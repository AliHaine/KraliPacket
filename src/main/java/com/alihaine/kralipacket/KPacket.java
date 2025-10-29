package com.alihaine.kralipacket;

import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.entity.Player;

public class KPacket {
    private final String channel;
    private final ByteArrayDataOutput connexionPacket;

    public KPacket(final String channel, final ByteArrayDataOutput connexionPacket) {
        this.channel = channel;
        this.connexionPacket = connexionPacket;
    }

    public void sendPacket(final Player player, final ByteArrayDataOutput outputData) {
        player.sendPluginMessage(
                KraliPacket.getKraliPacket(),
                this.channel,
                outputData.toByteArray()
        );
    }

    public void sendConnexionPackets(final Player player) {
        this.sendPacket(player, this.connexionPacket);
    }

    public String getChannel() {
        return channel;
    }

    public ByteArrayDataOutput getConnexionPacket() {
        return connexionPacket;
    }
}

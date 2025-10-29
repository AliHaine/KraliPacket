package com.alihaine.kralipacket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerConnection implements Listener {
    private static final KraliPacket kraliPacket = KraliPacket.getKraliPacket();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        kraliPacket.sendConnexionPackets(event.getPlayer());

    }
}

package com.alihaine.kralipacket;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

//Will Work at 99.9%, we should use PlayerRegisterChannelEvent to reach 100%.
public class onPlayerConnection implements Listener {
    private final KraliPacket kraliPacket = KraliPacket.getKraliPacket();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(
                KraliPacket.getKraliPacket(),
                () -> this.kraliPacket.sendConnexionPackets(event.getPlayer()),
                20L
        );
    }
}

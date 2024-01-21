package com.integral.gregorioustick;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.world.GameRules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "GregoriousTickMod", name = "GregoriousTick", version = "0.1", acceptableRemoteVersions = "*")
public class GregoriousTick {
    private static final Logger LOGGER = LogManager.getLogger("GregoriousTick");

    @Mod.EventHandler
    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        LOGGER.info("GregoriousTick has been initialized.");
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.world.isRemote)
            return;

        int playerCount = MinecraftServer.getServer().getCurrentPlayerCount();
        boolean daylightCycleState = playerCount > 0;

        LOGGER.info(String.format("Dedicated Server world just loaded. Setting DaylightCycle to %s (%d players connected).", daylightCycleState, playerCount));

        this.setDaylightCycle(daylightCycleState);
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        if (!(event.player instanceof EntityPlayerMP))
            return;

        EntityPlayerMP player = (EntityPlayerMP) event.player;

        LOGGER.info(String.format("%s has logged in. Toggling DaylightCycle to true.", player.getDisplayName()));

        this.setDaylightCycle(true);
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        if (!(event.player instanceof EntityPlayerMP))
            return;

        EntityPlayerMP player = (EntityPlayerMP) event.player;
        int playerCount = MinecraftServer.getServer().getCurrentPlayerCount() - 1;
        boolean daylightCycleState = playerCount > 0;

        LOGGER.info(String.format("%s has disconnected. %d players connected (daylightCycle => %s)", player.getDisplayName(), playerCount, Boolean.toString(daylightCycleState)));

        this.setDaylightCycle(daylightCycleState);
    }

    private void setDaylightCycle(boolean state) {
        GameRules gameRules = MinecraftServer.getServer().worldServers[0].getGameRules();

        if (gameRules.getGameRuleBooleanValue("doDaylightCycle") == state)
            return;

        gameRules.setOrCreateGameRule("doDaylightCycle", Boolean.toString(state));
    }
}
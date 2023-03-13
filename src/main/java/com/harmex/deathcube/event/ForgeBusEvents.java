package com.harmex.deathcube.event;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.server.commands.SkillCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DeathCube.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvents {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        new SkillCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}

package com.harmex.deathcube.server.commands;

import com.harmex.deathcube.util.capabilities.skills.SkillsDataProvider;
import com.harmex.deathcube.world.skill.Skills;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.command.EnumArgument;

import java.util.Collection;

public class SkillCommand {
    public SkillCommand(CommandDispatcher<CommandSourceStack> pDispatcher) {
        pDispatcher.register(Commands.literal("skill").requires(commandSourceStack ->
                        commandSourceStack.hasPermission(2))
                .then(Commands.argument("targets", EntityArgument.entities())
                        .then(Commands.argument("skill", EnumArgument.enumArgument(Skills.class))
                                .then(Commands.literal("add")
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                .executes(context ->
                                                        addExperience(context.getSource(),
                                                                context.getArgument("skill", Skills.class),
                                                                EntityArgument.getPlayers(context, "targets"),
                                                                IntegerArgumentType.getInteger(context, "amount"))
                                                )
                                                .then(Commands.literal("experience")
                                                        .executes(context ->
                                                                addExperience(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                                .then(Commands.literal("level")
                                                        .executes(context ->
                                                                addLevel(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                        )
                                )
                                .then(Commands.literal("sub")
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                .executes(context ->
                                                        addExperience(context.getSource(),
                                                                context.getArgument("skill", Skills.class),
                                                                EntityArgument.getPlayers(context, "targets"),
                                                                IntegerArgumentType.getInteger(context, "amount"))
                                                )
                                                .then(Commands.literal("experience")
                                                        .executes(context ->
                                                                addExperience(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                                .then(Commands.literal("level")
                                                        .executes(context ->
                                                                subLevel(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                        )
                                )
                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                .executes(context ->
                                                        addExperience(context.getSource(),
                                                                context.getArgument("skill", Skills.class),
                                                                EntityArgument.getPlayers(context, "targets"),
                                                                IntegerArgumentType.getInteger(context, "amount"))
                                                )
                                                .then(Commands.literal("experience")
                                                        .executes(context ->
                                                                addExperience(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                                .then(Commands.literal("level")
                                                        .executes(context ->
                                                                setLevel(context.getSource(),
                                                                        context.getArgument("skill", Skills.class),
                                                                        EntityArgument.getPlayers(context, "targets"),
                                                                        IntegerArgumentType.getInteger(context, "amount"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static int addExperience(CommandSourceStack pSource, Skills pSkill, Collection<? extends ServerPlayer> pTargets, float pAmount) {
        for (ServerPlayer player : pTargets) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData ->
                    skillsData.addExperience(player, pSkill, pAmount));
        }

        if (pTargets.size() == 1) {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.experience.success.single", pAmount, pTargets.iterator().next().getDisplayName()), true);
        } else {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.experience.success.multiple", pAmount, pTargets.size()), true);
        }

        return pTargets.size();
    }

    private static int addLevel(CommandSourceStack pSource, Skills pSkill, Collection<? extends ServerPlayer> pTargets, int pAmount) {
        for (ServerPlayer player : pTargets) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                skillsData.addLevel(player, pSkill, pAmount);
            });
        }

        if (pTargets.size() == 1) {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.single", pAmount, pTargets.iterator().next().getDisplayName()), true);
        } else {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.multiple", pAmount, pTargets.size()), true);
        }

        return pTargets.size();
    }

    private static int subLevel(CommandSourceStack pSource, Skills pSkill, Collection<? extends ServerPlayer> pTargets, int pAmount) {
        for (ServerPlayer player : pTargets) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                skillsData.subLevel(player, pSkill, pAmount);
            });
        }

        if (pTargets.size() == 1) {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.single", pAmount, pTargets.iterator().next().getDisplayName()), true);
        } else {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.multiple", pAmount, pTargets.size()), true);
        }

        return pTargets.size();
    }

    private static int setLevel(CommandSourceStack pSource, Skills pSkill, Collection<? extends ServerPlayer> pTargets, int pAmount) {
        for (ServerPlayer player : pTargets) {
            player.getCapability(SkillsDataProvider.SKILLS).ifPresent(skillsData -> {
                skillsData.setLevel(player, pSkill, pAmount);
            });
        }

        if (pTargets.size() == 1) {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.single", pAmount, pTargets.iterator().next().getDisplayName()), true);
        } else {
            pSource.sendSuccess(Component.translatable("commands.deathcube.skill.add.level.success.multiple", pAmount, pTargets.size()), true);
        }

        return pTargets.size();
    }
}

package com.harmex.deathcube.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DeathCubeCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> APPLES_TEMP_HEALTH;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_DIAMOND_MAX_HEALTH;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_DIAMOND_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_DIAMOND_BOOST_AMOUNT;
    
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_NETHERITE_MAX_HEALTH;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_NETHERITE_FIRST_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_NETHERITE_FIRST_BOOST_AMOUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_NETHERITE_LAST_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_NETHERITE_LAST_BOOST_AMOUNT;
    
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_MAX_HEALTH;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_FIRST_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_FIRST_BOOST_AMOUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_SECOND_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_SECOND_BOOST_AMOUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_LAST_BOOST_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> APPLES_BEDROCK_LAST_BOOST_AMOUNT;

    public static final ForgeConfigSpec.ConfigValue<Boolean> EMERALD_ARMOR_EFFECT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> EMERALD_ARMOR_DECAY;
    public static final ForgeConfigSpec.ConfigValue<Boolean> OBSIDIAN_ARMOR_EFFECT;
    public static final ForgeConfigSpec.ConfigValue<Boolean> OBSIDIAN_ARMOR_DECAY;

    static {
        BUILDER.push("General Configs for DeathCube");

        APPLES_TEMP_HEALTH = BUILDER.comment("Should special apples give temporary health boost ?")
                .define("Apples Temporary Health", true);
        //region Diamond Apple
        APPLES_DIAMOND_MAX_HEALTH = BUILDER.comment("Max health for Diamond Apples.")
                .define("Apples Diamond Max Health", 26);
        APPLES_DIAMOND_BOOST_CHANCE = BUILDER.comment("Chance for a Diamond Apple to boost health.")
                .define("Apples Diamond Boost Chance", 20);
        APPLES_DIAMOND_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by a Diamond Apple.")
                .define("Apples Diamond Boost Amount", 2);
        //endregion
        //region Netherite Apple
        APPLES_NETHERITE_MAX_HEALTH = BUILDER.comment("Max health for Netherite Apples.")
                .define("Apples Netherite Max Health", 32);
        APPLES_NETHERITE_FIRST_BOOST_CHANCE = BUILDER.comment("Chance for the FIRST boost of a Netherite Apple to boost health.")
                .define("Apples Netherite First Boost Chance", 40);
        APPLES_NETHERITE_FIRST_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by the FIRST boost of a Netherite Apple.")
                .define("Apples Netherite First Boost Amount", 2);
        APPLES_NETHERITE_LAST_BOOST_CHANCE = BUILDER.comment("Chance for the LAST boost of a Netherite Apple to boost health.")
                .define("Apples Netherite Last Boost Chance", 20);
        APPLES_NETHERITE_LAST_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by the LAST boost a Netherite Apple.")
                .define("Apples Netherite Last Boost Amount", 4);
        //endregion
        //region Bedrock Apple
        APPLES_BEDROCK_MAX_HEALTH = BUILDER.comment("Max health for Bedrock Apples.")
                .define("Apples Bedrock Max Health", 40);
        APPLES_BEDROCK_FIRST_BOOST_CHANCE = BUILDER.comment("Chance for the FIRST boost of a Bedrock Apple to boost health.")
                .define("Apples Bedrock First Boost Chance", 60);
        APPLES_BEDROCK_FIRST_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by the FIRST boost of a Bedrock Apple.")
                .define("Apples Bedrock First Boost Amount", 2);
        APPLES_BEDROCK_SECOND_BOOST_CHANCE = BUILDER.comment("Chance for the SECOND boost of a Bedrock Apple to boost health.")
                .define("Apples Bedrock Second Boost Chance", 40);
        APPLES_BEDROCK_SECOND_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by the SECOND boost a Bedrock Apple.")
                .define("Apples Bedrock Second Boost Amount", 4);
        APPLES_BEDROCK_LAST_BOOST_CHANCE = BUILDER.comment("Chance for the LAST boost of a Bedrock Apple to boost health.")
                .define("Apples Bedrock Last Boost Chance", 20);
        APPLES_BEDROCK_LAST_BOOST_AMOUNT = BUILDER.comment("Amount of health boosted by the LAST boost a Bedrock Apple.")
                .define("Apples Bedrock Last Boost Amount", 6);
        //endregion

        EMERALD_ARMOR_EFFECT = BUILDER.comment("Should the Emerald Armor have the Hero of the Village effect ?")
                .define("Emerald Armor Effect", true);
        EMERALD_ARMOR_DECAY = BUILDER.comment("Should the Emerald Armor decay overtime ?")
                .define("Emerald Armor Decay", true);
        OBSIDIAN_ARMOR_EFFECT = BUILDER.comment("Should the Obsidian Armor have the Fire Resistance effect ?")
                .define("Obsidian Armor Effect", true);
        OBSIDIAN_ARMOR_DECAY = BUILDER.comment("Should the Obsidian Armor decay overtime ?")
                .define("Obsidian Armor Decay", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}

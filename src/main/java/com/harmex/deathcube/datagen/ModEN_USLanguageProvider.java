package com.harmex.deathcube.datagen;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import com.harmex.deathcube.world.item.ModItems;
import com.harmex.deathcube.world.level.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEN_USLanguageProvider extends LanguageProvider {

    public ModEN_USLanguageProvider(PackOutput output, String locale) {
        super(output, DeathCube.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        addItemGroup("buildingBlocks", "DeathCube | Building Blocks");
        addItemGroup("coloredBlocks", "DeathCube | Colored Blocks");
        addItemGroup("naturalBlocks", "DeathCube | Natural Blocks");
        addItemGroup("functionalBlocks", "DeathCube | Functional Blocks");
        addItemGroup("redstone", "DeathCube | Redstone");
        addItemGroup("tools", "DeathCube | Tools & Utilities");
        addItemGroup("combat", "DeathCube | Combat");
        addItemGroup("foodAndDrinks", "DeathCube | Food & Drinks");
        addItemGroup("ingredients", "DeathCube | Ingredients");
        addItemGroup("spawnEggs", "DeathCube | Spawn Eggs");

        addCuriosSlot("totem", "Totem");

        add("gui.jei.deathcube.category.matter_manipulation", "Matter Manipulating");

        add("chat.deathcube.lvlup", "%s leveled Up! %s!");

        addKeyCategory("deathcube", "DeathCube");
        addKeyBind("toggleSkillHud", "Toggle Skill HUD");

        addEnchantment("smelt", "Smelt");
        addEnchantment("compact", "Compact");

        addSkill("combat", "Combat");
        addSkill("woodcutting", "Woodcutting");
        addSkill("mining", "Mining");
        addSkill("farming", "Farming");
        addSkill("fishing", "Fishing");

        addAttribute("max_mana", "Max Mana");

        addContainer("upgrading_station", "Upgrading Station");
        addContainer("matter_manipulator", "Matter Manipulator");
        addContainer("resurrection_altar", "Resurrection Altar");

        addItemTooltip("rarity.common", "Common");
        addItemTooltip("rarity.uncommon", "Uncommon");
        addItemTooltip("rarity.rare", "Rare");
        addItemTooltip("rarity.epic", "Epic");
        addItemTooltip("rarity.legendary", "Legendary");
        addItemTooltip("rarity.hellish", "Hellish");
        addItemTooltip("rarity.mythic", "Mythic");
        addItemTooltip("rarity.divine", "Divine");

        addItemTooltip("set", "Set : %s (%s / 4)");
        addItemTooltip("set.oak", "Oak");
        addItemTooltip("set.spruce", "Spruce");
        addItemTooltip("set.birch", "Birch");
        addItemTooltip("set.jungle", "Jungle");
        addItemTooltip("set.acacia", "Acacia");
        addItemTooltip("set.dark_oak", "Dark Oak");
        addItemTooltip("set.mangrove", "Mangrove");
        addItemTooltip("set.cherry", "Cherry");
        addItemTooltip("set.crimson", "Crimson");
        addItemTooltip("set.warped", "Warped");
        addItemTooltip("set.leather", "Leather");
        addItemTooltip("set.gold", "Gold");
        addItemTooltip("set.bone", "Bone");
        addItemTooltip("set.stone", "Stone");
        addItemTooltip("set.copper", "Copper");
        addItemTooltip("set.chainmail", "Chainmail");
        addItemTooltip("set.iron", "Iron");
        addItemTooltip("set.emerald", "Emerald");
        addItemTooltip("set.diamond", "Diamond");
        addItemTooltip("set.obsidian", "Obsidian");
        addItemTooltip("set.netherite", "Netherite");
        addItemTooltip("set.galterius", "Galterius");

        add(ModEntityTypes.AZRATHAL.get().getDescriptionId(), "Azrathal");
        add(ModEntityTypes.BORZADON.get().getDescriptionId(), "Borzadon");
        add(ModEntityTypes.GALTERIUS.get().getDescriptionId(), "Galterius");
        add(ModEntityTypes.NAERVUS.get().getDescriptionId(), "Naervus");
        add(ModEntityTypes.ZANUZAL.get().getDescriptionId(), "Zanuzal");

        add(ModItems.AZRATHAL_SPAWN_EGG.get(), "Azrathal Spawn Egg");
        add(ModItems.BORZADON_SPAWN_EGG.get(), "Borzadon Spawn Egg");
        add(ModItems.GALTERIUS_SPAWN_EGG.get(), "Galterius Spawn Egg");
        add(ModItems.NAERVUS_SPAWN_EGG.get(), "Naervus Spawn Egg");
        add(ModItems.ZANUZAL_SPAWN_EGG.get(), "Zanuzal Spawn Egg");

        add(ModBlocks.ECHO_AMETHYST_BLOCK.get(), "Echo-Amethyst Block");
        add(ModBlocks.UPGRADING_STATION.get(), "Upgrade Station");
        add(ModBlocks.MATTER_MANIPULATOR.get(), "Matter Manipulator");
        add(ModBlocks.RESURRECTION_ALTAR.get(), "Resurrection Altar");

        add(ModBlocks.ZANTHINE_ORE.get(), "Zanthine Ore");
        add(ModBlocks.DEEPSLATE_ZANTHINE_ORE.get(), "Deepslate Zanthine Ore");
        add(ModItems.ZANTHINE.get(), "Zanthine");

        add(ModItems.FRESH_WATER_BOTTLE.get(), "Fresh Water Bottle");
        add(ModItems.CHERRY.get(), "Cherry");
        add(ModItems.TIME_GEM_APPLE.get(), "Time Gem Apple");
        add(ModItems.DIAMOND_APPLE.get(), "Diamond Apple");
        add(ModItems.NETHERITE_APPLE.get(), "Netherite Apple");
        add(ModItems.BEDROCK_APPLE.get(), "Bedrock Apple");

        add(ModItems.TOTEM_OF_RESURRECTION.get(), "Totem of Resurrection");
        add(ModItems.TOTEM_OF_REGENERATION.get(), "Totem of Regeneration");
        add(ModItems.TOTEM_OF_RESISTANCE.get(), "Totem of Resistance");

        add(ModItems.TIME_GEM.get(), "Time Gem");
        add(ModItems.ECHO_AMETHYST_SHARD.get(), "Echo-Amethyst Shard");
        add(ModItems.ECHO_AMETHYST_INGOT.get(), "Echo-Amethyst Ingot");
        add(ModItems.WARDEN_HEART.get(), "Warden Heart");
        add(ModItems.ENDER_DRAGON_SCALE.get(), "Ender Dragon Scale");
        add(ModItems.TIME_WAND.get(), "Time Wand");
        add(ModItems.ENDER_BAG.get(), "Ender Bag");
        add(ModItems.OAK_HELMET.get(), "Oak Helmet");
        add(ModItems.OAK_CHESTPLATE.get(), "Oak Chestplate");
        add(ModItems.OAK_LEGGINGS.get(), "Oak Leggings");
        add(ModItems.OAK_BOOTS.get(), "Oak Boots");
        add(ModItems.SPRUCE_HELMET.get(), "Spruce Helmet");
        add(ModItems.SPRUCE_CHESTPLATE.get(), "Spruce Chestplate");
        add(ModItems.SPRUCE_LEGGINGS.get(), "Spruce Leggings");
        add(ModItems.SPRUCE_BOOTS.get(), "Spruce Boots");
        add(ModItems.BIRCH_HELMET.get(), "Birch Helmet");
        add(ModItems.BIRCH_CHESTPLATE.get(), "Birch Chestplate");
        add(ModItems.BIRCH_LEGGINGS.get(), "Birch Leggings");
        add(ModItems.BIRCH_BOOTS.get(), "Birch Boots");
        add(ModItems.JUNGLE_HELMET.get(), "Jungle Helmet");
        add(ModItems.JUNGLE_CHESTPLATE.get(), "Jungle Chestplate");
        add(ModItems.JUNGLE_LEGGINGS.get(), "Jungle Leggings");
        add(ModItems.JUNGLE_BOOTS.get(), "Jungle Boots");
        add(ModItems.ACACIA_HELMET.get(), "Acacia Helmet");
        add(ModItems.ACACIA_CHESTPLATE.get(), "Acacia Chestplate");
        add(ModItems.ACACIA_LEGGINGS.get(), "Acacia Leggings");
        add(ModItems.ACACIA_BOOTS.get(), "Acacia Boots");
        add(ModItems.DARK_OAK_HELMET.get(), "Dark Oak Helmet");
        add(ModItems.DARK_OAK_CHESTPLATE.get(), "Dark Oak Chestplate");
        add(ModItems.DARK_OAK_LEGGINGS.get(), "Dark Oak Leggings");
        add(ModItems.DARK_OAK_BOOTS.get(), "Dark Oak Boots");
        add(ModItems.MANGROVE_HELMET.get(), "Mangrove Helmet");
        add(ModItems.MANGROVE_CHESTPLATE.get(), "Mangrove Chestplate");
        add(ModItems.MANGROVE_LEGGINGS.get(), "Mangrove Leggings");
        add(ModItems.MANGROVE_BOOTS.get(), "Mangrove Boots");
        add(ModItems.CHERRY_HELMET.get(), "Cherry Helmet");
        add(ModItems.CHERRY_CHESTPLATE.get(), "Cherry Chestplate");
        add(ModItems.CHERRY_LEGGINGS.get(), "Cherry Leggings");
        add(ModItems.CHERRY_BOOTS.get(), "Cherry Boots");
        add(ModItems.CRIMSON_HELMET.get(), "Crimson Helmet");
        add(ModItems.CRIMSON_CHESTPLATE.get(), "Crimson Chestplate");
        add(ModItems.CRIMSON_LEGGINGS.get(), "Crimson Leggings");
        add(ModItems.CRIMSON_BOOTS.get(), "Crimson Boots");
        add(ModItems.WARPED_HELMET.get(), "Warped Helmet");
        add(ModItems.WARPED_CHESTPLATE.get(), "Warped Chestplate");
        add(ModItems.WARPED_LEGGINGS.get(), "Warped Leggings");
        add(ModItems.WARPED_BOOTS.get(), "Warped Boots");
        add(ModItems.BONE_HELMET.get(), "Bone Helmet");
        add(ModItems.BONE_CHESTPLATE.get(), "Bone Chestplate");
        add(ModItems.BONE_LEGGINGS.get(), "Bone Leggings");
        add(ModItems.BONE_BOOTS.get(), "Bone Boots");
        add(ModItems.BONE_SWORD.get(), "Bone Sword");
        add(ModItems.BONE_PICKAXE.get(), "Bone Pickaxe");
        add(ModItems.BONE_AXE.get(), "Bone Axe");
        add(ModItems.BONE_SHOVEL.get(), "Bone Shovel");
        add(ModItems.BONE_HOE.get(), "Bone Hoe");
        add(ModItems.COPPER_HELMET.get(), "Copper Helmet");
        add(ModItems.COPPER_CHESTPLATE.get(), "Copper Chestplate");
        add(ModItems.COPPER_LEGGINGS.get(), "Copper Leggings");
        add(ModItems.COPPER_BOOTS.get(), "Copper Boots");
        add(ModItems.COPPER_SWORD.get(), "Copper Sword");
        add(ModItems.COPPER_PICKAXE.get(), "Copper Pickaxe");
        add(ModItems.COPPER_AXE.get(), "Copper Axe");
        add(ModItems.COPPER_SHOVEL.get(), "Copper Shovel");
        add(ModItems.COPPER_HOE.get(), "Copper Hoe");
        add(ModItems.EMERALD_HELMET.get(), "Emerald Helmet");
        add(ModItems.EMERALD_CHESTPLATE.get(), "Emerald Chestplate");
        add(ModItems.EMERALD_LEGGINGS.get(), "Emerald Leggings");
        add(ModItems.EMERALD_BOOTS.get(), "Emerald Boots");
        add(ModItems.EMERALD_SWORD.get(), "Emerald Sword");
        add(ModItems.EMERALD_PICKAXE.get(), "Emerald Pickaxe");
        add(ModItems.EMERALD_AXE.get(), "Emerald Axe");
        add(ModItems.EMERALD_SHOVEL.get(), "Emerald Shovel");
        add(ModItems.EMERALD_HOE.get(), "Emerald Hoe");
        add(ModItems.OBSIDIAN_HELMET.get(), "Obsidian Helmet");
        add(ModItems.OBSIDIAN_CHESTPLATE.get(), "Obsidian Chestplate");
        add(ModItems.OBSIDIAN_LEGGINGS.get(), "Obsidian Leggings");
        add(ModItems.OBSIDIAN_BOOTS.get(), "Obsidian Boots");
        add(ModItems.OBSIDIAN_SWORD.get(), "Obsidian Sword");
        add(ModItems.OBSIDIAN_PICKAXE.get(), "Obsidian Pickaxe");
        add(ModItems.OBSIDIAN_AXE.get(), "Obsidian Axe");
        add(ModItems.OBSIDIAN_SHOVEL.get(), "Obsidian Shovel");
        add(ModItems.OBSIDIAN_HOE.get(), "Obsidian Hoe");

        add(ModItems.GALTERIUS_HELMET.get(), "Galterius's Helmet");
        add(ModItems.GALTERIUS_CHESTPLATE.get(), "Galterius's Chestplate");
        add(ModItems.GALTERIUS_LEGGINGS.get(), "Galterius's Leggings");
        add(ModItems.GALTERIUS_BOOTS.get(), "Galterius's Boots");
    }

    private void addCuriosSlot(String pId, String pName) {
        add("curios.identifier." + pId, pName);
    }
    private void addItemGroup(String pId, String pName) {
        add("itemGroup." + DeathCube.MODID + "." + pId, pName);
    }
    private void addItemTooltip(String pId, String pName) {
        add("itemTooltip." + DeathCube.MODID + "." + pId, pName);
    }
    private void addEnchantment(String pId, String pName) {
        add("enchantment." + DeathCube.MODID + "." + pId, pName);
    }
    private void addContainer(String pId, String pName) {
        add("container." + DeathCube.MODID + "." + pId, pName);
    }
    private void addAttribute(String pId, String pName) {
        add("attribute.name." + DeathCube.MODID + "." + pId, pName);
    }
    private void addSkill(String pId, String pName) {
        add("skill.name." + DeathCube.MODID + "." + pId, pName);
    }
    private void addKeyBind(String pId, String pName) {
        add("key." + DeathCube.MODID + "." + pId, pName);
    }
    private void addKeyCategory(String pId, String pName) {
        add("key.categories." + DeathCube.MODID + "." + pId, pName);
    }
}

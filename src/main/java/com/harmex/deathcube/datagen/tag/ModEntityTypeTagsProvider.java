package com.harmex.deathcube.datagen.tag;

import com.harmex.deathcube.DeathCube;
import com.harmex.deathcube.util.ModTags;
import com.harmex.deathcube.world.entity.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagsProvider extends net.minecraft.data.tags.EntityTypeTagsProvider {
    public ModEntityTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, ExistingFileHelper pExistingFileHelper) {
        super(pOutput, pProvider, DeathCube.MODID, pExistingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.EntityTypes.BOSSES).add(
                ModEntityTypes.AZRATHAL.get(),
                ModEntityTypes.BORZADON.get(),
                ModEntityTypes.GALTERIUS.get(),
                ModEntityTypes.NAERVUS.get(),
                ModEntityTypes.ZANUZAL.get()
                );
    }
}

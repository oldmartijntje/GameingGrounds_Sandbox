package com.gameinggrounds.sandbox.datagen;

import com.gameinggrounds.sandbox.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOL_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.KITSUNE_MAISON_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUESTIONABLE_SMITHING_TEMPLATE, Models.GENERATED);

    }
}

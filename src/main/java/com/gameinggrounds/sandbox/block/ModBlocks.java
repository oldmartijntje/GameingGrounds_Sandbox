package com.gameinggrounds.sandbox.block;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(GameingGroundsSandbox.MOD_ID, name), block);
    }

    public static final Block RED_SUGAR_ORE = registerBlock("red_sugar_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(3f)
                            .requiresTool()));
    public static final Block RED_SUGAR_DEEPSLATE_ORE = registerBlock("red_sugar_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.create().strength(4f)
                            .sounds(BlockSoundGroup.DEEPSLATE)
                            .requiresTool()));
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(GameingGroundsSandbox.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(GameingGroundsSandbox.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        GameingGroundsSandbox.LOGGER.info("registering ModBlocks for " + GameingGroundsSandbox.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {

        });
    }
}

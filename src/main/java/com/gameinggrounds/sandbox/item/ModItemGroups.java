package com.gameinggrounds.sandbox.item;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SANDBOX_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "all_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FURY_SMITHING_TEMPLATE))
                    .displayName(Text.translatable("itemgroup.gameinggrounds-sandbox.all_items"))
                    .entries(((displayContext, entries) -> {

                        entries.add(ModItems.DRILL);

                        entries.add(ModItems.COOL_HAMMER);
                        entries.add(ModItems.RED_SUGAR);
                        entries.add(ModItems.WOOD_SLIME_SWORD);
                        entries.add(ModItems.STONE_SLIME_SWORD);
                        entries.add(ModItems.IRON_SLIME_SWORD);
                        entries.add(ModItems.GOLD_SLIME_SWORD);
                        entries.add(ModItems.DIAMOND_SLIME_SWORD);
                        entries.add(ModItems.NETHERITE_SLIME_SWORD);

                        entries.add(ModItems.KITSUNE_MAISON_MUSIC_DISC);
                        entries.add(ModItems.QUESTIONABLE_SMITHING_TEMPLATE);
                        entries.add(ModItems.FURY_SMITHING_TEMPLATE);
                    })).build());

    public static final ItemGroup SANDBOX_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "all_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.RED_SUGAR_ORE))
                    .displayName(Text.translatable("itemgroup.gameinggrounds-sandbox.all_blocks"))
                    .entries(((displayContext, entries) -> {

                        entries.add(ModBlocks.RED_SUGAR_ORE);
                        entries.add(ModBlocks.RED_SUGAR_DEEPSLATE_ORE);


                    })).build());

    public static void registerItemGroups() {
        GameingGroundsSandbox.LOGGER.info("registering Item groups for " + GameingGroundsSandbox.MOD_ID);
    }
}

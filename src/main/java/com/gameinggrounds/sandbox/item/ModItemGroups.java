package com.gameinggrounds.sandbox.item;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
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
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DRILL))
                    .displayName(Text.translatable("itemgroup.gameinggrounds-sandbox.all_items"))
                    .entries(((displayContext, entries) -> {

                        entries.add(ModItems.DRILL);

                        entries.add(ModItems.COOL_HAMMER);

                        entries.add(ModItems.KITSUNE_MAISON_MUSIC_DISC);
                        entries.add(ModItems.QUESTIONABLE_SMITHING_TEMPLATE);
                    })).build());

    public static void registerItemGroups() {
        GameingGroundsSandbox.LOGGER.info("registering Item groups for " + GameingGroundsSandbox.MOD_ID);
    }
}

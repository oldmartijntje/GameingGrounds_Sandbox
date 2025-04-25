package com.gameinggrounds.sandbox.datagen;

import com.gameinggrounds.sandbox.item.ModItems;
import com.gameinggrounds.sandbox.item.custom.TradingCard;
import com.gameinggrounds.sandbox.item.custom.TradingCardsCreator;
import com.gameinggrounds.sandbox.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ItemTags.SWORDS);
        getOrCreateTagBuilder(ItemTags.PICKAXES);
        getOrCreateTagBuilder(ItemTags.HOES);
        getOrCreateTagBuilder(ItemTags.AXES);
        getOrCreateTagBuilder(ItemTags.SHOVELS);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.QUESTIONABLE_SMITHING_TEMPLATE)
                .add(ModItems.FURY_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(Items.ECHO_SHARD)
                .add(Items.HONEYCOMB)
                .add(Items.FIRE_CHARGE)
                .add(Items.SLIME_BALL)
                .add(Items.NETHER_STAR)
                .add(Items.ENDER_PEARL);

        for (Item item : TradingCardsCreator.getPlayingCardsList()) {
            getOrCreateTagBuilder(ModTags.Items.TRADING_CARDS).add(item);
        }
    }
}

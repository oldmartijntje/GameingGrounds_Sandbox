package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.*;

public class TradingCardsCreator {
    private static final Map<Integer, List<Item>> ALL_TRADING_CARDS = new HashMap<>();

    static {
        // Generate all trading cards at class load time
        registerTradingCard("steve", 0);
        registerTradingCard("alex", 1);
    }

    public static void RegisterModModels(ItemModelGenerator itemModelGenerator) {
        for (List<Item> itemList : ALL_TRADING_CARDS.values()) {
            for (Item item : itemList) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        }
    }

    private static void registerTradingCard(String registerNameOffset, Integer minRarity) {
        Item tradingCard = registerItem("trading_card_" + registerNameOffset,
                new TradingCardPack(new Item.Settings().maxCount(16)));

        ALL_TRADING_CARDS.computeIfAbsent(minRarity, k -> new ArrayList<>()).add(tradingCard);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GameingGroundsSandbox.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GameingGroundsSandbox.LOGGER.info("registering Trading cards for " + GameingGroundsSandbox.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            // add items to item group here, if needed
        });
    }
}


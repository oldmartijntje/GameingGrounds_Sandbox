package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
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
        registerTradingCard("bean", 0);
        registerTradingCard("duck", 0);
        registerTradingCard("heat", 3);
        registerTradingCard("life", 3);
        registerTradingCard("spiney", 1);
        registerTradingCard("stabby", 2);
        registerTradingCard("wet", 1);
    }

    public static void registerModModels(ItemModelGenerator itemModelGenerator) {
        for (List<Item> itemList : ALL_TRADING_CARDS.values()) {
            for (Item item : itemList) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        }
    }

    private static Item getRandomItem(List<Item> items) {
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }

    public static ItemStack getItemBasedRarity(Integer baseRarity) {
        int rando = (int)(Math.random() * 100);
        int extraRarity = 0;
        if (rando >= 99) {
            extraRarity = 4;
        } else if (rando >= 96) {
            extraRarity = 3;
        } else if (rando >= 90) {
            extraRarity = 2;
        } else if (rando >= 80) {
            extraRarity = 1;
        }
        Integer rarity = Math.max(extraRarity, baseRarity);

        Item item = getRandomItem(ALL_TRADING_CARDS.get(baseRarity));
        ItemStack stack = new ItemStack(item, 1);
        stack.set(ModDataComponentTypes.RARITY, rarity);

        return stack;
    }

    private static void registerTradingCard(String registerNameOffset, Integer minRarity) {
        Item tradingCard = registerItem("trading_card_" + registerNameOffset,
                new TradingCard(new Item.Settings().maxCount(16), capitalizeFirst(registerNameOffset)));

        ALL_TRADING_CARDS.computeIfAbsent(minRarity, k -> new ArrayList<>()).add(tradingCard);
    }

    public static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
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


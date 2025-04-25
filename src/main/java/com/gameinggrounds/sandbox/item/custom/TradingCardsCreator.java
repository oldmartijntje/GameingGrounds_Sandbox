package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.util.Models.TradingCardAbilities.TradingCardAbility;
import com.gameinggrounds.sandbox.util.Models.TradingCardAbilities.TradingCardHealAbility;
import com.gameinggrounds.sandbox.util.Models.TradingCardData;
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
    private static final int MAX_RARITY = 4;
    private static final Map<Integer, List<Item>> ALL_TRADING_CARDS = new HashMap<>();

    static {
        TradingCardAbility ABILITY_LESS = new TradingCardAbility();
        // Generate all trading cards at class load time
        registerTradingCard("steve", 0, ABILITY_LESS);
        registerTradingCard("alex", 1, ABILITY_LESS);
        registerTradingCard("bean", 0, ABILITY_LESS);
        registerTradingCard("duck", 0, ABILITY_LESS);
        registerTradingCard("heat", 3, ABILITY_LESS);
        registerTradingCard("life", 3, new TradingCardHealAbility());
        registerTradingCard("spiney", 1, ABILITY_LESS);
        registerTradingCard("stabby", 2, ABILITY_LESS);
        registerTradingCard("wet", 1, ABILITY_LESS);
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

        Item item = getRandomItem(ALL_TRADING_CARDS.get(rarity));
        ItemStack stack = new ItemStack(item, 1);
        TradingCardData data = new TradingCardData(false, rarity, 0);
        stack.set(ModDataComponentTypes.TRADING_CARD_DATA, data);

        return stack;
    }


    private static void registerTradingCard(String registerNameOffset, Integer minRarity, TradingCardAbility ability) {
        Item tradingCard = registerItem("trading_card_" + registerNameOffset,
                new TradingCard(new Item.Settings().maxCount(16), capitalizeFirst(registerNameOffset), ability));

        for (int rarity = minRarity; rarity <= MAX_RARITY; rarity++) {
            ALL_TRADING_CARDS.computeIfAbsent(rarity, k -> new ArrayList<>()).add(tradingCard);
        }
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


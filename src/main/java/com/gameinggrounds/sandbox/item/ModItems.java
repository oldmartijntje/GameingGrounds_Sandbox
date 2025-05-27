package com.gameinggrounds.sandbox.item;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.item.custom.*;
import com.gameinggrounds.sandbox.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item DRILL = registerItem("drill", new DrillItem(new Item.Settings().maxDamage(32)));

    public static final Item RED_SUGAR = registerItem("red_sugar", new Item(new Item.Settings().food(ModFoodComponent.RED_SUGAR)){
        // this apparently is an anonymous class
        // here you can expend the class

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.red_sugar"));
            super.appendTooltip(stack, context, tooltip, options);
        }
    });

    public static final Item COOL_HAMMER = registerItem("cool_hammer",
            new HammerItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.IRON, 7, -3.4f))));

    public static final Item WOOD_SLIME_SWORD = registerItem("wood_slime_sword",
            new SlimeSword(ToolMaterials.WOOD, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -2.4f)),
                    1.5F));

    public static final Item STONE_SLIME_SWORD = registerItem("stone_slime_sword",
            new SlimeSword(ToolMaterials.STONE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 0, -1.4f)),
                    2.1f));

    public static final Item IRON_SLIME_SWORD = registerItem("iron_slime_sword",
            new SlimeSword(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 0, -0.7f)),
                    3.3f));

    public static final Item GOLD_SLIME_SWORD = registerItem("gold_slime_sword",
            new SlimeSword(ToolMaterials.GOLD, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 0, -0.4f)),
                    3.5f));

    public static final Item DIAMOND_SLIME_SWORD = registerItem("diamond_slime_sword",
            new SlimeSword(ToolMaterials.DIAMOND, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 0, -0.2f)),
                    4.5f));

    public static final Item NETHERITE_SLIME_SWORD = registerItem("netherite_slime_sword",
            new SlimeSword(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 0, -0.0f)),
                    5.3f));

    public static final Item KITSUNE_MAISON_MUSIC_DISC = registerItem("kitsune_maison_music_disc",
            new Item( new Item.Settings().jukeboxPlayable(ModSounds.KITSUNE_MAISON_KEY).maxCount(1)));

    public static final Item QUESTIONABLE_SMITHING_TEMPLATE = registerItem("questionable_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(GameingGroundsSandbox.MOD_ID, "questionable"), FeatureFlags.VANILLA));

    public static final Item FURY_SMITHING_TEMPLATE = registerItem("fury_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(GameingGroundsSandbox.MOD_ID, "fury"), FeatureFlags.VANILLA));


    public static final Item TRADING_CARD_PACK = registerItem("trading_card_pack",
            new TradingCardPack( new Item.Settings().maxCount(32), 0));
    public static final Item GOLD_TRADING_CARD_PACK = registerItem("gold_trading_card_pack",
            new TradingCardPack( new Item.Settings().maxCount(32), 1));
    public static final Item DIAMOND_TRADING_CARD_PACK = registerItem("diamond_trading_card_pack",
            new TradingCardPack( new Item.Settings().maxCount(32), 2));
    public static final Item OBSIDIAN_TRADING_CARD_PACK = registerItem("obsidian_trading_card_pack",
            new TradingCardPack( new Item.Settings().maxCount(32), 3));

    public static final Item FRACTURED_PICKAXE = registerItem("fractured_pickaxe",
            new FracturedPickaxeItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 7, -0.1f))));

    public static final Item BROKEN_FRACTURED_PICKAXE = registerItem("broken_fractured_pickaxe",
            new Item(new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 7, -0.1f))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GameingGroundsSandbox.MOD_ID, name), item);
    }
    public static void registerModItems() {
        GameingGroundsSandbox.LOGGER.info("registering ModItems for " + GameingGroundsSandbox.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
    }
 }

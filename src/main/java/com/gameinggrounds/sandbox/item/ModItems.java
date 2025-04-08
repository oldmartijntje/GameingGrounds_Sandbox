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

    public static final Item COOL_HAMMER = registerItem("cool_hammer",
            new HammerItem(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.IRON, 7, -3.4f))));

    public static final Item WOOD_SLIME_SWORD = registerItem("wood_slime_sword",
            new WoodSlimeSword(ToolMaterials.WOOD, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -2.4f))));

    public static final Item STONE_SLIME_SWORD = registerItem("stone_slime_sword",
            new StoneSlimeSword(ToolMaterials.STONE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 0, -1.4f))));

    public static final Item IRON_SLIME_SWORD = registerItem("iron_slime_sword",
            new IronSlimeSword(ToolMaterials.IRON, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 0, -0.7f))));

    public static final Item GOLD_SLIME_SWORD = registerItem("gold_slime_sword",
            new GoldSlimeSword(ToolMaterials.GOLD, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 0, -0.4f))));

    public static final Item DIAMOND_SLIME_SWORD = registerItem("diamond_slime_sword",
            new DiamondSlimeSword(ToolMaterials.DIAMOND, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 0, -0.2f))));

    public static final Item NETHERITE_SLIME_SWORD = registerItem("netherite_slime_sword",
            new DiamondSlimeSword(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 0, -0.0f))));

    public static final Item KITSUNE_MAISON_MUSIC_DISC = registerItem("kitsune_maison_music_disc",
            new Item( new Item.Settings().jukeboxPlayable(ModSounds.KITSUNE_MAISON_KEY).maxCount(1)));

    public static final Item QUESTIONABLE_SMITHING_TEMPLATE = registerItem("questionable_armor_trim_smithing_template",
            SmithingTemplateItem.of(Identifier.of(GameingGroundsSandbox.MOD_ID, "questionable"), FeatureFlags.VANILLA));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GameingGroundsSandbox.MOD_ID, name), item);
    }
    public static void registerModItems() {
        GameingGroundsSandbox.LOGGER.info("registering ModItems for " + GameingGroundsSandbox.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
    }
 }

package com.gameinggrounds.sandbox.item;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.item.custom.DrillItem;
import com.gameinggrounds.sandbox.item.custom.HammerItem;
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

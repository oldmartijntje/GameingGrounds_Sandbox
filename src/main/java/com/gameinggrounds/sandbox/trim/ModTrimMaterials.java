package com.gameinggrounds.sandbox.trim;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ModTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> ECHO_SHARD = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "echo_shard"));
    public static final RegistryKey<ArmorTrimMaterial> SLIME_BALL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "slime_ball"));
    public static final RegistryKey<ArmorTrimMaterial> HONEYCOMB = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "honeycomb"));
    public static final RegistryKey<ArmorTrimMaterial> FIRE_CHARGE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "fire_charge"));
    public static final RegistryKey<ArmorTrimMaterial> ENDER_PEARL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "ender_pearl"));
    public static final RegistryKey<ArmorTrimMaterial> NETHER_STAR = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "nether_star"));
    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, ECHO_SHARD, Registries.ITEM.getEntry(Items.ECHO_SHARD),
                Style.EMPTY.withColor(TextColor.parse("#03404f").getOrThrow()), 1.0f);
        register(registerable, SLIME_BALL, Registries.ITEM.getEntry(Items.SLIME_BALL),
                Style.EMPTY.withColor(TextColor.parse("#84cd7a").getOrThrow()), 1.0f);
        register(registerable, HONEYCOMB, Registries.ITEM.getEntry(Items.HONEYCOMB),
                Style.EMPTY.withColor(TextColor.parse("#e78c16").getOrThrow()), 1.0f);
        register(registerable, FIRE_CHARGE, Registries.ITEM.getEntry(Items.FIRE_CHARGE),
                Style.EMPTY.withColor(TextColor.parse("#d89012").getOrThrow()), 1.0f);
        register(registerable, ENDER_PEARL, Registries.ITEM.getEntry(Items.ENDER_PEARL),
                Style.EMPTY.withColor(TextColor.parse("#032620").getOrThrow()), 1.0f);
        register(registerable, NETHER_STAR, Registries.ITEM.getEntry(Items.NETHER_STAR),
                Style.EMPTY.withColor(TextColor.parse("#d2d7dc").getOrThrow()), 1.0f);
    }

    private static void register(Registerable<ArmorTrimMaterial> registrable, RegistryKey<ArmorTrimMaterial> armorTrimKey,
                                 RegistryEntry<Item> item, Style style, float itemModelIndex) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, Map.of(),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registrable.register(armorTrimKey, trimMaterial);
    }
}

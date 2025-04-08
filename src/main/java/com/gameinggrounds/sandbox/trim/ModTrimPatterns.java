package com.gameinggrounds.sandbox.trim;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModTrimPatterns {
    public static final RegistryKey<ArmorTrimPattern> QUESTIONABLE = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "questionable"));

    public static final RegistryKey<ArmorTrimPattern> FURY = RegistryKey.of(RegistryKeys.TRIM_PATTERN,
            Identifier.of(GameingGroundsSandbox.MOD_ID, "fury"));

    public static void bootstrap(Registerable<ArmorTrimPattern> context) {
        register(context, ModItems.QUESTIONABLE_SMITHING_TEMPLATE, QUESTIONABLE);
        register(context, ModItems.FURY_SMITHING_TEMPLATE, FURY);
    }

    private static void register(Registerable<ArmorTrimPattern> context, Item item, RegistryKey<ArmorTrimPattern> key) {
        ArmorTrimPattern trimPattern = new ArmorTrimPattern(key.getValue(), Registries.ITEM.getEntry(item),
                Text.translatable(Util.createTranslationKey("trim_pattern", key.getValue())), false);

        context.register(key, trimPattern);
    }
}

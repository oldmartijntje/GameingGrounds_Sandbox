package com.gameinggrounds.sandbox.enchantment;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.enchantment.custom.LimboEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> LIMBO =
            registerEntityEffect("limbo", LimboEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(GameingGroundsSandbox.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        GameingGroundsSandbox.LOGGER.info("registering enchantment for " + GameingGroundsSandbox.MOD_ID);
    }
}

package com.gameinggrounds.sandbox.potion;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static final RegistryEntry<Potion> SPLEEF_POTION_WOOD = registerPotion("spleef_potion_wood",
            new Potion(new StatusEffectInstance(ModEffects.SPLEEF, 1200, 0)));
    public static final RegistryEntry<Potion> SPLEEF_POTION_STONE = registerPotion("spleef_potion_stone",
            new Potion(new StatusEffectInstance(ModEffects.SPLEEF, 1200, 3)));
    public static final RegistryEntry<Potion> SPLEEF_POTION_IRON = registerPotion("spleef_potion_iron",
            new Potion(new StatusEffectInstance(ModEffects.SPLEEF, 1200, 7)));
    public static final RegistryEntry<Potion> SPLEEF_POTION_DIAMOND = registerPotion("spleef_potion_diamond",
            new Potion(new StatusEffectInstance(ModEffects.SPLEEF, 1200, 11)));
    public static final RegistryEntry<Potion> SPLEEF_POTION_NETHERITE = registerPotion("spleef_potion_netherite",
            new Potion(new StatusEffectInstance(ModEffects.SPLEEF, 1200, 100)));
    public static final RegistryEntry<Potion> SPRINGY_POTION = registerPotion("springy_potion",
            new Potion(new StatusEffectInstance(ModEffects.SPRING, 1200, 0)));
    public static final RegistryEntry<Potion> SPRINGY_POTION_LV2 = registerPotion("springy_potion_lv2",
            new Potion(new StatusEffectInstance(ModEffects.SPRING, 1200, 1)));
    public static final RegistryEntry<Potion> SPRINGY_POTION_LV3 = registerPotion("springy_potion_lv3",
            new Potion(new StatusEffectInstance(ModEffects.SPRING, 1200, 3)));
    public static final RegistryEntry<Potion> SPRINGY_POTION_LV4 = registerPotion("springy_potion_lv4",
            new Potion(new StatusEffectInstance(ModEffects.SPRING, 1200, 6)));
    public static final RegistryEntry<Potion> SPRINGY_POTION_LV5 = registerPotion("springy_potion_lv5",
            new Potion(new StatusEffectInstance(ModEffects.SPRING, 1200, 10)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(GameingGroundsSandbox.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        GameingGroundsSandbox.LOGGER.info("Registering Mod Potions for "+ GameingGroundsSandbox.MOD_ID);
    }
}

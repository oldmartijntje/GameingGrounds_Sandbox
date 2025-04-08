package com.gameinggrounds.sandbox.effect;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SPLEEF = registerStatusEffect("spleef",
            new SpleefEffect(StatusEffectCategory.NEUTRAL, 0xA1A8A8)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(GameingGroundsSandbox.MOD_ID, "spleef"), 0,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(GameingGroundsSandbox.MOD_ID, name), statusEffect);
    }
    public static void registerEffects() {
        GameingGroundsSandbox.LOGGER.info("registering Mod Effects for " + GameingGroundsSandbox.MOD_ID);
    }
}

package com.gameinggrounds.sandbox.effect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// MIT License!
public class SpleefEffect extends StatusEffect {
    public SpleefEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld() instanceof ServerWorld serverWorld) {
            BlockPos beneathPos = entity.getBlockPos().down();
            BlockState beneathBlock = serverWorld.getBlockState(beneathPos);

            if (!beneathBlock.isAir()) {
                serverWorld.breakBlock(beneathPos, true);
                boolean limitFound = false;
                for (int i = 0; i < Math.floor(amplifier / 5f); i++) {
                    beneathPos = beneathPos.down();
                    beneathBlock = serverWorld.getBlockState(beneathPos);
                    if (!beneathBlock.isAir() && !limitFound) {
                        serverWorld.breakBlock(beneathPos, true);
                    } else {
                        limitFound = true;
                    }
                }
            }
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
//        amplifier = 1;
        int safeAmplifier = Math.max(amplifier, 1);
        return duration % Math.max(1, Math.round(50f / (safeAmplifier + 1))) == 0;
    }
}
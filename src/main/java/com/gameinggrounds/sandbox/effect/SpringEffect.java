package com.gameinggrounds.sandbox.effect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

// Climbing Effect by SameDifferent: https://github.com/samedifferent/TrickOrTreat/blob/master/LICENSE
// MIT License!
public class SpringEffect extends StatusEffect {
    public SpringEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld() instanceof ServerWorld serverWorld) {
            entity.fallDistance = 0.0F;
            BlockPos entityPos = entity.getBlockPos();
            BlockPos headPos = entity.getBlockPos().up((int) entity.getEyeHeight(EntityPose.STANDING) + 1);
            BlockState blockAbove = serverWorld.getBlockState(headPos);

            if (entity.isOnGround()) {
                serverWorld.playSound(null, entityPos, SoundEvents.ENTITY_BEE_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.addVelocity(0, (amplifier /2F) + 0.2F, 0);
                entity.velocityModified = true;
            } else if (!blockAbove.isAir()) {
                serverWorld.playSound(null, entityPos, SoundEvents.ENTITY_BEE_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.addVelocity(0, -(((amplifier / 2F) + 0.2F) + entity.getVelocity().y), 0);
                entity.velocityModified = true;
            }
        }

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
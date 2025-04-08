package com.gameinggrounds.sandbox.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class GoldSlimeSword extends SwordItem {
    public GoldSlimeSword(ToolMaterial material, Settings settings) {
        super(material, settings);
        settings.maxDamage(0);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Vec3d look = attacker.getRotationVec(1.0F);
        float knockbackStrength = 3.5F;
        target.takeKnockback(knockbackStrength, -look.x, -look.z);
        attacker.getWorld()
                .playSound(
                        null,
                        target.getZ(), target.getY(), target.getZ(),
                        SoundEvents.BLOCK_SLIME_BLOCK_HIT,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F
                );

        return true;
    }
}
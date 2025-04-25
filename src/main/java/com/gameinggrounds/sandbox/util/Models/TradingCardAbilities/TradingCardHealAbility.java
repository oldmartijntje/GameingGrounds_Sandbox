package com.gameinggrounds.sandbox.util.Models.TradingCardAbilities;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.util.dto.AbilityTimeObject;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TradingCardHealAbility extends TradingCardAbility {

    @Override
    public TypedActionResult<ItemStack> triggerAbility(World world, PlayerEntity user, Hand hand) {
        int gememode = 0;
        if (user.isCreative()) {
            gememode = 1;
        }
        ItemStack stack = user.getStackInHand(hand);
        if (this.haveTicksPassed(stack, world, this.cooldown(), gememode)) {
            if (world.isClient()) {
                BlockPos position = user.getBlockPos();
                summonHeartParticles(world, position, stack.getCount(), 0, 1, 0);
            } else {
                user.heal(stack.getCount());
                this.hasUsedAbility(stack, world);
            }
            return TypedActionResult.consume(user.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    @Override
    public Long cooldown() {
        return MINUTE * 15L;
    }

    private void summonHeartParticles(World world, BlockPos position, int amount, double velocityX, double velocityY, double velocityZ) {
        int loop1 = 3;
        int loop2 = 3;
        int skipWhenBoth = 1;

        for (int o = 0; o < amount; o++) {
            for (int i = 0; i < loop1; i++) {
                for (int j = 0; j < loop2; j++) {
                    if (i == j && i == skipWhenBoth) {
                        continue;
                    }
                    double offsetX = i - 0.5 + Math.random() / 2 - 0.25;
                    double offsetZ = j - 0.5 + Math.random() / 2 - 0.25;
                    double offsetY = 0 + ((double) amount / 100);

                    double x = position.getX() + offsetX;
                    double y = position.getY() + 0 + offsetY;
                    double z = position.getZ() + offsetZ;

                    world.addParticle(ParticleTypes.HEART, x, y, z, velocityX, velocityY, velocityZ);
                }
            }
        }
    }

    @Override
    public boolean showAbillityTooltip() {
        return true;
    }

    @Override
    public AbilityTimeObject getAbilityCooldown(World world, ItemStack stack) {
        Long timeSince = getTimeSince(stack, world);
        Long cooldown = (long) (this.cooldown());
        return new AbilityTimeObject(timeSince, cooldown, haveTicksPassed(stack, world, cooldown));

    }

    @Override
    public List<Text> getAbilityDescription() {
        List<Text> description = new ArrayList<>();
        description.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.ability.tooltip.heal.1"));
        description.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.ability.tooltip.heal.2"));
        return description;
    }
}

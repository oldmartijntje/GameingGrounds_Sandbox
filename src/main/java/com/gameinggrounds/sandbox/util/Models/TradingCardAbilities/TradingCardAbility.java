package com.gameinggrounds.sandbox.util.Models.TradingCardAbilities;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.util.dto.AbilityTimeObject;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TradingCardAbility {
    public static final Long MINUTE = 1200L;

    public Long cooldown() {
        return null;
    }
    public TypedActionResult<ItemStack> triggerAbility(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.fail(user.getStackInHand(hand));
    }
    public AbilityTimeObject getAbilityCooldown(World world, ItemStack stack) {
        return new AbilityTimeObject(null, null, false);
    }

    public boolean haveTicksPassed(ItemStack stack, World world, Long ticks) {
        if (stack.get(ModDataComponentTypes.LAST_CARD_USE) != null) {
            Long cooldown = stack.get(ModDataComponentTypes.LAST_CARD_USE);
            long now = world.getTime();
            return cooldown == null || now - cooldown >= ticks;
        } else {
            return true;
        }
    }

    public boolean showAbillityTooltip() {
        return false;
    }

    public List<Text> getAbilityDescription() {
        return new ArrayList<>();
    }

    public Long getTimeSince(ItemStack stack, World world) {
        if (stack.get(ModDataComponentTypes.LAST_CARD_USE) != null) {
            Long cooldown = stack.get(ModDataComponentTypes.LAST_CARD_USE);
            long now = world.getTime();
            return now - cooldown;
        } else {
            return null;
        }

    }

    public void hasUsedAbility(ItemStack stack, World world) {
        long now = world.getTime();
        stack.set(ModDataComponentTypes.LAST_CARD_USE, now);
    }
}

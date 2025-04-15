package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.sun.jna.WString;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class TradingCard extends Item {
    public TradingCard(Settings settings) {
        super(settings);
    }

    private Formatting getRarityFormatting(Integer rarity) {
        if (rarity == null) {
            return Formatting.WHITE; // Default color
        }
        return switch (rarity) {
            case 0 -> Formatting.GRAY;     // Common
            case 1 -> Formatting.GREEN;     // Uncommon
            case 2 -> Formatting.BLUE;      // Rare
            case 3 -> Formatting.DARK_PURPLE; // Epic
            case 4 -> Formatting.GOLD;      // Legendary
            default -> Formatting.WHITE;     // Fallback
        };
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.get(ModDataComponentTypes.RARITY) != null) {
            Integer rarity = stack.get(ModDataComponentTypes.RARITY);
             if (rarity != null) {
                Formatting color = getRarityFormatting(rarity);
                return Text.translatable("item.gameinggrounds-sandbox.trading_card").styled(style -> style.withColor(color));
            }
        }
        return Text.translatable("item.gameinggrounds-sandbox.trading_card");
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill"));
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill.shift_down"));
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill.shift_down.1"));
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill.shift_down.2"));

        } else {
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill"));
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.drill.1"));
        }

        if (stack.get(ModDataComponentTypes.RARITY) != null) {
            tooltip.add(Text.literal("Last Block Used at "));
        }

        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            // cooldown etc
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}

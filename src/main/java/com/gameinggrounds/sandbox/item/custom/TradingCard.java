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
    private String cardName = "";
    public TradingCard(Settings settings, String cardName) {
        super(settings);
        this.cardName = cardName;
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
        tooltip.add(Text.literal(String.format("%s - ", this.cardName)).append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card")));
        if (Screen.hasShiftDown()) {
            if (stack.get(ModDataComponentTypes.RARITY) != null) {
                Integer rarity = stack.get(ModDataComponentTypes.RARITY);
                Formatting color = getRarityFormatting(rarity);
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity").append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity." + rarity).styled(style -> style.withColor(color))));
            } else {
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity.none").styled(style -> style.withColor(Formatting.RED)));
            }
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.1"));
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.2"));

            if (stack.get(ModDataComponentTypes.DISCOVERED_BY) != null) {
                String playerName = stack.get(ModDataComponentTypes.DISCOVERED_BY).playerName();
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.owner").append(String.format("§5%s", playerName)));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.1"));
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

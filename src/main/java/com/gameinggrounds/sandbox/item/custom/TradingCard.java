package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

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

    private Formatting getDamageFormatting(Integer damage) {
        if (damage == null) {
            return Formatting.RED; // Default color
        }
        return switch (damage) {
            case 0 -> Formatting.GREEN;     // Mint
            case 1 -> Formatting.AQUA;      // Near-Mint
            case 2 -> Formatting.YELLOW;    // Played
            case 3 -> Formatting.GOLD;      // Worn
            case 4 -> Formatting.RED;       // Damaged
            default -> Formatting.RED;    // Fallback
        };
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.get(ModDataComponentTypes.TRADING_CARD_DATA) != null) {
            Integer rarity = stack.get(ModDataComponentTypes.TRADING_CARD_DATA).rarity();
             if (rarity != null) {
                Formatting color = getRarityFormatting(rarity);
                return Text.translatable("item.gameinggrounds-sandbox.trading_card").styled(style -> style.withColor(color));
            }
        }
        return Text.translatable("item.gameinggrounds-sandbox.trading_card");
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.literal(String.format("%s - ", this.cardName))
                .styled(style -> style.withColor(Formatting.DARK_GRAY))
                .append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card")
                        .styled(style -> style.withColor(Formatting.DARK_GRAY))));
        if (Screen.hasShiftDown()) {
            if (stack.get(ModDataComponentTypes.TRADING_CARD_DATA) != null) {
                Integer rarity = stack.get(ModDataComponentTypes.TRADING_CARD_DATA).rarity();
                Formatting rarityColor = getRarityFormatting(rarity);
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity")
                        .append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity." + rarity)
                                .styled(style -> style.withColor(rarityColor))));

                Integer quality = stack.get(ModDataComponentTypes.TRADING_CARD_DATA).condition();
                Formatting conditionColor = getDamageFormatting(quality);
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.condition")
                        .append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.condition." + quality)
                                .styled(style -> style.withColor(conditionColor))));
                Boolean hasSleeves = stack.get(ModDataComponentTypes.TRADING_CARD_DATA).has_card_sleeve();
                if (hasSleeves) {
                    tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.sleeve.has"));
                } else {
                    tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.sleeve.none"));
                }
            } else {
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.rarity.none")
                        .styled(style -> style.withColor(Formatting.RED)));
            }
//            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.1"));
//            tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.2"));

            if (stack.get(ModDataComponentTypes.DISCOVERED_BY) != null) {
                String playerName = stack.get(ModDataComponentTypes.DISCOVERED_BY).playerName();
                tooltip.add(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.shift_down.owner")
                        .append(String.format("§5%s", playerName)));
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

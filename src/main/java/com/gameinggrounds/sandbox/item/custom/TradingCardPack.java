package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.util.Models.PlayerIdentity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class TradingCardPack extends Item {
    public int rarityBossted;
    public TradingCardPack(Settings settings, int rarityBossted) {
        super(settings);
        this.rarityBossted = rarityBossted;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.gameinggrounds-sandbox.trading_card_pack");
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType options) {

        if (this.rarityBossted > 0) {
            tooltip.add(
                    Text.translatable("tooltip.gameinggrounds-sandbox.trading_card_pack.lucky")
                            .append(Text.literal(this.getRomanNumeral(this.rarityBossted))
                    ).styled(style -> style.withColor(Formatting.GRAY))
                );
        }
        super.appendTooltip(stack, context, tooltip, options);
    }

    private String getRomanNumeral(Integer number) {
        if (number == null || number > 10) {
            return Integer.toString(number); // Default color
        }
        switch (number) {
            case 1 -> {return "I";}
            case 2 -> {return "II";}
            case 3 -> {return "III";}
            case 4 -> {return "IV";}
            case 5 -> {return "V";}
            case 6 -> {return "VI";}
            case 7 -> {return "VII";}
            case 8 -> {return "VIII";}
            case 9 -> {return "IX";}
            case 10 -> {return "X";}
            default -> {return Integer.toString(number);}
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (!user.isCreative()) {
                ItemStack heldStack = user.getMainHandStack();
                heldStack.setCount(heldStack.getCount() - 1);
            }
            List<Integer> cardPackCards = Arrays.asList(0,0,0,0,1,1,1,2);
            if (this.rarityBossted > 0) {
                cardPackCards = Arrays.asList(0,0,this.rarityBossted -1,this.rarityBossted - 1,this.rarityBossted -1,this.rarityBossted,this.rarityBossted + 1,this.rarityBossted + 2);
            }
            for (int i = 0; i < cardPackCards.size(); i++) {
                ItemStack stack = TradingCardsCreator.getItemBasedRarity(cardPackCards.get(i));
                if (stack != null) {
                    PlayerIdentity playerIdentity = new PlayerIdentity(user.getGameProfile().getId(), user.getGameProfile().getName());
                    stack.set(ModDataComponentTypes.DISCOVERED_BY, playerIdentity);
                    if (!user.getInventory().insertStack(stack)) {
                        // If inventory is full, drop the item at the player's location
                        user.dropItem(stack, false);
                    }
                }
            }
        }
        return super.use(world, user, hand);
    }
}

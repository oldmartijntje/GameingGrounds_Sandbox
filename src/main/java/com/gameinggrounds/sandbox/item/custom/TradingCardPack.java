package com.gameinggrounds.sandbox.item.custom;

import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.util.Models.PlayerIdentity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TradingCardPack extends Item {
    public TradingCardPack(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            List<Integer> cardPackCards = Arrays.asList(0,0,0,0,1,1,1,2);
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

package com.gameinggrounds.sandbox.util;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.item.custom.FracturedPickaxeItem;
import com.gameinggrounds.sandbox.item.custom.HammerItem;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FracturedPickaxeUsageEvent implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = player.getMainHandStack();

        Integer value = mainHandItem.get(ModDataComponentTypes.COUNTER);
        int counter = (value != null) ? value : 0;
        counter++;
        mainHandItem.set(ModDataComponentTypes.COUNTER, counter);
        if (mainHandItem.get(ModDataComponentTypes.MAXVALUE) == null){
            int randomNum = new Random().nextInt(100) + 1;
            System.out.println("Setting MAXVALUE to: " + randomNum);
            mainHandItem.set(ModDataComponentTypes.MAXVALUE, randomNum);
        } else {
            System.out.println("MAXVALUE = " + mainHandItem.get(ModDataComponentTypes.MAXVALUE));
        }

        if (mainHandItem.get(ModDataComponentTypes.MAXVALUE) != null){
            if (counter >= mainHandItem.get(ModDataComponentTypes.MAXVALUE)){
                mainHandItem.set(ModDataComponentTypes.TRIGGEREVENT, true);
            }
        }

        if(mainHandItem.getItem() instanceof FracturedPickaxeItem && player instanceof ServerPlayerEntity serverPlayer) {
            if(HARVESTED_BLOCKS.contains(pos)) {
                return true;
            }

            if(FracturedPickaxeItem.will_explode(mainHandItem)){
                BlockPos blockPos = serverPlayer.getBlockPos();
                serverPlayer.getServerWorld().createExplosion(
                    serverPlayer,
                    blockPos.getX(),
                    blockPos.getY(),
                    blockPos.getZ(),
                    20.0f,
                    true,
                    World.ExplosionSourceType.MOB
                );
                ItemStack droppedItem = new ItemStack(state.getBlock().asItem(), 5);
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), droppedItem);
                world.spawnEntity(itemEntity);
            } else {
                ItemStack droppedItem = new ItemStack(state.getBlock().asItem());
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), droppedItem);
                world.spawnEntity(itemEntity);
                BlockPos blockPos = serverPlayer.getBlockPos();
                HARVESTED_BLOCKS.add(pos);
            }
        }

        return true;
    }
}

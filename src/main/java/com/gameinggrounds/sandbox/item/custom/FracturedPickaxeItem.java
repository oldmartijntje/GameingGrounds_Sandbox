package com.gameinggrounds.sandbox.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import net.minecraft.world.World;

import java.util.Random;

public class FracturedPickaxeItem extends MiningToolItem {
    public FracturedPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    public static boolean will_explode(ItemStack stack){
        Integer counter = stack.get(ModDataComponentTypes.COUNTER);
        Integer max = stack.get(ModDataComponentTypes.MAXVALUE);

        if (counter != null && max != null && counter >= max) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (!world.isClient()) {
            if (clickedBlock == Blocks.ICE || clickedBlock == Blocks.PACKED_ICE || clickedBlock ==Blocks.BLUE_ICE || clickedBlock == Blocks.FROSTED_ICE || clickedBlock == Blocks.SNOW_BLOCK || clickedBlock == Blocks.POWDER_SNOW) {
                world.setBlockState(context.getBlockPos(), Blocks.AIR.getDefaultState());
                context.getStack().set(ModDataComponentTypes.COUNTER, 0);
                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS);
            } else if (clickedBlock == Blocks.FIRE || clickedBlock == Blocks.LAVA) {
                world.setBlockState(context.getBlockPos(), Blocks.AIR.getDefaultState());
                context.getStack().set(ModDataComponentTypes.COUNTER, 253);
                world.playSound(null, context.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
            }
        }

        return ActionResult.SUCCESS;
    }
}
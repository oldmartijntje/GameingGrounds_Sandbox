package com.gameinggrounds.sandbox.item.custom;

import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import java.util.Random;

public class FracturedPickaxeItem extends MiningToolItem {
    public FracturedPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }
    public static boolean will_explode(BlockPos initalBlockPos, ServerPlayerEntity player){
        int randomNum = new Random().nextInt(10);
        return randomNum == 1;
    }
}
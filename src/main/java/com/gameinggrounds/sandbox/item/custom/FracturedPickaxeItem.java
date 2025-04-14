package com.gameinggrounds.sandbox.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import java.util.Random;

public class FracturedPickaxeItem extends MiningToolItem {
    public FracturedPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Integer value = context.getStack().get(ModDataComponentTypes.COUNTER);
        int counter = (value != null) ? value : 0;
        counter++;
        context.getStack().set(ModDataComponentTypes.COUNTER, counter);
        if (context.getStack().get(ModDataComponentTypes.MAXVALUE) == null){
            int randomNum = new Random().nextInt(10);
            context.getStack().set(ModDataComponentTypes.MAXVALUE, randomNum);
        }

        if (context.getStack().get(ModDataComponentTypes.MAXVALUE) != null){
            if (counter >= context.getStack().get(ModDataComponentTypes.MAXVALUE)){
                context.getStack().set(ModDataComponentTypes.TRIGGEREVENT, true);
            }
        }
        return super.useOnBlock(context);
    }

    public static boolean will_explode(ItemStack stack){
        Integer counter = stack.get(ModDataComponentTypes.COUNTER);
        Integer max = stack.get(ModDataComponentTypes.MAXVALUE);

        if (counter != null && max != null && counter >= max) {
            System.out.println("BOOOOOOOM");

            return true;
        } else {
            System.out.println("Nein");
            return false;
        }
    }
}
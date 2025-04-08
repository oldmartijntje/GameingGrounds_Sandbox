package com.gameinggrounds.sandbox.datagen;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DRILL, 1)
                .pattern(" T ")
                .pattern("TSL")
                .pattern("POD")
                .input('T', Blocks.TNT)
                .input('S', Blocks.SMOOTH_STONE)
                .input('L', Blocks.LEVER)
                .input('P', Blocks.PISTON)
                .input('O', Blocks.OBSIDIAN)
                .input('D', Blocks.OAK_TRAPDOOR)
                .criterion(hasItem(Blocks.OBSIDIAN), conditionsFromItem(Blocks.OBSIDIAN))
                .offerTo(recipeExporter);

        offerSmithingTrimRecipe(recipeExporter, ModItems.QUESTIONABLE_SMITHING_TEMPLATE, Identifier.of(GameingGroundsSandbox.MOD_ID, "questionable"));
        offerSmithingTrimRecipe(recipeExporter, ModItems.FURY_SMITHING_TEMPLATE, Identifier.of(GameingGroundsSandbox.MOD_ID, "fury"));
    }
}

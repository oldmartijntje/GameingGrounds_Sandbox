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

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WOOD_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.WOODEN_SWORD)
                        .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STONE_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.STONE_SWORD)
                        .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.IRON_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.IRON_SWORD)
                        .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.GOLD_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.GOLDEN_SWORD)
                        .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.DIAMOND_SWORD)
                        .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.NETHERITE_SLIME_SWORD, 1)
                        .pattern("SSS")
                        .pattern("SIS")
                        .pattern("SSS")
                        .input('S', Items.SLIME_BALL)
                        .input('I', Items.NETHERITE_SWORD)
                        .offerTo(recipeExporter);
        offerNetheriteUpgradeRecipe(recipeExporter, ModItems.DIAMOND_SLIME_SWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_SLIME_SWORD);

        offerSmithingTrimRecipe(recipeExporter, ModItems.QUESTIONABLE_SMITHING_TEMPLATE, Identifier.of(GameingGroundsSandbox.MOD_ID, "questionable"));
        offerSmithingTrimRecipe(recipeExporter, ModItems.FURY_SMITHING_TEMPLATE, Identifier.of(GameingGroundsSandbox.MOD_ID, "fury"));
    }
}

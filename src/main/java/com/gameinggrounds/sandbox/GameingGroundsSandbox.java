package com.gameinggrounds.sandbox;

import com.gameinggrounds.sandbox.block.ModBlocks;
import com.gameinggrounds.sandbox.component.ModDataComponentTypes;
import com.gameinggrounds.sandbox.effect.ModEffects;
import com.gameinggrounds.sandbox.enchantment.ModEnchantmentEffects;
import com.gameinggrounds.sandbox.item.ModItemGroups;
import com.gameinggrounds.sandbox.item.ModItems;
import com.gameinggrounds.sandbox.potion.ModPotions;
import com.gameinggrounds.sandbox.util.HammerUsageEvent;
import com.gameinggrounds.sandbox.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameingGroundsSandbox implements ModInitializer {
	public static final String MOD_ID = "gameinggrounds-sandbox";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModEffects.registerEffects();
		ModPotions.registerPotions();

		ModEnchantmentEffects.registerEnchantmentEffects();
		ModWorldGeneration.generateModWorldGen();

		ModDataComponentTypes.registerDataComponentTypes();

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
			if(entity instanceof SheepEntity sheepEntity && !world.isClient()) {
				if (playerEntity.getMainHandStack().getItem() == Items.END_ROD) {
					playerEntity.sendMessage(Text.literal("Bababooi!"));
					playerEntity.getMainHandStack().decrement(1);
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 6));
				}
			}
			return ActionResult.PASS;
		});

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.WOODEN_SHOVEL, ModPotions.SPLEEF_POTION_WOOD);
			builder.registerPotionRecipe(Potions.AWKWARD, Items.STONE_SHOVEL, ModPotions.SPLEEF_POTION_STONE);
			builder.registerPotionRecipe(Potions.AWKWARD, Items.IRON_SHOVEL, ModPotions.SPLEEF_POTION_IRON);
			builder.registerPotionRecipe(Potions.AWKWARD, Items.DIAMOND_SHOVEL, ModPotions.SPLEEF_POTION_DIAMOND);
			builder.registerPotionRecipe(Potions.AWKWARD, Items.NETHERITE_SHOVEL, ModPotions.SPLEEF_POTION_NETHERITE);
			builder.registerPotionRecipe(Potions.AWKWARD, Items.RED_MUSHROOM, ModPotions.SPRINGY_POTION);
		});
	}
}
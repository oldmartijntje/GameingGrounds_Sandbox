package com.gameinggrounds.sandbox.component;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final Codec<Integer> INT_CODEC = Codec.INT;
    public static final Codec<String> STRING_CODEC = Codec.STRING;
    public static final Codec<Boolean> BOOLEAN = Codec.BOOL;
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<Integer> COUNTER = register("counter", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> RARITY = register("rarity", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> COOLDOWN = register("cooldown", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> MAXVALUE = register("maxvalue", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Boolean> TRIGGEREVENT = register("triggerevent", builder -> builder.codec(BOOLEAN));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(GameingGroundsSandbox.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        GameingGroundsSandbox.LOGGER.info("Registering Data Component Types for " + GameingGroundsSandbox.MOD_ID);
    }
}

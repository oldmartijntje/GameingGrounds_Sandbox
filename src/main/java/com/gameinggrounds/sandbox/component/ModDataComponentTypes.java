package com.gameinggrounds.sandbox.component;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import com.gameinggrounds.sandbox.util.Models.PlayerIdentity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final Codec<Integer> INT_CODEC = Codec.INT;
    public static final Codec<String> STRING_CODEC = Codec.STRING;
    public static final Codec<PlayerIdentity> PLAYER_IDENTITY_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.xmap(UUID::fromString, UUID::toString).fieldOf("uuid").forGetter(PlayerIdentity::uuid),
                    Codec.STRING.fieldOf("playerName").forGetter(PlayerIdentity::playerName)
            ).apply(instance, PlayerIdentity::new)
    );
    public static final Codec<Boolean> BOOLEAN = Codec.BOOL;
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<Integer> COUNTER = register("counter", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> RARITY = register("rarity", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> LAST_CARD_USE = register("last_card_use", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<Integer> MAXVALUE = register("maxvalue", builder -> builder.codec(INT_CODEC));
    public static final ComponentType<PlayerIdentity> DISCOVERED_BY = register("discovered_by", builder -> builder.codec(PLAYER_IDENTITY_CODEC));
    public static final ComponentType<Boolean> TRIGGEREVENT = register("triggerevent", builder -> builder.codec(BOOLEAN));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(GameingGroundsSandbox.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        GameingGroundsSandbox.LOGGER.info("Registering Data Component Types for " + GameingGroundsSandbox.MOD_ID);
    }
}

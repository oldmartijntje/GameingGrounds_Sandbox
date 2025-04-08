package com.gameinggrounds.sandbox.sound;

import com.gameinggrounds.sandbox.GameingGroundsSandbox;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent KITSUNE_MAISON = registerSoundEvent("kitsune_maison");
    public static final RegistryKey<JukeboxSong> KITSUNE_MAISON_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(GameingGroundsSandbox.MOD_ID, "kitsune_maison"));

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(GameingGroundsSandbox.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        GameingGroundsSandbox.LOGGER.info("Registering Mod Sounds for " + GameingGroundsSandbox.MOD_ID);
    }
}

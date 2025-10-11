package net.jolene.ninetofiveessentials.sound;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent PUFF = registerSoundEvent("puff");
    public static final SoundEvent FIZZLE = registerSoundEvent("fizzle");
    public static final SoundEvent RESULT= registerSoundEvent("result");
    public static final SoundEvent WHEEL = registerSoundEvent("wheel");
    public static final SoundEvent COUGH = registerSoundEvent("cough");
    public static final SoundEvent DISC = registerSoundEvent("disc");

    public static final SoundEvent DARK_IS_THE_NIGHT = registerSoundEvent("dark_is_the_night");
public static final RegistryKey<JukeboxSong> DARK_IS_THE_NIGHT_KEY =
        RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(NineToFiveEssentials.MOD_ID, "dark_is_the_night"));

    public static final SoundEvent CAN_CAN = registerSoundEvent("can_can");
    public static final RegistryKey<JukeboxSong> CAN_CAN_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(NineToFiveEssentials.MOD_ID, "can_can"));


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(NineToFiveEssentials.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        NineToFiveEssentials.LOGGER.info("Registering Sounds for " + NineToFiveEssentials.MOD_ID);
    }
}

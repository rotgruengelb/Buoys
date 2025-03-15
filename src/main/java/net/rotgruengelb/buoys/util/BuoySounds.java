package net.rotgruengelb.buoys.util;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.rotgruengelb.buoys.Buoys;

public class BuoySounds {

	public static final SoundEvent BUOY_BLOCK_BREAK = registerSoundEvent("buoy_block_break");
	public static final SoundEvent BUOY_BLOCK_STEP = registerSoundEvent("buoy_block_step");
	public static final SoundEvent BUOY_BLOCK_PLACE = registerSoundEvent("buoy_block_place");
	public static final SoundEvent BUOY_BLOCK_HIT = registerSoundEvent("buoy_block_hit");
	public static final SoundEvent BUOY_BLOCK_FALL = registerSoundEvent("buoy_block_fall");

	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = Buoys.id( name);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}

	public static class ModSoundGroups {
		public static final BlockSoundGroup BUOY_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f, BUOY_BLOCK_BREAK, BUOY_BLOCK_STEP, BUOY_BLOCK_PLACE, BUOY_BLOCK_HIT, BUOY_BLOCK_FALL);
	}
}

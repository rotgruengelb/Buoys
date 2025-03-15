package net.rotgruengelb.buoys.util;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public class BuoyProperties {

	public static final EnumProperty<ChainBuoyState> CHAIN_BUOY_STATE = EnumProperty.of("chain_buoy_state", ChainBuoyState.class);

	public enum ChainBuoyState implements StringIdentifiable {
		UPPER,
		LOWER,
		LOWER_CHAIN_N,
		LOWER_CHAIN_E,
		LOWER_CHAIN_SE,
		LOWER_CHAIN_SW;

		private final String name;

		ChainBuoyState() {
			this.name = this.name().toLowerCase();
		}

		public boolean isLower() {
			return this != UPPER;
		}

		@Override
		public String asString() {
			return name;
		}
	}
}

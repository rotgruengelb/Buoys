package net.rotgruengelb.buoys;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.rotgruengelb.buoys.block.BuoyBlocks;
import net.rotgruengelb.buoys.util.BuoyTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Buoys implements ModInitializer {
    public static final String VERSION = /*$ mod_version*/ "0.1.0";
    public static final String MOD_ID = "buoys";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BuoyBlocks.registerModBlocks();
        BuoyTags.registerModTags();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
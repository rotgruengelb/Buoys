package net.rotgruengelb.buoys.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
	protected ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, registryLookup);
	}

	public static String convertSnakeCaseToTitleCase(String snakeCaseText) {
		String[] words = snakeCaseText.split("_");
		StringBuilder titleCaseText = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				titleCaseText.append(Character.toUpperCase(word.charAt(0)))
						.append(word.substring(1)
								.toLowerCase())
						.append(" ");
			}
		}
		return titleCaseText.toString()
				.trim();
	}

	@Override
	public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
		for (Block block : BuoyDataGenerator.combineLists(BuoyDataGenerator.BUOYS, BuoyDataGenerator.CHAIN_BUOYS)) {
			translationBuilder.add(block.getTranslationKey(), convertSnakeCaseToTitleCase(Registries.BLOCK.getId(block)
					.getPath()));
		}

		for (TagKey<?> tag : BuoyDataGenerator.TAGS) {
			translationBuilder.add(tag.getTranslationKey(), convertSnakeCaseToTitleCase(tag.id()
					.getPath()));
		}
	}
}

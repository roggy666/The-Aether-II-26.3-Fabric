package com.aetherteam.aetherii.data.generators.models;

import com.aetherteam.aetherii.block.AetherIIBlocks;
import com.aetherteam.aetherii.data.providers.AetherIIItemModelSubProvider;
import com.aetherteam.aetherii.item.AetherIIItems;
import com.aetherteam.aetherii.item.components.ReinforcementTier;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.resources.Identifier;

import java.util.function.BiConsumer;

public class AetherIIItemModels extends AetherIIItemModelSubProvider {
    public AetherIIItemModels(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        // Tools
        this.generateReinforcedItem(AetherIIItems.SKYROOT_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.SKYROOT_AXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.SKYROOT_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.SKYROOT_TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);

        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);

        this.generateReinforcedItem(AetherIIItems.ZANITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ZANITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ZANITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ZANITE_TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);

        this.generateReinforcedItem(AetherIIItems.ARKENIUM_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateReinforcedItem(AetherIIItems.ARKENIUM_AXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateReinforcedItem(AetherIIItems.ARKENIUM_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateReinforcedItem(AetherIIItems.ARKENIUM_TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);

        this.generateReinforcedItem(AetherIIItems.GRAVITITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.GRAVITITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.GRAVITITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.GRAVITITE_TROWEL, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);

        this.generateFlatItem(AetherIIItems.ZANITE_SHEARS, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Combat
        this.generateReinforcedItem(AetherIIItems.SKYROOT_SHORTSWORD, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.SKYROOT_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.SKYROOT_PIKE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateAetherCrossbow(AetherIIItems.SKYROOT_CROSSBOW);

        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_SHORTSWORD, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.HOLYSTONE_PIKE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateAetherCrossbow(AetherIIItems.HOLYSTONE_CROSSBOW);

        this.generateReinforcedItem(AetherIIItems.ZANITE_SHORTSWORD, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ZANITE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ZANITE_PIKE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateAetherCrossbow(AetherIIItems.ZANITE_CROSSBOW);

        this.generateReinforcedItem(AetherIIItems.ARKENIUM_SHORTSWORD, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateReinforcedItem(AetherIIItems.ARKENIUM_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateReinforcedItem(AetherIIItems.ARKENIUM_PIKE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.FOURTH);
        this.generateAetherCrossbow(AetherIIItems.ARKENIUM_CROSSBOW);

        this.generateReinforcedItem(AetherIIItems.GRAVITITE_SHORTSWORD, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.GRAVITITE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.GRAVITITE_PIKE, ModelTemplates.FLAT_HANDHELD_ITEM, ReinforcementTier.THIRD);
        this.generateAetherCrossbow(AetherIIItems.GRAVITITE_CROSSBOW);

        this.generateModeledShield(AetherIIItems.SKYROOT_SHIELD, TextureMapping.getBlockTexture(AetherIIBlocks.SKYROOT_PLANKS), "light_shield");
        this.generateModeledShield(AetherIIItems.BURRUKAI_PLATE_SHIELD, TextureMapping.getBlockTexture(AetherIIBlocks.SKYROOT_PLANKS), "heavy_shield");
        this.generateModeledShield(AetherIIItems.ZANITE_SHIELD, TextureMapping.getBlockTexture(AetherIIBlocks.SKYROOT_PLANKS), "light_shield");
        this.generateModeledShield(AetherIIItems.ARKENIUM_SHIELD, TextureMapping.getBlockTexture(AetherIIBlocks.SKYROOT_PLANKS), "heavy_shield");
        this.generateModeledShield(AetherIIItems.GRAVITITE_SHIELD, TextureMapping.getBlockTexture(AetherIIBlocks.SKYROOT_PLANKS), "heavy_shield");

        this.generateDartShooter(AetherIIItems.DART_SHOOTER);
        this.generateDarts(AetherIIItems.AMBER_DARTS);

        this.generateFlatItem(AetherIIItems.SCATTERGLASS_BOLT, ModelTemplates.FLAT_ITEM);

        this.generateHammerOfDemolition(AetherIIItems.HAMMER_OF_DEMOLITION);
        this.generateBrokenItem(AetherIIItems.HAMMER_OF_DEMOLITION);

        // Armor
        this.generateDyedArmorItem(AetherIIItems.BEAST_PELT_HELMET, 0xFFCFEEF9);
        this.generateDyedArmorItem(AetherIIItems.BEAST_PELT_CHESTPLATE, 0xFFCFEEF9);
        this.generateDyedArmorItem(AetherIIItems.BEAST_PELT_LEGGINGS, 0xFFCFEEF9);
        this.generateDyedArmorItem(AetherIIItems.BEAST_PELT_BOOTS, 0xFFCFEEF9);
        this.generateDyedArmorItem(AetherIIItems.BEAST_PELT_GLOVES, 0xFFCFEEF9);

        this.generateDyedArmorItem(AetherIIItems.BURRUKAI_PLATE_HELMET, 0xFF619CC0);
        this.generateDyedArmorItem(AetherIIItems.BURRUKAI_PLATE_CHESTPLATE, 0xFF619CC0);
        this.generateDyedArmorItem(AetherIIItems.BURRUKAI_PLATE_LEGGINGS, 0xFF619CC0);
        this.generateDyedArmorItem(AetherIIItems.BURRUKAI_PLATE_BOOTS, 0xFF619CC0);
        this.generateDyedArmorItem(AetherIIItems.BURRUKAI_PLATE_GLOVES, 0xFF619CC0);

        this.generateFlatItem(AetherIIItems.ZANITE_HELMET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZANITE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZANITE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZANITE_BOOTS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZANITE_GLOVES, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(AetherIIItems.ARKENIUM_HELMET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_LEGGINGS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_BOOTS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_GLOVES, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(AetherIIItems.GRAVITITE_HELMET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_BOOTS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_GLOVES, ModelTemplates.FLAT_ITEM);

        this.generateFlatItem(AetherIIItems.SENTRY_BOOTS, ModelTemplates.FLAT_ITEM);
        this.generateBrokenItem(AetherIIItems.SENTRY_BOOTS);

        this.generateFlatItem(AetherIIItems.NEPTUNE_HELMET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.NEPTUNE_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.NEPTUNE_LEGGINGS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.NEPTUNE_BOOTS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.NEPTUNE_GLOVES, ModelTemplates.FLAT_ITEM);
        this.generateBrokenItem(AetherIIItems.NEPTUNE_HELMET);
        this.generateBrokenItem(AetherIIItems.NEPTUNE_CHESTPLATE);
        this.generateBrokenItem(AetherIIItems.NEPTUNE_LEGGINGS);
        this.generateBrokenItem(AetherIIItems.NEPTUNE_BOOTS);
        this.generateBrokenItem(AetherIIItems.NEPTUNE_GLOVES);

        // Relics
        this.generateFlatItem(AetherIIItems.KINETIC_THRUSTERS, ModelTemplates.FLAT_ITEM);

        // Accessories
        this.generateReinforcedItem(AetherIIItems.ZANITE_PENDANT, ModelTemplates.FLAT_ITEM, ReinforcementTier.THIRD);
        this.generateReinforcedItem(AetherIIItems.ICESTONE_PENDANT, ModelTemplates.FLAT_ITEM, ReinforcementTier.THIRD);

        // Charms
        this.generateCharmItem(AetherIIItems.CHARM_OF_EFFICIENCY_I, "tool", "1", "efficiency");
        this.generateCharmItem(AetherIIItems.CHARM_OF_REACH_I, "tool", "1", "reach");

        this.generateCharmItem(AetherIIItems.CHARM_OF_DAMAGE_I, "weapon", "1", "damage");
        this.generateCharmItem(AetherIIItems.CHARM_OF_DEXTERITY_I, "weapon", "1", "dexterity");
        this.generateCharmItem(AetherIIItems.CHARM_OF_KNOCKBACK_I, "weapon", "1", "knockback");

        this.generateCharmItem(AetherIIItems.CHARM_OF_HEALTH_I, "armor", "1", "health");
        this.generateCharmItem(AetherIIItems.CHARM_OF_DEFENSE_I, "armor", "1", "defense");
        this.generateCharmItem(AetherIIItems.CHARM_OF_TOUGHNESS_I, "armor", "1", "toughness");
        this.generateCharmItem(AetherIIItems.CHARM_OF_RESISTANCE_I, "armor", "1", "resistance");
        this.generateCharmItem(AetherIIItems.CHARM_OF_AGILITY_I, "armor", "1", "agility");

        // Materials
        this.generateFlatItem(AetherIIItems.SKYROOT_STICK, ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateFlatItem(AetherIIItems.SCATTERGLASS_SHARD, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.AMBROSIUM_SHARD, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.FOSSILIZED_ZANITE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZANITE_GEMSTONE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.INERT_ARKENIUM, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_PLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_CHIP, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.INERT_GRAVITITE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_PLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.FOSSILIZED_CORROBONITE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.CORROBONITE_CRYSTAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.NEPTUNE_SCALE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SENTRY_SERVO, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.RESONANT_STONE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.FOSSILIZED_GLINT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GLINT_GEMSTONE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GOLDEN_AMBER, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.CLOUDTWINE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BEAST_PELT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BURRUKAI_PLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.KIRRID_PLATE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_PINECONE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.VALKYRIE_WINGS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BRETTL_CANE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BRETTL_GRASS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BRETTL_ROPE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARILUM_BULBS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.AECHOR_PETAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARCTIC_SNOWBALL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SWET_GEL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SWET_SUGAR, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.PRISMALLARD_FEATHER, ModelTemplates.FLAT_ITEM);
        this.generateMoaFeatherItem(AetherIIItems.MOA_FEATHER);
        this.generateFlatItem(AetherIIItems.COCKATRICE_FEATHER, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SCATTERGLASS_VIAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZEPHYR_HUSK, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.CHARGE_CATALYST, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_CORE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_CORE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.EYE_OF_THE_MIMIC, ModelTemplates.FLAT_ITEM);

        // Irradiated Items
        this.generateFlatItem(AetherIIItems.IRRADIATED_ARMOR, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.IRRADIATED_WEAPON, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.IRRADIATED_TOOL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.IRRADIATED_CHUNK, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.IRRADIATED_DUST, ModelTemplates.FLAT_ITEM);

        // Food
        this.generateFlatItem(AetherIIItems.BLUEBERRY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ENCHANTED_BLUEBERRY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ORANGE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ENCHANTED_ORANGE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.WYNDBERRY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ENCHANTED_WYNDBERRY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GOLDEN_WYNDBERRY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SATIVAL_BULB, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SWET_JELLY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ENCHANTED_SWET_JELLY, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.FRIED_PRISMALLARD_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.PRISMALLARD_LEG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.PRISMALLARD_ROAST, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BURRUKAI_RIBS, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BURRUKAI_RIB_CUT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.KIRRID_CUTLET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.KIRRID_LOIN, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.RAW_TAEGORE_MEAT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.TAEGORE_STEAK, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_LIZARD_ON_A_STICK, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ROASTED_SKYROOT_LIZARD_ON_A_STICK, ModelTemplates.FLAT_ITEM);

        // Consumables
        this.generateFlatItem(AetherIIItems.WATER_VIAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BANDAGE, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SPLINT, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ANTITOXIN_VIAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ANTIVENOM_VIAL, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.VALKYRIE_TEA, ModelTemplates.FLAT_ITEM);
        this.generateHealingStoneItem(AetherIIItems.HEALING_STONE);

        // Utilities
        this.generateFlatItem(AetherIIItems.SHIFTING_GLASS, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Companions
        this.generateCompanionItem(AetherIIItems.AERBUNNY_BELL);

        // Gliders
        this.generateGliderItem(AetherIIItems.COLD_AERCLOUD_GLIDER, false);
        this.generateGliderItem(AetherIIItems.GOLDEN_AERCLOUD_GLIDER, false);
        this.generateGliderItem(AetherIIItems.BLUE_AERCLOUD_GLIDER, true);
        this.generateGliderItem(AetherIIItems.PURPLE_AERCLOUD_GLIDER, true);

        // Skyroot Buckets
        this.generateFlatItem(AetherIIItems.SKYROOT_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_WATER_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_MILK_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_POWDER_SNOW_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_COD_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_SALMON_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_PUFFERFISH_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_TROPICAL_FISH_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_AXOLOTL_BUCKET, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_TADPOLE_BUCKET, ModelTemplates.FLAT_ITEM);

        // Arkenium Canisters
        this.generateFlatItem(AetherIIItems.ARKENIUM_CANISTER, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_ALKAHEST_CANISTER, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_HESTVEIL_CANISTER, ModelTemplates.FLAT_ITEM);

        // Music Discs
        this.generateMusicPlayer(AetherIIItems.MUSIC_PLAYER);

        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_ASCENDING_DAWN);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_AERWHALE);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_APPROACHES);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_DEMISE);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_CHINCHILLA);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_HIGH);
        this.generateMusicDisc(AetherIIItems.ENGRAVED_DISC_REVOLUTIONS);

        // Spawn Eggs
        this.generateFlatItem(AetherIIItems.FLYING_COW_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SHEEPUFF_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.PHYG_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.AERBUNNY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.AERWHALE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.HIGHFIELDS_TAEGORE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.MAGNETIC_TAEGORE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARCTIC_TAEGORE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.HIGHFIELDS_BURRUKAI_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.MAGNETIC_BURRUKAI_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARCTIC_BURRUKAI_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.HIGHFIELDS_KIRRID_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.MAGNETIC_KIRRID_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARCTIC_KIRRID_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.MOA_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.PRISMALLARD_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKYROOT_LIZARD_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.AECHOR_PLANT_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.CARRION_SPROUT_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GLITTERWING_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SHROUDWING_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ZEPHYR_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.TEMPEST_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.COCKATRICE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BLUE_SWET_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GOLDEN_SWET_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SKEPHID_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ARKENIUM_TALUTON_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GRAVITITE_TALUTON_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.DETONATION_SENTRY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SENTRY_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SENTRY_CRATE_MIMIC_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.SLIDER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        // Misc
        this.generateBundleModels(AetherIIItems.BEAST_PELT_BUNDLE);
        this.generateLasso(AetherIIItems.BRETTL_LASSO);
        this.generateFlatItem(AetherIIItems.PRISMALLARD_EGG, ModelTemplates.FLAT_ITEM);
        this.generateMoaEggItem(AetherIIItems.MOA_EGG);
        this.generateFlatItem(AetherIIItems.MOA_FEED, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BLUEBERRY_MOA_FEED, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.ENCHANTED_MOA_FEED, ModelTemplates.FLAT_ITEM);
        this.generateDyedSaddleItem(AetherIIItems.MOA_SADDLE);
        this.generateFlatItem(AetherIIItems.MOA_SADDLEBAG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.LARGE_MOA_SADDLEBAG, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.CLOUD_SKIFF, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GLINT_COIN, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.GUIDEBOOK_PAGE, ModelTemplates.FLAT_ITEM);
        this.generatePortalFrameItem(AetherIIItems.AETHER_PORTAL_FRAME);
        this.generateFlatItem(AetherIIItems.MURAL_ITEM, ModelTemplates.FLAT_ITEM);
        this.generateFlatItem(AetherIIItems.BROKEN_ITEM, ModelTemplates.FLAT_ITEM);

        // Blocks
        this.generateFlatItem(AetherIIBlocks.ARKENIUM_CHAIN.asItem(), ModelTemplates.FLAT_ITEM);
    }
}

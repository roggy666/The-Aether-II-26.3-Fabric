package com.aetherteam.aetherii.data.resources.builders.models;

import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;

public class AetherIITexturedModels {
    public static final TexturedModel.Provider LEAVES = TexturedModel.createDefault(TextureMapping::cube, ModelTemplates.CUBE_ALL);
    public static final TexturedModel.Provider TINTED_LEAVES = TexturedModel.createDefault(TextureMapping::cube, AetherIIModelTemplates.LEAVES);
    public static final TexturedModel.Provider ASYMMETRICAL_CROSS_EVEN = TexturedModel.createDefault(AetherIITextureMappings::asymmetricalCross, AetherIIModelTemplates.ASYMMETRICAL_CROSS_EVEN);
    public static final TexturedModel.Provider ASYMMETRICAL_CROSS_EVEN_MIRRORED = TexturedModel.createDefault(AetherIITextureMappings::asymmetricalCross, AetherIIModelTemplates.ASYMMETRICAL_CROSS_EVEN_MIRRORED);
    public static final TexturedModel.Provider ASYMMETRICAL_CROSS_ODD = TexturedModel.createDefault(AetherIITextureMappings::asymmetricalCross, AetherIIModelTemplates.ASYMMETRICAL_CROSS_ODD);
    public static final TexturedModel.Provider ASYMMETRICAL_CROSS_ODD_MIRRORED = TexturedModel.createDefault(AetherIITextureMappings::asymmetricalCross, AetherIIModelTemplates.ASYMMETRICAL_CROSS_ODD_MIRRORED);
    public static final TexturedModel.Provider LILICHIME = TexturedModel.createDefault(AetherIITextureMappings::lilichime, AetherIIModelTemplates.LILICHIME);
    public static final TexturedModel.Provider PLURACIAN = TexturedModel.createDefault(AetherIITextureMappings::pluracian, AetherIIModelTemplates.PLURACIAN);
    public static final TexturedModel.Provider BRYALINN_MOSS_FLOWERS_1 = TexturedModel.createDefault(AetherIITextureMappings::flowerbed, AetherIIModelTemplates.BRYALINN_MOSS_FLOWERS_1);
    public static final TexturedModel.Provider BRYALINN_MOSS_FLOWERS_2 = TexturedModel.createDefault(AetherIITextureMappings::flowerbed, AetherIIModelTemplates.BRYALINN_MOSS_FLOWERS_2);
    public static final TexturedModel.Provider BRYALINN_MOSS_FLOWERS_3 = TexturedModel.createDefault(AetherIITextureMappings::flowerbed, AetherIIModelTemplates.BRYALINN_MOSS_FLOWERS_3);
    public static final TexturedModel.Provider BRYALINN_MOSS_FLOWERS_4 = TexturedModel.createDefault(AetherIITextureMappings::flowerbed, AetherIIModelTemplates.BRYALINN_MOSS_FLOWERS_4);
    public static final TexturedModel.Provider HOLPUPEA_1 = TexturedModel.createDefault(AetherIITextureMappings::flowerbedAndStem, AetherIIModelTemplates.HOLPUPEA_1);
    public static final TexturedModel.Provider HOLPUPEA_2 = TexturedModel.createDefault(AetherIITextureMappings::flowerbedAndStem, AetherIIModelTemplates.HOLPUPEA_2);
    public static final TexturedModel.Provider HOLPUPEA_3 = TexturedModel.createDefault(AetherIITextureMappings::flowerbedAndStem, AetherIIModelTemplates.HOLPUPEA_3);
    public static final TexturedModel.Provider HOLPUPEA_4 = TexturedModel.createDefault(AetherIITextureMappings::flowerbedAndStem, AetherIIModelTemplates.HOLPUPEA_4);
    public static final TexturedModel.Provider TARAHESP_FLOWERS_1 = TexturedModel.createDefault(AetherIITextureMappings::tarahespFlowerbed, AetherIIModelTemplates.TARAHESP_FLOWERS_1);
    public static final TexturedModel.Provider TARAHESP_FLOWERS_2 = TexturedModel.createDefault(AetherIITextureMappings::tarahespFlowerbed, AetherIIModelTemplates.TARAHESP_FLOWERS_2);
    public static final TexturedModel.Provider TARAHESP_FLOWERS_3 = TexturedModel.createDefault(AetherIITextureMappings::tarahespFlowerbed, AetherIIModelTemplates.TARAHESP_FLOWERS_3);
    public static final TexturedModel.Provider TARAHESP_FLOWERS_4 = TexturedModel.createDefault(AetherIITextureMappings::tarahespFlowerbed, AetherIIModelTemplates.TARAHESP_FLOWERS_4);
    public static final TexturedModel.Provider CARPET_CUTOUT = TexturedModel.createDefault(TextureMapping::wool, AetherIIModelTemplates.CARPET_CUTOUT);
    public static final TexturedModel.Provider MOSSY_CARPET_SIDE_CUTOUT = TexturedModel.createDefault(TextureMapping::side, AetherIIModelTemplates.MOSSY_CARPET_SIDE_CUTOUT);
    public static final TexturedModel.Provider ARKENIUM_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, AetherIIModelTemplates.ARKENIUM_LANTERN);
    public static final TexturedModel.Provider HANGING_ARKENIUM_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, AetherIIModelTemplates.HANGING_ARKENIUM_LANTERN);
    public static final TexturedModel.Provider RUSTIC_ARKENIUM_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, AetherIIModelTemplates.RUSTIC_ARKENIUM_LANTERN);
    public static final TexturedModel.Provider HANGING_RUSTIC_ARKENIUM_LANTERN = TexturedModel.createDefault(TextureMapping::lantern, AetherIIModelTemplates.HANGING_RUSTIC_ARKENIUM_LANTERN);
    public static final TexturedModel.Provider ALTAR = TexturedModel.createDefault(TextureMapping::cube, AetherIIModelTemplates.ALTAR);
    public static final TexturedModel.Provider ARKENIUM_FORGE = TexturedModel.createDefault(TextureMapping::cube, AetherIIModelTemplates.ARKENIUM_FORGE);
}
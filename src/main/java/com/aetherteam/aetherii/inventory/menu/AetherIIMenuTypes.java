package com.aetherteam.aetherii.inventory.menu;

import com.aetherteam.aetherii.AetherII;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class AetherIIMenuTypes {
    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType.MenuSupplier<T> menu) {
        return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new MenuType<>(menu, FeatureFlags.VANILLA_SET));
    }

    private static <T extends AbstractContainerMenu, D> ExtendedMenuType<T, D> registerExtended(String name, ExtendedMenuType.ExtendedFactory<T, D> factory, StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        return Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(AetherII.MODID, name), new ExtendedMenuType<>(factory, streamCodec));
    }

    public static final ExtendedMenuType<GuidebookEquipmentMenu, Integer> GUIDEBOOK = registerExtended("guidebook", (containerId, inventory, entityId) -> new GuidebookEquipmentMenu(AetherIIMenuTypes.GUIDEBOOK, containerId, inventory, entityId), ByteBufCodecs.INT);
    public static final MenuType<SkyrootCraftingMenu> SKYROOT_CRAFTING_TABLE = register("skyroot_crafting_table", SkyrootCraftingMenu::new);
    public static final MenuType<HolystoneFurnaceMenu> HOLYSTONE_FURNACE = register("holystone_furnace", HolystoneFurnaceMenu::new);
    public static final MenuType<HolystoneSmokerMenu> HOLYSTONE_SMOKER = register("holystone_smoker", HolystoneSmokerMenu::new);
    public static final MenuType<ArtisansBenchMenu> ARTISANS_BENCH = register("artisans_bench", ArtisansBenchMenu::new);
    public static final MenuType<AmberHourglassMenu> AMBER_HOURGLASS = register("amber_hourglass", AmberHourglassMenu::new);
    public static final MenuType<AltarMenu> ALTAR = register("altar", AltarMenu::new);
    public static final MenuType<ArkeniumForgeMenu> ARKENIUM_FORGE = register("arkenium_forge", ArkeniumForgeMenu::new);
    public static final MenuType<AlkahestPurifierMenu> ALKAHEST_PURIFIER = register("alkahest_purifier", AlkahestPurifierMenu::new);

    public static void init() {}
}
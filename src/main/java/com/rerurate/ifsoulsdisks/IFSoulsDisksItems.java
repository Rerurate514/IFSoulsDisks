package com.rerurate.ifsoulsdisks;

import appeng.items.materials.MaterialItem;
import appeng.items.storage.StorageTier;
import com.buuz135.industrialforegoingsouls.IndustrialForegoingSouls;
import com.rerurate.ifsoulsdisks.items.SoulsStorageCell;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IFSoulsDisksItems {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            Ifsoulsdisks.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Ifsoulsdisks.MODID);

    private static Item basic() {
        return new MaterialItem(properties());
    }

    private static Item.Properties properties() {
        return new Item.Properties();
    }

    public static final RegistryObject<Item> SOULS_CELL_HOUSING = ITEMS.register("souls_cell_housing", IFSoulsDisksItems::basic);

    public static final RegistryObject<Item> SOULS_STORAGE_CELL_1K = ITEMS.register("souls_storage_cell_1k",
            () -> new SoulsStorageCell(properties().stacksTo(1), StorageTier.SIZE_1K, SOULS_CELL_HOUSING.get()));
    public static final RegistryObject<Item> SOULS_STORAGE_CELL_4K = ITEMS.register("souls_storage_cell_4k",
            () -> new SoulsStorageCell(properties().stacksTo(1), StorageTier.SIZE_4K, SOULS_CELL_HOUSING.get()));
    public static final RegistryObject<Item> SOULS_STORAGE_CELL_16K = ITEMS.register("souls_storage_cell_16k",
            () -> new SoulsStorageCell(properties().stacksTo(1), StorageTier.SIZE_16K, SOULS_CELL_HOUSING.get()));
    public static final RegistryObject<Item> SOULS_STORAGE_CELL_64K = ITEMS.register("souls_storage_cell_64k",
            () -> new SoulsStorageCell(properties().stacksTo(1), StorageTier.SIZE_64K, SOULS_CELL_HOUSING.get()));
    public static final RegistryObject<Item> SOULS_STORAGE_CELL_256K = ITEMS.register("souls_storage_cell_256k",
            () -> new SoulsStorageCell(properties().stacksTo(1), StorageTier.SIZE_256K,
                    SOULS_CELL_HOUSING.get()));

    public static final RegistryObject<CreativeModeTab> IF_SOULS_DISKS_TAB = TABS.register("if_souls_disks_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ifsoulsdisks"))
                    .icon(() -> SOULS_STORAGE_CELL_1K.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(SOULS_CELL_HOUSING.get());
                        output.accept(SOULS_STORAGE_CELL_1K.get());
                        output.accept(SOULS_STORAGE_CELL_4K.get());
                        output.accept(SOULS_STORAGE_CELL_16K.get());
                        output.accept(SOULS_STORAGE_CELL_64K.get());
                        output.accept(SOULS_STORAGE_CELL_256K.get());
                    })
                    .build());

    public static RegistryObject<Item> get(SoulsStorageTier tier) {
        return switch (tier) {
            case CELL_1K -> SOULS_STORAGE_CELL_1K;
            case CELL_4K -> SOULS_STORAGE_CELL_4K;
            case CELL_16K -> SOULS_STORAGE_CELL_16K;
            case CELL_64K -> SOULS_STORAGE_CELL_64K;
            case CELL_256K -> SOULS_STORAGE_CELL_256K;
        };
    }

    public enum SoulsStorageTier {
        CELL_1K,
        CELL_4K,
        CELL_16K,
        CELL_64K,
        CELL_256K
    }
}

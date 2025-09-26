package com.rerurate.ifsoulsdisks.items;

import appeng.items.storage.BasicStorageCell;
import appeng.items.storage.StorageTier;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulAEKeyType;
import net.minecraft.world.level.ItemLike;

public class SoulsStorageCell extends BasicStorageCell {
    public SoulsStorageCell(Properties properties, StorageTier tier, ItemLike housingItem) {
        super(
                properties,
                tier.componentSupplier().get(),
                housingItem, tier.idleDrain(),
                tier.bytes() / 1024,
                tier.bytes() / 128,
                1,
                SoulAEKeyType.TYPE
        );
    }
}

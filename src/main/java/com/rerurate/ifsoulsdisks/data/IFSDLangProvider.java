package com.rerurate.ifsoulsdisks.data;

import com.rerurate.ifsoulsdisks.IFSoulsDisksItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class IFSDLangProvider extends LanguageProvider {
    public IFSDLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.ifsoulsdisks", "Industrial Foregoing: Souls Disks");

        this.add(IFSoulsDisksItems.SOULS_CELL_HOUSING.get(), "ME Souls Cell Housing");

        this.add(IFSoulsDisksItems.SOULS_STORAGE_CELL_1K.get(), "1k ME Souls Storage Cell");
        this.add(IFSoulsDisksItems.SOULS_STORAGE_CELL_4K.get(), "4k ME Souls Storage Cell");
        this.add(IFSoulsDisksItems.SOULS_STORAGE_CELL_16K.get(), "16k ME Souls Storage Cell");
        this.add(IFSoulsDisksItems.SOULS_STORAGE_CELL_64K.get(), "64k ME Souls Storage Cell");
        this.add(IFSoulsDisksItems.SOULS_STORAGE_CELL_256K.get(), "256k ME Souls Storage Cell");
    }
}
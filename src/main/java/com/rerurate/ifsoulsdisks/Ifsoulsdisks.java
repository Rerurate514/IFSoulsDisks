package com.rerurate.ifsoulsdisks;

import appeng.parts.automation.StackWorldBehaviors;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulAEKeyType;
import com.mojang.logging.LogUtils;
import com.rerurate.ifsoulsdisks.applied.stack.SoulsStackExportStrategy;
import com.rerurate.ifsoulsdisks.applied.stack.SoulsStackImportStrategy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Ifsoulsdisks.MODID)
public class Ifsoulsdisks {
    public static final String MODID = "ifsoulsdisks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Ifsoulsdisks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        IFSoulsDisksItems.ITEMS.register(modEventBus);
        IFSoulsDisksItems.TABS.register(modEventBus);

        StackWorldBehaviors.registerImportStrategy(
                SoulAEKeyType.TYPE,
                SoulsStackImportStrategy::new
        );

        StackWorldBehaviors.registerExportStrategy(
                SoulAEKeyType.TYPE,
                SoulsStackExportStrategy::new
        );
    }
}
package com.rerurate.ifsoulsdisks;

import appeng.parts.automation.StackWorldBehaviors;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulAEKeyType;
import com.hrznstudio.titanium.module.ModuleController;
import com.mojang.logging.LogUtils;
import com.rerurate.ifsoulsdisks.applied.stack.SoulsStackExportStrategy;
import com.rerurate.ifsoulsdisks.applied.stack.SoulsStackImportStrategy;
import com.rerurate.ifsoulsdisks.data.IFSDLangProvider;
import com.rerurate.ifsoulsdisks.data.IFSDRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mod(Ifsoulsdisks.MODID)
public class Ifsoulsdisks extends ModuleController {
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

    @Override
    protected void initModules() {
        //noop
    }

    @Override
    public void addDataProvider(GatherDataEvent event) {
        NonNullLazy<List<Block>> blocksToProcess = NonNullLazy.of(() ->
                ForgeRegistries.BLOCKS.getValues()
                        .stream()
                        .filter(basicBlock -> Optional.ofNullable(ForgeRegistries.BLOCKS.getKey(basicBlock))
                                .map(ResourceLocation::getNamespace)
                                .filter(MODID::equalsIgnoreCase)
                                .isPresent())
                        .collect(Collectors.toList())
        );

        event.getGenerator().addProvider(true, new IFSDLangProvider(event.getGenerator().getPackOutput(), MODID, "en_us"));
        event.getGenerator().addProvider(true, new IFSDRecipeProvider(event.getGenerator()));
    }
}
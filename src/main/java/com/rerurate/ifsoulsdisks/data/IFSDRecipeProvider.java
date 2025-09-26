package com.rerurate.ifsoulsdisks.data;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.buuz135.industrial.utils.IndustrialTags;
import com.buuz135.industrialforegoingsouls.IndustrialForegoingSouls;
import com.hrznstudio.titanium.recipe.generator.TitaniumRecipeProvider;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import com.rerurate.ifsoulsdisks.IFSoulsDisksItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class IFSDRecipeProvider extends TitaniumRecipeProvider {
    public IFSDRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void register(Consumer<FinishedRecipe> consumer) {
        TitaniumShapedRecipeBuilder.shapedRecipe(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .pattern("QSQ")
                .pattern("ECE")
                .pattern("IDI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.ECHO_SHARD)
                .define('D', Items.DIAMOND)
                .define('Q', AEBlocks.QUARTZ_GLASS)
                .define('S', IndustrialForegoingSouls.SOUL_SURGE_BLOCK.getKey().get())
                .define('E', IndustrialTags.Items.PLASTIC)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFSoulsDisksItems.SOULS_STORAGE_CELL_1K.get())
                .requires(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .requires(AEItems.CELL_COMPONENT_1K)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFSoulsDisksItems.SOULS_STORAGE_CELL_4K.get())
                .requires(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .requires(AEItems.CELL_COMPONENT_4K)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFSoulsDisksItems.SOULS_STORAGE_CELL_16K.get())
                .requires(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .requires(AEItems.CELL_COMPONENT_16K)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFSoulsDisksItems.SOULS_STORAGE_CELL_64K.get())
                .requires(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .requires(AEItems.CELL_COMPONENT_64K)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(IFSoulsDisksItems.SOULS_STORAGE_CELL_256K.get())
                .requires(IFSoulsDisksItems.SOULS_CELL_HOUSING.get())
                .requires(AEItems.CELL_COMPONENT_256K)
                .save(consumer);

    }
}

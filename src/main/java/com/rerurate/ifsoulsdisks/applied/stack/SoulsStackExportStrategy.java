package com.rerurate.ifsoulsdisks.applied.stack;

import appeng.api.behaviors.StackExportStrategy;
import appeng.api.behaviors.StackTransferContext;
import appeng.api.config.Actionable;
import appeng.api.stacks.AEKey;
import appeng.api.storage.StorageHelper;
import appeng.util.BlockApiCache;
import com.buuz135.industrialforegoingsouls.capabilities.ISoulHandler;
import com.buuz135.industrialforegoingsouls.capabilities.SoulCapabilities;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulKey;
import com.google.common.primitives.Ints;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulsStackExportStrategy implements StackExportStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoulsStackExportStrategy.class);
    private final BlockApiCache<? extends ISoulHandler> cache;
    private final Direction fromSide;

    public SoulsStackExportStrategy(
            ServerLevel level,
            BlockPos fromPos,
            Direction fromSide
    ) {
        this.cache = BlockApiCache.create(SoulCapabilities.BLOCK, level, fromPos);

        this.fromSide = fromSide;
    }


    @Override
    public long transfer(StackTransferContext context, AEKey what, long amount) {
        if (!(what instanceof SoulKey soulsKey)) {
            return 0;
        }

        var soulHandler = cache.find(fromSide);
        if (soulHandler == null) return 0;

        var inventory = context.getInternalStorage();
        var filledSimulation = soulHandler.fill(Ints.saturatedCast(amount), ISoulHandler.Action.SIMULATE);

        var drained = (int) StorageHelper.poweredExtraction(
                context.getEnergySource(),
                inventory.getInventory(),
                what,
                filledSimulation,
                context.getActionSource(),
                Actionable.SIMULATE
        );

        if(drained > 0) {
            soulHandler.fill(drained, ISoulHandler.Action.EXECUTE);
        }

        return drained;

    }

    @Override
    public long push(AEKey what, long amount, Actionable mode) {
        if (!(what instanceof SoulKey soulKey)) {
            return 0;
        }

        var soulHandler = cache.find(fromSide);
        if (soulHandler == null) return 0;

        if(mode.isSimulate()) {
            return soulHandler.fill(Ints.saturatedCast(amount), ISoulHandler.Action.SIMULATE);
        } else {
            return soulHandler.fill(Ints.saturatedCast(amount), ISoulHandler.Action.EXECUTE);
        }
    }
}
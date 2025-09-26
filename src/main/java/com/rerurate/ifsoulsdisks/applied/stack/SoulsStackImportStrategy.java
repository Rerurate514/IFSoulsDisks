package com.rerurate.ifsoulsdisks.applied.stack;

import appeng.api.behaviors.StackImportStrategy;
import appeng.api.behaviors.StackTransferContext;
import appeng.api.config.Actionable;
import appeng.util.BlockApiCache;
import com.buuz135.industrialforegoingsouls.capabilities.ISoulHandler;
import com.buuz135.industrialforegoingsouls.capabilities.SoulCapabilities;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulAEKeyType;
import com.buuz135.industrialforegoingsouls.soulplied_energistics.applied.SoulKey;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulsStackImportStrategy implements StackImportStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoulsStackExportStrategy.class);
    private final BlockApiCache<? extends ISoulHandler> cache;
    private final Direction fromSide;

    public SoulsStackImportStrategy(
            ServerLevel level,
            BlockPos fromPos,
            Direction fromSide
    ) {
        this.cache = BlockApiCache.create(SoulCapabilities.BLOCK, level, fromPos);

        this.fromSide = fromSide;
    }

    @Override
    public boolean transfer(StackTransferContext context) {
        if(!context.isKeyTypeEnabled(SoulAEKeyType.TYPE)) return false;

        var soulHandler = cache.find(fromSide);
        if(soulHandler == null) return false;

        var remainingAmount = context.getOperationsRemaining() * (long) SoulAEKeyType.TYPE.getAmountPerOperation();
        var inventory = context.getInternalStorage();

        for(int i = 0; i < soulHandler.getSoulTanks() && remainingAmount > 0; i++) {
            var stack = soulHandler.getSoulInTank(i);

            var simulate = (int) inventory.getInventory().insert(
                SoulKey.INSTANCE,
                remainingAmount,
                Actionable.SIMULATE,
                    context.getActionSource()
            );

            var amount = soulHandler.drain(simulate, ISoulHandler.Action.EXECUTE);

            if(amount > 0) {
                var inserted = inventory.getInventory().insert(
                    SoulKey.INSTANCE,
                    amount,
                    Actionable.MODULATE,
                    context.getActionSource()
                );

                if(inserted < amount) {
                    int leftOver = (int)(amount - inserted);
                    leftOver -= soulHandler.drain(
                            leftOver,
                            ISoulHandler.Action.EXECUTE
                    );
                }

                var opsUsed = Math.max(1, inserted /SoulAEKeyType.TYPE.getAmountPerOperation());
                context.reduceOperationsRemaining(opsUsed);
                remainingAmount -= inserted;
            }
        }

        return false;
    }
}

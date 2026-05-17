package com.cukkoo.keepwalking;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(KeepWalking.MOD_ID)
public class KeepWalking {
    public static final String MOD_ID = "keepwalking";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public KeepWalking(IEventBus modEventBus) {
        LOGGER.info("Keep Walking loaded — movement stays active through inventory screens");
    }
}

package com.cukkoo.keepwalking;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(KeepWalking.MOD_ID)
public class KeepWalking {
    public static final String MOD_ID = "keepwalking";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public KeepWalking() {
        LOGGER.info("Keep Walking loaded — movement stays active through inventory screens");
    }
}

package com.cukkoo.keepwalking;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepWalkingClient implements ClientModInitializer {
    public static final String MOD_ID = "keepwalking";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Keep Walking loaded — movement stays active through inventory screens");
    }
}

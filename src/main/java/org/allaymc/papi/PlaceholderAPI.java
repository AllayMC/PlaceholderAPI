package org.allaymc.papi;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;

@Slf4j
public class PlaceholderAPI extends Plugin {
    @Override
    public void onLoad() {
        log.info("PlaceholderAPI loaded!");
    }

    @Override
    public void onEnable() {
        log.info("PlaceholderAPI enabled!");
    }

    @Override
    public void onDisable() {
        log.info("PlaceholderAPI disabled!");
    }
}
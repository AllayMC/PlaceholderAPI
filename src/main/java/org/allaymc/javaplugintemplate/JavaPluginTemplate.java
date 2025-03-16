package org.allaymc.javaplugintemplate;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;

@Slf4j
public class JavaPluginTemplate extends Plugin {
    @Override
    public void onLoad() {
        log.info("JavaPluginTemplate loaded!");
    }

    @Override
    public void onEnable() {
        log.info("JavaPluginTemplate enabled!");
    }

    @Override
    public void onDisable() {
        log.info("JavaPluginTemplate disabled!");
    }
}
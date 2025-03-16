package org.allaymc.papi;

import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface PlaceholderProcessor {
    /**
     * Process the placeholder.
     *
     * @param player the player used to process the placeholder, can be {@code null}.
     * @param params the params used to process the placeholder, can be {@code null}.
     *
     * @return the processed placeholder.
     */
    String process(EntityPlayer player, String params);
}

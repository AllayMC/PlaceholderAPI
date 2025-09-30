package org.allaymc.papi;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.jetbrains.annotations.UnmodifiableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PlaceholderAPI extends Plugin {

    protected static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[{]([^{}]+)[}]");
    @Getter
    protected static PlaceholderAPI API;

    protected Map<String, PlaceholderProcessor> registry;

    public PlaceholderAPI() {
        API = this;
        this.registry = new HashMap<>();
        this.registerDefaultPlaceholders();
    }

    @Override
    public void onLoad() {
        log.info("PlaceholderAPI loaded!");
    }

    @Override
    public void onEnable() {
        Registries.COMMANDS.register(new PAPICommand());
        log.info("PlaceholderAPI enabled!");
    }

    @Override
    public void onDisable() {
        log.info("PlaceholderAPI disabled!");
    }

    /**
     * Translate all placeholders into their corresponding values.
     * The pattern of a valid placeholder is {@code {<identifier>|<params>}}.
     *
     * @param player player to parse the placeholders against
     * @param text text to set the placeholder values in
     *
     * @return string containing all translated placeholders
     */
    public String setPlaceholders(EntityPlayer player, String text) {
        return CharsReplacer.apply(text, player, this.registry::get);
    }

    /**
     * Check if a specific placeholder identifier is currently registered.
     *
     * @param identifier the identifier to check
     *
     * @return {@code true} if identifier is already registered, {@code false} otherwise
     */
    public boolean isRegistered(String identifier) {
        return this.registry.containsKey(identifier);
    }

    /**
     * Get all registered placeholder identifiers.
     *
     * @return A Set of type string containing the identifiers of all registered placeholders
     */
    @UnmodifiableView
    public Set<String> getRegisteredIdentifiers() {
        return Collections.unmodifiableSet(this.registry.keySet());
    }

    /**
     * Get the placeholder pattern.
     *
     * @return regex pattern of {@code [{]([^{}]+)[}]}
     */
    public Pattern getPlaceholderPattern() {
        return PLACEHOLDER_PATTERN;
    }

    /**
     * Check if a text contains any placeholders ({@code {<identifier>|<params>}}).
     *
     * @param text text to check
     *
     * @return {@code true} if text contains any matches to the bracket placeholder pattern, {@code false} otherwise
     */
    public boolean containsPlaceholders(String text) {
        return text != null && PLACEHOLDER_PATTERN.matcher(text).find();
    }

    /**
     * Attempt to register a placeholder.
     *
     * @param plugin the plugin that is registering the placeholder
     * @param identifier the identifier of the placeholder
     * @param processor the processor that will process the placeholder
     *
     * @return {@code true} if the placeholder was successfully registered, {@code false} otherwise
     */
    public boolean registerPlaceholder(Plugin plugin, String identifier, PlaceholderProcessor processor) {
        if (isRegistered(identifier)) {
            identifier = plugin.getPluginContainer().descriptor().getName() + ":" + identifier;
        }
        if (isRegistered(identifier)) {
            log.warn("Plugin {} trying to register a duplicate placeholder: {}", plugin.getPluginContainer().descriptor().getName(), identifier);
            return false;
        }

        this.registry.put(identifier, processor);
        return true;
    }

    protected void registerDefaultPlaceholders() {
        registerPlaceholder(this, "x", (player, params) -> String.valueOf(Math.floor(player.getLocation().x())));
        registerPlaceholder(this, "y", (player, params) -> String.valueOf(Math.floor(player.getLocation().y())));
        registerPlaceholder(this, "z", (player, params) -> String.valueOf(Math.floor(player.getLocation().z())));
        registerPlaceholder(this, "player_name", (player, params) -> player.getOriginName());
        registerPlaceholder(this, "player_display_name", (player, params) -> player.getDisplayName());
        registerPlaceholder(this, "dimension", (player, params) -> player.getLocation().dimension().getDimensionInfo().toString());
        registerPlaceholder(this, "dimension_id", (player, params) -> String.valueOf(player.getLocation().dimension().getDimensionInfo().dimensionId()));
        registerPlaceholder(this, "ping", (player, params) -> String.valueOf(player.getPing()));
        registerPlaceholder(this, "mc_version", (player, params) -> player.getLoginData().getGameVersion());
        registerPlaceholder(this, "online", (player, params) -> String.valueOf(Server.getInstance().getPlayerManager().getPlayerCount()));
        registerPlaceholder(this, "max_online", (player, params) -> String.valueOf(Server.getInstance().getPlayerManager().getMaxPlayerCount()));
        registerPlaceholder(this, "address", (player, params) -> player.getSocketAddress().toString());
        registerPlaceholder(this, "runtime_id", (player, params) -> String.valueOf(player.getRuntimeId()));
        registerPlaceholder(this, "exp_level", (player, params) -> String.valueOf(player.getExperienceLevel()));
        registerPlaceholder(this, "exp_progress", (player, params) -> String.valueOf(player.getExperienceProgress()));
        registerPlaceholder(this, "game_mode", (player, params) -> player.getGameMode().toString().toLowerCase());
        registerPlaceholder(this, "xuid", (player, params) -> player.getLoginData().getXuid());
        registerPlaceholder(this, "uuid", (player, params) -> player.getLoginData().getUuid().toString());
        registerPlaceholder(this, "device_os", (player, params) -> player.getLoginData().getDeviceInfo().device().getName());
        registerPlaceholder(this, "locale", (player, params) -> player.getLoginData().getLangCode().toString());
        registerPlaceholder(this, "date", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        registerPlaceholder(this, "time", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
        registerPlaceholder(this, "datetime", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        registerPlaceholder(this, "year", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")));
        registerPlaceholder(this, "month", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")));
        registerPlaceholder(this, "day", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd")));
        registerPlaceholder(this, "hour", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        registerPlaceholder(this, "minute", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm")));
        registerPlaceholder(this, "second", (player, params) -> LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")));
    }
}
package org.allaymc.papi;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class PAPICommand extends Command {
    public PAPICommand() {
        super("papi", "PlaceholderAPI command", "papi.command");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("parse")
                .str("text")
                .playerTarget("player").optional()
                .exec(context -> {
                    String text = context.getResult(1);
                    List<EntityPlayer> players = context.getResult(2);
                    if (players.size() > 1) {
                        context.addTooManyTargetsError();
                        return context.fail();
                    }

                    EntityPlayer player = null;
                    if (players.size() == 1) {
                        player = players.getFirst();
                    }

                    context.addOutput(PlaceholderAPI.getAPI().setPlaceholders(player, text));
                    return context.success();
                })
                .root()
                .key("list")
                .exec(context -> {
                    context.addOutput("Available placeholders:");
                    for (var identifier : PlaceholderAPI.getAPI().getRegisteredIdentifiers()) {
                        context.addOutput("- " + identifier);
                    }

                    return context.success();
                });
    }
}

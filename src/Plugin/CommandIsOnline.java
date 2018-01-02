package Plugin;

import PluginReference.MC_Command;
import PluginReference.MC_Player;

import java.util.Arrays;
import java.util.List;

public class CommandIsOnline implements MC_Command {

    private MyPlugin main;

    public CommandIsOnline(MyPlugin main) {
        this.main = main;
    }

    @Override
    public String getCommandName() {
        return "isonline";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(new String[]{"checkonline"});
    }

    @Override
    public String getHelpLine(MC_Player mc_player) {
        return "/isonline - Let you know if the player is online.";
    }

    @Override
    public void handleCommand(MC_Player plr, String[] args) {
        if (plr == null) return; // Don't let the console use this command

        if (args.length == 1) {
            // We have 1 argument, that's fine, do it

            // If the user only entered a partial name, let fix it and search for a matching name
            String name = this.main.server.getPlayerExactName(args[0]);

            // Have we found a player?
            if (name == null) {
                plr.sendMessage("No matching player found.");
                return;
            }

            // Let's get the user with the name
            MC_Player tofind = this.main.server.getOnlinePlayerByName(name);

            // Is the player online?
            if (tofind == null) {
                plr.sendMessage("The player is offline.");
            } else {
                // Let's send a response to the command issuer.
                plr.sendMessage("Yaaay "+name+" is online on the server!");

                // Let's send a message to the queried player.
                tofind.sendMessage("Yaaay "+plr.getName()+" now knows that you are online!");
            }
        } else {
            // The command is not used right, let the user know
            plr.sendMessage(this.getHelpLine(plr));
        }
    }

    @Override
    public boolean hasPermissionToUse(MC_Player plr) {
        if (plr == null) return false;
        return plr.hasPermission("yourrainbowplugin.commands.isonline");
    }

    @Override
    public List<String> getTabCompletionList(MC_Player plr, String[] args) {
        if (args.length == 1 && plr != null) { // Make sure this is the first argument and it isn't called from the console
            List<String> names = this.main.server.getMatchingOnlinePlayerNames(args[0]);
            // Uncomment this if you want to check that completion list works, cheaty, but the only way to test alone =P
            //names.addAll(Arrays.asList(new String[] {"MyName", "AnotherName", "AnotherName2"}));

            // Have we found any names?
            if (names.isEmpty()) plr.sendMessage("No player found on server with a name like this.");
                // Found some names, return it
            else return names;
        }

        return null;
    }
}

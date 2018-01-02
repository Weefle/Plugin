package Plugin;

import Plugin.CommandIsOnline;
import PluginReference.*;

public class MyPlugin extends PluginBase {

    public MC_Server server;

    @Override
    public void onStartup(MC_Server server) {
        this.server = server;
        server.registerCommand(new CommandIsOnline(this));
    }

    @Override
    public void onShutdown() {

    }

    @Override
    public PluginInfo getPluginInfo() {
        PluginInfo info = new PluginInfo();
        info.name = "Plugin";
        info.description = "Plugin";
        return info;
    }
}

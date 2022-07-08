package sk.adonikeoffice.epichomes;

import org.mineacademy.fo.MinecraftVersion;
import org.mineacademy.fo.plugin.SimplePlugin;
import sk.adonikeoffice.epichomes.home.Home;
import sk.adonikeoffice.epichomes.home.HomeCommand;

public final class EpicHomesPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Home.createHome("testHome");
	}

	@Override
	protected void onReloadablesStart() {
		Home.loadHomes();

		this.registerCommand(new HomeCommand());
	}

	@Override
	public MinecraftVersion.V getMinimumVersion() {
		return MinecraftVersion.V.v1_8;
	}

}
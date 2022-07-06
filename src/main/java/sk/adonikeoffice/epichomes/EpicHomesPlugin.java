package sk.adonikeoffice.epichomes;

import org.mineacademy.fo.plugin.SimplePlugin;
import sk.adonikeoffice.epichomes.home.Home;

public final class EpicHomesPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {

	}

	@Override
	protected void onReloadablesStart() {
		Home.loadHomes();
	}

}
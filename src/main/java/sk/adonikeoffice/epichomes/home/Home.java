package sk.adonikeoffice.epichomes.home;

import lombok.Getter;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.settings.ConfigItems;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.Collection;
import java.util.Set;

@Getter
public class Home extends YamlConfig {

	private static final ConfigItems<Home> loadedHomes = ConfigItems.fromFolder("homes", Home.class);

	public Home(final String name) {
		this.loadConfiguration(NO_DEFAULT, "homes/" + name + ".yml");
	}

	public static void createHome(final String name) {
		final Home home = loadedHomes.loadOrCreateItem(name, () -> new Home(name));

		home.save();
	}

	public static void removeHome(final String name) {
		removeHome(findHome(name));
	}

	public static void removeHome(final Home home) {
		loadedHomes.removeItem(home);
	}

	public static boolean isLoaded(final String name) {
		return loadedHomes.isItemLoaded(name);
	}

	public static void loadHomes() {
		loadedHomes.loadItems();

		final int homesSize = getLoadedHomes().size();

		if (homesSize > 0)
			Common.log(
					Common.chatLine(),
					"Loaded " + Common.plural(homesSize, "home"),
					Common.chatLine()
			);
	}

	public static Home findHome(final String name) {
		for (final Home home : getLoadedHomes())
			if (home.getName().equals(name))
				return home;

		return null;
	}

	public static Collection<Home> getLoadedHomes() {
		return loadedHomes.getItems();
	}

	public static Set<String> getHomesNames() {
		return loadedHomes.getItemNames();
	}

}
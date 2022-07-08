package sk.adonikeoffice.epichomes.home;

import lombok.Getter;
import org.bukkit.Location;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.ConfigItems;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
public class Home extends YamlConfig {

	private static final ConfigItems<Home> loadedHomes = ConfigItems.fromFolder("homes", Home.class);

	private String description;
	private CompMaterial material;
	private Location location;
	private UUID owner;

	public Home(final String homeName) {
		this.loadConfiguration(NO_DEFAULT, "homes/" + homeName + ".yml");
	}

	@Override
	protected void onLoad() {
		this.description = this.getString("Description", "Not set.");
		this.material = this.getMaterial("Material", CompMaterial.BOOK);
		this.location = this.isSet("Location") ? this.getLocation("Location") : null;
		this.owner = this.isSet("Owner") ? this.get("Owner", UUID.class) : null;
	}

	@Override
	protected void onSave() {
		super.onSave();
	}

	@Override
	public SerializedMap saveToMap() {
		final SerializedMap map = new SerializedMap();

		map.putIf("Description", this.description != null ? this.description : null);
		map.putIf("Material", this.material != null ? this.material : null);
		map.putIf("Location", this.location != null ? this.location : null);
		map.putIf("Owner", this.owner != null ? this.owner : null);

		return map;
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
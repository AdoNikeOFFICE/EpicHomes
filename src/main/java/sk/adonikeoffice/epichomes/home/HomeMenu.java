package sk.adonikeoffice.epichomes.home;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;

import java.util.stream.Collectors;

public class HomeMenu extends MenuPagged<Home> {

	public HomeMenu(final Player player) {
		super(9 * 3, Home.getLoadedHomes().stream().filter(home -> home.getOwner().equals(player.getUniqueId())).collect(Collectors.toList()));

		this.setTitle(player.getName() + "'s homes");
	}

	@Override
	protected ItemStack convertToItemStack(final Home item) {
		return ItemCreator.of(
				item.getMaterial(),
				item.getName(),
				"&8'" + item.getDescription() + "'",
				" "
		).makeMenuTool();
	}

	@Override
	protected void onPageClick(final Player player, final Home item, final ClickType click) {

	}

	public static void showTo(final Player player) {
		new HomeMenu(player).displayTo(player);
	}

}
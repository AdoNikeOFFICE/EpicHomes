package sk.adonikeoffice.epichomes.home;

import org.mineacademy.fo.command.SimpleCommand;

public class HomeCommand extends SimpleCommand {

	public HomeCommand() {
		super("home|homes");

		this.setPermission(null);
	}

	@Override
	protected void onCommand() {
		this.checkConsole();

		HomeMenu.showTo(this.getPlayer());
	}

}
package no.runsafe.tripwire.wires;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.log.IDebug;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.text.ChatColour;
import no.runsafe.tripwire.database.TripwireLogRepository;

import java.util.ArrayList;

public class WireBase implements IConfigurationChanged
{
	public WireBase(
		TripwireLogRepository logRepository,
		IDebug debugger, IConsole console, IServer server)
	{
		this.debugger = debugger;
		this.console = console;
		logging = logRepository;
		this.server = server;
	}

	public void Tripped(IPlayer player, int category, String message)
	{
		logging.LogWarning(player.getName(), category, message);
		console.logWarning(ChatColour.RED + "[TripWire category %d] " + ChatColour.AQUA + "%s" + ": " + ChatColour.MAGIC + "%s", category, player.getName(), message);
		if (alertLevel <= category)
		{
			debugger.debugFine("Sending alerts");
			ArrayList<IPlayer> players = new ArrayList<IPlayer>();
			for (IPlayer user : server.getOnlinePlayers())
				if (user.hasPermission("tripwire.notify"))
					players.add(user);
			if (players.size() == 0)
				debugger.debugFine("Found no players");
			else
				debugger.debugFine(String.format("Found %d players", players.size()));
			for (IPlayer alert : players)
				alert.sendMessage(ChatColour.RED + "[TripWire] " + ChatColour.AQUA + player.getName() + ": " + ChatColour.LIGHT_PURPLE + message);
		}
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		alertLevel = configuration.getConfigValueAsInt("tripwire.alertlevel");
	}

	private final TripwireLogRepository logging;
	private final IDebug debugger;
	private final IConsole console;
	private final IServer server;
	private int alertLevel = 0;
}

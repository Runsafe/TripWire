package no.runsafe.tripwire.wires;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IDebug;
import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.text.ChatColour;
import no.runsafe.tripwire.database.TripwireLogRepository;

import java.util.ArrayList;

public class WireBase implements IConfigurationChanged
{
	public WireBase(
		TripwireLogRepository logRepository,
		IDebug output, IServer server)
	{
		this.output = output;
		logging = logRepository;
		this.server = server;
	}

	public void Tripped(IPlayer player, int category, String message)
	{
		output.logWarning(ChatColour.RED + "[TripWire category %d] " + ChatColour.AQUA + "%s" + ": " + ChatColour.MAGIC + "%s", category, player.getName(), message);
		if (alertLevel <= category)
		{
			output.debugFine("Sending alerts");
			ArrayList<IPlayer> players = new ArrayList<IPlayer>();
			for (IPlayer user : server.getOnlinePlayers())
				if (user.hasPermission("tripwire.notify"))
					players.add(user);
			if (players.size() == 0)
				output.debugFine("Found no players");
			else
				output.debugFine(String.format("Found %d players", players.size()));
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
	private final IDebug output;
	private final IServer server;
	private int alertLevel = 0;
}

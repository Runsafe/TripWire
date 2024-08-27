package no.runsafe.tripwire.wires;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.tripwire.database.TripwireLogRepository;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.log.IDebug;
import no.runsafe.framework.api.player.IPlayer;

public class BlockPlace extends WireBase implements IBlockPlace
{
	public BlockPlace(TripwireLogRepository logRepository, IDebug output, IConsole console, IServer server)
	{
		super(logRepository, output, console, server);
	}

	@Override
	public boolean OnBlockPlace(IPlayer player, IBlock block)
	{
		//Check if player is trying to place a suspicious "Copy Me" block that's usually intended to soft-ban players.
		if (block.getMaterial().getName().toLowerCase().contains("copy me"))
			Tripped(player, 20, "Placed Copy Me block at: " + block.getLocation());

		return true;
	}

}

package no.runsafe.tripwire.wires;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.block.IBlockBreak;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.log.IDebug;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.tripwire.database.TripwireLogRepository;

public class BlockBreakAir extends WireBase implements IBlockBreak
{
	public BlockBreakAir(TripwireLogRepository logRepository, IDebug output, IConsole console, IServer server)
	{
		super(logRepository, output, console, server);
	}

	@Override
	public boolean OnBlockBreak(IPlayer player, IBlock block)
	{
		if (block.isAir())
			Tripped(player, 1, "Block-break event: Air");
		return true;
	}
}

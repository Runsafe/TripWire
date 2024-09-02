package no.runsafe.tripwire.wires;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.minecraft.IInventoryHolder;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
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
		if (!(block instanceof IInventoryHolder))
			return true;

		RunsafeInventory inventory = ((IInventoryHolder) block).getInventory();
		if (inventory == null || !inventory.getTitle().toLowerCase().contains("copy me"))
			return true;

		Tripped(player, 20, "Placed Copy Me block at: " + block.getLocation());

		return true;
	}

}

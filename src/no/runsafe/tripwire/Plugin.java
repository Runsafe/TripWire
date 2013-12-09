package no.runsafe.tripwire;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.tripwire.database.TripwireLogRepository;
import no.runsafe.tripwire.wires.BlockBreakAir;
import no.runsafe.tripwire.wires.DiggingStraightDown;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{
		addComponent(TripwireLogRepository.class);
		addComponent(BlockBreakAir.class);
		addComponent(DiggingStraightDown.class);
	}
}

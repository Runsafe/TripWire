package no.runsafe.tripwire;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.features.Database;
import no.runsafe.framework.features.Events;
import no.runsafe.tripwire.database.TripwireLogRepository;
import no.runsafe.tripwire.wires.BlockBreakAir;
import no.runsafe.tripwire.wires.DiggingStraightDown;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{
		// Framework features
		addComponent(Events.class);
		addComponent(Database.class);

		// Plugin components
		addComponent(TripwireLogRepository.class);
		addComponent(BlockBreakAir.class);
		addComponent(DiggingStraightDown.class);
	}
}

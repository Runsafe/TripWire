package no.runsafe.tripwire.database;

import no.runsafe.framework.api.database.ISchemaUpdate;
import no.runsafe.framework.api.database.Repository;
import no.runsafe.framework.api.database.SchemaUpdate;

public class TripwireLogRepository extends Repository
{
	public void LogWarning(String playerName, int category, String message)
	{
		database.execute(
			"INSERT INTO tripwire_events (player, category, message) VALUES (?, ?, ?)",
			playerName,
			category,
			message
		);
	}

	@Override
	public String getTableName()
	{
		return "tripwire_events";
	}

	@Override
	public ISchemaUpdate getSchemaUpdateQueries()
	{
		ISchemaUpdate update = new SchemaUpdate();
		update.addQueries(
			"CREATE TABLE IF NOT EXISTS `tripwire_events` (" +
				"  `id` bigint(20) NOT NULL AUTO_INCREMENT," +
				"  `player` varchar(50) NOT NULL," +
				"  `category` int(11) NOT NULL," +
				"  `message` varchar(150) NOT NULL," +
				"  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
				"  PRIMARY KEY (`id`)," +
				"  KEY `player` (`player`)" +
				")"
		);
		return update;
	}
}

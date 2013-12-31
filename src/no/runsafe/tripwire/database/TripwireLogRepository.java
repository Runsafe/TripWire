package no.runsafe.tripwire.database;

import no.runsafe.framework.api.database.IDatabase;
import no.runsafe.framework.api.database.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TripwireLogRepository extends Repository
{
	public TripwireLogRepository(IDatabase database)
	{
		db = database;
	}

	public void LogWarning(String playerName, int category, String message)
	{
		db.execute(
			"INSERT INTO tripwire_events (player, category, message) VALUES (?, ?, ?)",
			playerName,
			category,
			message
		);
	}

	private IDatabase db;

	@Override
	public String getTableName()
	{
		return "tripwire_events";
	}

	@Override
	public HashMap<Integer, List<String>> getSchemaUpdateQueries()
	{
		HashMap<Integer, List<String>> revisions = new HashMap<Integer, List<String>>();
		List<String> sql = new ArrayList<String>();
		sql.add("CREATE TABLE IF NOT EXISTS `tripwire_events` (" +
			"  `id` bigint(20) NOT NULL AUTO_INCREMENT," +
			"  `player` varchar(50) NOT NULL," +
			"  `category` int(11) NOT NULL," +
			"  `message` varchar(150) NOT NULL," +
			"  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
			"  PRIMARY KEY (`id`)," +
			"  KEY `player` (`player`)" +
			")");
		revisions.put(1, sql);
		return revisions;
	}
}

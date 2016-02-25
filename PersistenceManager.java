package database;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DDL_GENERATION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DROP_AND_CREATE;

public class PersistenceManager {
	private PersistenceManager(){}
	
	private static EntityManagerFactory emf = null;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		Map<String, String> properties = new HashMap<String, String>();

		properties.put(DDL_GENERATION, DROP_AND_CREATE);

		if(PersistenceManager.emf == null) {
			PersistenceManager.emf = Persistence.createEntityManagerFactory("AuctionJPA", properties);
		}
		return PersistenceManager.emf;
	}

	public static void closeEntityManagerFactory() {
		if(PersistenceManager.emf != null && PersistenceManager.emf.isOpen()) 
			PersistenceManager.emf.close();
	}
}

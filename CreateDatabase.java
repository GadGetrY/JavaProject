package testing;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import auction.IBid;
import database.DaoFactory;
import database.GenericDao;

public class CreateDatabase {
	public static void main(String... args) {
		EntityManager em = DaoFactory.getInstance().getEm();
		
		EntityTransaction tx = em.getTransaction();
		
		GenericDao<IBid> jpaBidDao = DaoFactory.getInstance().getBidDAO(); 
		
		tx.begin();
		// populate data
		
		jpaBidDao.persist(ObjectFactory.INSTANCE.danielsBids(), em);
		jpaBidDao.persist(ObjectFactory.INSTANCE.alexBids(), em);
	
		tx.commit();
		
		DaoFactory.getInstance().closeEm();
		DaoFactory.getInstance().closeEntityManagerFactory();
		
		return;
	}
}

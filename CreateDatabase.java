package testing;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import auction.IAuctionItem;
import auction.IAuctionUser;
import auction.IBid;
import database.DaoFactory;
import database.GenericDao;

public class CreateDatabase {
	public static void main(String... args) {
		EntityManager em = DaoFactory.getInstance().getEm();
		
		EntityTransaction tx = em.getTransaction();
		
		GenericDao<IAuctionUser> jpaAuctionUserDao = DaoFactory.getInstance().getAuctionUserDAO();
		GenericDao<IAuctionItem> jpaAuctionItemDao = DaoFactory.getInstance().getAuctionItemDAO(); 
		GenericDao<IBid> jpaBidDao = DaoFactory.getInstance().getBidDAO(); 
		
		tx.begin();
		// populate data
		
		jpaAuctionUserDao.persist(ObjectFactory.INSTANCE.daniel(), em);
		jpaAuctionUserDao.persist(ObjectFactory.INSTANCE.alex(), em);
		//jpaAuctionItemDao.persist(ObjectFactory.INSTANCE.phoneItem(), em);
		//jpaBidDao.persist(ObjectFactory.INSTANCE.alexBids(), em);
	
		tx.commit();
		
		DaoFactory.getInstance().closeEm();
		DaoFactory.getInstance().closeEntityManagerFactory();
		
		return;
	}
}

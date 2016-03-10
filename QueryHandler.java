package testing;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import auction.AuctionUser;
import auction.AuctionItem;
import auction.Bid;
import auction.IAuctionItem;
import auction.IAuctionUser;
import auction.IBid;
import database.DaoFactory;
import database.GenericDao;

public enum QueryHandler {
	INSTANCE;
	
	public IBid changeBidScenario(
			GenericDao<IBid> jpaBidDao,
			EntityManager em) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		IBid found = QueryHandler.INSTANCE.findBidByIdScenario(jpaBidDao, em);
		if(found != null) {
			found.setAmount(60f);
		}
		
		tx.commit();
		
		return found;
	}

	public IBid findBidByIdScenario(
			GenericDao<IBid> jpaBidDao,
			EntityManager em) {
		IBid search = new Bid().setId(1);
		return jpaBidDao.findById(search, em);
	}
	
	public IBid findBidScenario(
			GenericDao<IBid> jpaBidDao,
			EntityManager em) {
		IBid search = new Bid()
			.setAmount(50f)
			.setBidder(ObjectFactory.INSTANCE.alex())
			.setItem(ObjectFactory.INSTANCE.phoneItem());
		
		return jpaBidDao.find(search, em);
	}
	
	public IAuctionUser findAuctionUserByIdScenario(
			GenericDao<IAuctionUser> jpaAuctionUserDao,
			EntityManager em) {
		IAuctionUser search = new AuctionUser().setAuctionUserId(1);
		
		return jpaAuctionUserDao.findById(search, em);
	}
	
	public IAuctionItem findAuctionItemByIdScenario(
			GenericDao<IAuctionItem> jpaAuctionItemDao,
			EntityManager em) {
		IAuctionItem search = new AuctionItem().setAuctionItemId(1);
		
		return jpaAuctionItemDao.findById(search, em);
	}

	public IAuctionUser findAuctionUserScenario(
			GenericDao<IAuctionUser> jpaAuctionUserDao,
			EntityManager em) {
		IAuctionUser search = ObjectFactory.INSTANCE.alex();
		
		return jpaAuctionUserDao.find(search, em);
	}
	
	public IAuctionUser changeUserDetails(
			GenericDao<IAuctionUser>jpaAuctionUserDao,
			EntityManager em 
			) {

		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		IAuctionUser found =
				QueryHandler.INSTANCE.findAuctionUserByIdScenario(jpaAuctionUserDao, em);
		
		if(found != null) {
			found.getName().setFirstName("Jury");
			found.getName().setLastName("Doiﬂling");
			found.setEmail("jury@gmail.com");
			found.setPassword("jury1234");
			found.setUsername("juryUser");
		}
		
		tx.commit();
		
		return found;
	}
	
	public IAuctionItem changeItemDescription(
		GenericDao<IAuctionItem>jpaAuctionItemDao,
		EntityManager em
		) {
		

		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		IAuctionItem found =
				QueryHandler.INSTANCE.findAuctionItemByIdScenario(jpaAuctionItemDao, em);
		
		if(found != null) {
			found.setDescription("Gutes Ger‰t!");
		}
		
		tx.commit();
		
		return found;
	}
	
	public IBid findBidByUserScenario(
			GenericDao<IBid> jpaBidDao,
			EntityManager em
			){
		
	
		IBid search = new Bid()
			.setBidder(ObjectFactory.INSTANCE.alex());
		
		return jpaBidDao.find(search, em);
	}
	
	public IBid findBidForAuctionItemScenario(
			GenericDao<IBid>jpaBidDao,
			EntityManager em){
		IBid search = new Bid()
			.setItem(ObjectFactory.INSTANCE.tabletItem());
		
		return jpaBidDao.find(search, em);
	}
	
	public IAuctionItem findAuctionItemByUserScenario(
			GenericDao<IAuctionItem>jpaAuctionItemDao,
			EntityManager em){
		IAuctionItem search = new AuctionItem()
			.setSeller(ObjectFactory.INSTANCE.daniel());
		
		return jpaAuctionItemDao.find(search, em);
	}
	
	public IAuctionItem findAuctionItem(
			GenericDao<IAuctionItem>jpaAuctionItemDao,
			EntityManager em){
			
		IAuctionItem search = new AuctionItem()
			.setAuctionItemId(1)
			.setDescription(ObjectFactory.INSTANCE.phoneItem().getDescription());
					
		return jpaAuctionItemDao.find(search, em);
	}
	
	
	public IAuctionItem bidForAuctionItem(
			GenericDao<IAuctionItem> jpaAuctionItemDao,
			EntityManager em) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		IAuctionItem found =
				QueryHandler.INSTANCE.findAuctionItemByIdScenario(jpaAuctionItemDao, em);
		
		if(found != null) {
			found.add(new Bid()
					.setAmount(5)
					.setBidder(ObjectFactory.INSTANCE.alex())
					.setDatetime(LocalDate.now())
					.setItem(found));
		}
		
		tx.commit();
		
		return found;
	}
	
	public IAuctionUser linkAuctionItemToAuctionUserScenario(
			GenericDao<IAuctionUser> jpaAuctionUserDao,
			EntityManager em) {
		
		IAuctionUser found = null;
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		found = jpaAuctionUserDao.findById(new AuctionUser().setAuctionUserId(1), em);
		if(found != null) {
			found.add(ObjectFactory.INSTANCE.phoneItem());
		}
		
		tx.commit();
		
		return found;
	}
	
	public IAuctionItem cancelBidForAuctionItem(
			GenericDao<IAuctionItem> jpaAuctionItemDao,
			GenericDao<IBid>jpaBidDao,
			EntityManager em) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		IAuctionItem found =
				QueryHandler.INSTANCE.findAuctionItemByIdScenario(jpaAuctionItemDao, em);
		
		if(found != null) {
			found.remove(QueryHandler.INSTANCE.findBidByUserScenario(jpaBidDao, em));
		}
		
		tx.commit();
		
		return found;
	}
	
	public void addAuctionItem(
			GenericDao<IAuctionItem>jpaAuctionItemDao,
			EntityManager em){
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		ObjectFactory.INSTANCE.alex().add(new AuctionItem()
				.setDescription("k.A")
				.setSeller(ObjectFactory.INSTANCE.alex())
				.setEnds(LocalDate.of(2016, 02, 25)))   ;		
		
		tx.commit();
	}
	
	public void removeAuctionItem(
			GenericDao<IAuctionUser> jpaAuctionUserDao,
			GenericDao<IAuctionItem> jpaAuctionItemDao,
			EntityManager em) {
		
		EntityTransaction tx = em.getTransaction();
		
		IAuctionUser user = QueryHandler.INSTANCE.findAuctionUserScenario(jpaAuctionUserDao, em);
		IAuctionItem found = QueryHandler.INSTANCE.findAuctionItemByIdScenario(jpaAuctionItemDao, em);
		
		if(user != null) {
			tx.begin();
			user.remove(found);
			tx.commit();
		}
	}
	
	/*public IAuctionUser register(IAuctionUser auctionUser){
		EntityManager em = DaoFactory.getInstance().getEm();
		IAuctionUser user = DaoFactory.getInstance().getAuctionUserDAO().add(auctionUser, em);

		return user;
	}*/
	
	public static void main(String[] args) {
		//GenericDao<IBid> jpaBidDao = DaoFactory.getInstance().getBidDAO();
		GenericDao<IAuctionUser> jpaAuctionUserDao = DaoFactory.getInstance().getAuctionUserDAO();
		GenericDao<IAuctionItem> jpaAuctionItemDao = DaoFactory.getInstance().getAuctionItemDAO();
		GenericDao<IBid> jpaBidDao = DaoFactory.getInstance().getBidDAO();
		EntityManager em = DaoFactory.getInstance().getEm();
		//EntityTransaction tx = em.getTransaction();
		
		QueryHandler.INSTANCE.linkAuctionItemToAuctionUserScenario(jpaAuctionUserDao, em);
		QueryHandler.INSTANCE.addAuctionItem(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.bidForAuctionItem(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.cancelBidForAuctionItem(jpaAuctionItemDao, jpaBidDao, em);
		QueryHandler.INSTANCE.changeBidScenario(jpaBidDao, em);
		QueryHandler.INSTANCE.changeItemDescription(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.changeUserDetails(jpaAuctionUserDao, em);
		QueryHandler.INSTANCE.findAuctionItem(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.findAuctionItemByIdScenario(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.findAuctionItemByUserScenario(jpaAuctionItemDao, em);
		QueryHandler.INSTANCE.findBidByIdScenario(jpaBidDao, em);
		QueryHandler.INSTANCE.findBidByUserScenario(jpaBidDao, em);
		QueryHandler.INSTANCE.findBidForAuctionItemScenario(jpaBidDao, em);
		QueryHandler.INSTANCE.findBidScenario(jpaBidDao, em);
		QueryHandler.INSTANCE.removeAuctionItem(jpaAuctionUserDao, jpaAuctionItemDao, em);
	
		//IBid alexBids = QueryHandler.INSTANCE.findBidUsingTextAndReceiverScenario(jpaBidDao, em);
		//IAuctionUser alex = QueryHandler.INSTANCE.findAuctionUserScenario(jpaAuctionUserDao, em);
		//IBid alexBids = QueryHandler.INSTANCE.changeBidScenario(jpaBidDao, em);
		
		//tx.begin();
		//bidDao.persist(ObjectFactory.INSTANCE.alexBids(), em);
		//tx.commit();
		
		DaoFactory.getInstance().closeEm();
		DaoFactory.getInstance().closeEntityManagerFactory();
		return;
	}
}

package database;

import java.util.List;

import javax.persistence.EntityManager;

import auction.AuctionUser;
import auction.IAuctionUser;

public final class AuctionUserDAO implements GenericDao<IAuctionUser> {

	private AuctionUserDAO() {
		
	}
	
	private static AuctionUserDAO INSTANCE = null;
	
	public static AuctionUserDAO getInstance() {
		if(AuctionUserDAO.INSTANCE == null) {
			AuctionUserDAO.INSTANCE = new AuctionUserDAO();
		}
		
		return AuctionUserDAO.INSTANCE;
	}
	
	@Override
	public IAuctionUser find(IAuctionUser entity, EntityManager em) {
		IAuctionUser found = null;
		
		List<IAuctionUser> auctionUsers = em.createQuery("auctionuser.findPersonsUsingUsername")
				.setParameter("email", entity.getEmail())
				.setParameter("firstname", entity.getName().getFirstName())
				.setParameter("lastname", entity.getName().getLastName())
				.setParameter("password", entity.getPassword())
				.setParameter("username", entity.getUsername())
				.getResultList();
		
		if(!auctionUsers.isEmpty()) {
			found = auctionUsers.get(0);
		}
		return found;
	}

	@Override
	public IAuctionUser findById(IAuctionUser entity, EntityManager em) {
		return (IAuctionUser) em.find(AuctionUser.class, entity.getAuctionUserId());
	}

	@Override
	public void persist(IAuctionUser entity, EntityManager em) {
		em.persist(entity);
	}

	@Override
	public void remove(IAuctionUser entity, EntityManager em) {
		em.remove(entity);
	}
}

package database;

import java.util.List;

import javax.persistence.EntityManager;

import auction.Bid;
import auction.IBid;

public final class BidDAO implements GenericDao<IBid> {

	private BidDAO() {
		
	}
	
	private static BidDAO INSTANCE = null;
	
	public static BidDAO getInstance() {
		if(BidDAO.INSTANCE == null) {
			BidDAO.INSTANCE = new BidDAO();
		}
		
		return BidDAO.INSTANCE;
	}
	
	@Override
	public IBid find(IBid entity, EntityManager em) {
		IBid found = null;
	
		List<IBid> bids = em.createQuery("bid.findBidUsingBidderUsername")
				.setParameter("amount", entity.getAmount())
				.setParameter("bidderUsername", entity.getBidder().getUsername())
				.setParameter("datetime", entity.getDatetime().toString())
				.setParameter("item", entity.getItem().getDescription())
				.getResultList();
		
		if(!bids.isEmpty()) {
			found = bids.get(0);
		}
		return found;
	}

	@Override
	public IBid findById(IBid entity, EntityManager em) {
		return (IBid) em.find(Bid.class, entity.getId());
	}

	@Override
	public void persist(IBid entity, EntityManager em) {
		em.persist(entity);
	}

	@Override
	public void remove(IBid entity, EntityManager em) {
		em.remove(entity);
	}
}

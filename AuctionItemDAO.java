package database;

import java.util.List;

import javax.persistence.EntityManager;

import auction.AuctionItem;
import auction.IAuctionItem;

public final class AuctionItemDAO implements GenericDao<IAuctionItem> {

	private AuctionItemDAO() {
		
	}
	
	private static AuctionItemDAO INSTANCE = null;
	
	public static AuctionItemDAO getInstance() {
		if(AuctionItemDAO.INSTANCE == null) {
			AuctionItemDAO.INSTANCE = new AuctionItemDAO();
		}
		
		return AuctionItemDAO.INSTANCE;
	}
	
	@Override
	public IAuctionItem find(IAuctionItem entity, EntityManager em) {
		IAuctionItem found = null;
		
		List<IAuctionItem> auctionItems = em.createQuery("auctionitem.findAuctionItemUsingSellerUsername")
				.setParameter("description", entity.getDescription())
				.setParameter("ends", entity.getEnds())
				.setParameter("seller", entity.getSeller())
				.setParameter("bids", entity.getBids())
				.setParameter("successfulBid", entity.getSuccessfulBid())
				.getResultList();
		
		if(!auctionItems.isEmpty()) {
			found = auctionItems.get(0);
		}
		return found;
	}

	@Override
	public IAuctionItem findById(IAuctionItem entity, EntityManager em) {
		return (IAuctionItem) em.find(AuctionItem.class, entity.getAuctionItemId());
	}

	@Override
	public IAuctionItem persist(IAuctionItem entity, EntityManager em) {
		em.persist(entity);
		return entity;
	}

	@Override
	public IAuctionItem remove(IAuctionItem entity, EntityManager em) {
		em.remove(entity);
		return null;
	}

}

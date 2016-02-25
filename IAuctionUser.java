package auction;

import java.util.List;



public interface IAuctionUser {
	
	int getAuctionUserId();
	
	IAuctionUser setAuctionUserId(int auctionUserId);
	
	String getUsername();
	
	IAuctionUser setUsername(String username);
	
	String getPassword();
	
	IAuctionUser setPassword(String password);
	
	String getEmail();
	
	IAuctionUser setEmail(String email); 
	
	Name getName();
	
	IAuctionUser setName(Name name); 
	
	boolean equals(Object obj);
	
	boolean add(IAuctionItem auctionItem);
	
	boolean add(IBid bid); 
	
	boolean remove(IAuctionItem auctionItem);
	
	boolean remove(IBid bid);
	
	List<IAuctionItem> getAuctions();
	
	IAuctionUser setAuctions(List<IAuctionItem> auctions);
	
	List<IBid> getBids(); 
	
	IAuctionUser setBids(List<IBid> bids);
	
	int hashCode();
}

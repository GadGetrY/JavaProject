#Erzeugung von IAuctionItem

package auction;

import java.time.LocalDate;
import java.util.List;

public interface IAuctionItem {

	String getDescription();
	
	IAuctionItem setDescription(String description);
	
	LocalDate getEnds(); 
	
	IAuctionItem setEnds(LocalDate ends); 
	
	IAuctionUser getSeller();
	
	IAuctionItem setSeller(IAuctionUser seller); 
	
	boolean equals(Object obj);
	
	int getAuctionItemId();
	
	IAuctionItem setAuctionItemId(int auctionItemId); 
	
	List<IBid> getBids(); 
	
	IAuctionItem setBids(List<IBid> bids);
	
	int hashCode();
	
	boolean remove(IBid bid);
	
    boolean add(IBid bid);
	
	IBid getSuccessfulBid();
	
	IAuctionItem setSuccessfulBid(IBid successfulBid); 
	
	String toString();
	
	IBid findBid(IAuctionUser auctionUser);
	
}

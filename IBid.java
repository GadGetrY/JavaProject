package auction;

import java.time.LocalDate;

public interface IBid {
	
	boolean equals(Object obj);
	
	float getAmount();
	
	IBid setAmount(float amount);
	
	IAuctionUser getBidder();
	
	IBid setBidder(IAuctionUser bidder);
	
	LocalDate getDatetime();
	
	IBid setDatetime(LocalDate dateTime); 
	
	int getId();
	
	IBid setId(int id); 
	
	IAuctionItem getItem();
	
	IBid setItem(IAuctionItem item); 
	
	int hashCode();
	
	String toString();

}

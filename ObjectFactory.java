package testing;

import java.time.LocalDate;

import auction.AuctionItem;
import auction.AuctionUser;
import auction.Bid;
import auction.IAuctionItem;
import auction.IAuctionUser;
import auction.IBid;
import auction.Name;

public enum ObjectFactory {
	INSTANCE; 
	
	public IAuctionUser daniel(){
		
		IAuctionUser daniel = new AuctionUser()
				.setName(new Name("Daniel", "Dees"))
				.setUsername("danielUser")
				.setPassword("daniel1234")
				.setEmail("daniel@gmail.com");
		
		return daniel; 
	}
	
	public IAuctionUser alex(){
		
		IAuctionUser alex = new AuctionUser()
				.setName(new Name("Alex", "Elstner"))
				.setUsername("alex")
				.setPassword("alex1234")
				.setEmail("alex@gmail.com");
		
		return alex;
	}
	
	public IBid danielsBids(){
		
		IBid bid = new Bid()
				.setAmount(50)
				.setBidder(ObjectFactory.INSTANCE.daniel())
				.setDatetime(LocalDate.of(2016, 01, 25))
				.setItem(ObjectFactory.INSTANCE.phoneItem());
			
		return bid;
	};
	
	public IBid alexBids(){
		
		IBid bid = new Bid()
				.setAmount(75)
				.setBidder(ObjectFactory.INSTANCE.alex())
				.setDatetime(LocalDate.of(2016, 01, 25))
				.setItem(ObjectFactory.INSTANCE.tabletItem());
			
		return bid;
	};
	
	public IAuctionItem phoneItem(){
		
		IAuctionItem phoneItem = new AuctionItem()
				.setDescription("Nexus 5X")
				.setSeller(ObjectFactory.INSTANCE.daniel())
				.setEnds(LocalDate.of(2016, 02, 10));
		
		return phoneItem;
	};
	
	public IAuctionItem tabletItem(){
			
		IAuctionItem tabletItem = new AuctionItem()
				.setDescription("Samsung Galaxy 10.1")
				.setSeller(ObjectFactory.INSTANCE.alex())
				.setEnds(LocalDate.of(2016, 01, 28));
			
		return tabletItem;
	}
}
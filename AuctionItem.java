package auction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.annotations.TypeConverter;

@Entity
@Table(name = "AUCTIONITEM")
@NamedQueries({
	@NamedQuery(
			name = "auctionitem.findAuctionItemUsingSellerUsername",
			query = "SELECT i FROM AuctionItem i "
					+ "WHERE i.seller.username = :username"
	)
})
public class AuctionItem implements IAuctionItem {
	
	@Id
	@SequenceGenerator(name = "AUCTIONITEM_SEQ", sequenceName = "AUCTIONITEM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUCTIONITEM_SEQ")
	@Column(name = "AUCTIONITEMID", columnDefinition = "NUMBER(4, 0)")
	private int auctionItemId = 0;
	
	@OneToMany(targetEntity = Bid.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "BID", columnDefinition = "NUMBER(4, 0)")
	private List<IBid> bids = new ArrayList<>();
	
	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2(128)")
	private String description;
	
	@TypeConverter(
			name = "localDateToSqlDate",
			dataType = LocalDate.class,
			objectType = java.sql.Date.class)
	private LocalDate ends;

	@JoinColumn(name = "SELLER", columnDefinition = "NUMBER(4, 0) CONSTRAINT AuctionItem_Seller_NN NOT NULL")
	@OneToOne(targetEntity = Bid.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private IAuctionUser seller;
	
	@JoinColumn(name = "SUCCESSFULBID", columnDefinition = "NUMBER(4, 0)")
	@OneToOne(targetEntity = Bid.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private IBid successfulBid;

	public AuctionItem() {
		super();
	}

	public boolean add(IBid bid) {
		boolean answer;
		answer = this.getBids().add(bid);
		answer = bid.getBidder().add(this);

		return answer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuctionItem other = (AuctionItem) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ends == null) {
			if (other.ends != null)
				return false;
		} else if (!ends.equals(other.ends))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	public IBid findBid(IAuctionUser auctionUser) {
		for (IBid bid : this.getBids()) {
			if (bid.getBidder().equals(auctionUser))
				return bid;
		}
		return null;
	}

	public int getAuctionItemId() {
		return auctionItemId;
	}

	public List<IBid> getBids() {
		return bids;
	}

	public String getDescription() {
		return description;
	}
	
	public LocalDate getEnds() {
		return ends;
	}

	public IAuctionUser getSeller() {
		return seller;
	}

	public IBid getSuccessfulBid() {
		return successfulBid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((ends == null) ? 0 : ends.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		return result;
	}

	
	public boolean remove(IBid bid) {
		boolean answer = false;
		if (this.getBids().contains(bid)) {
			answer = bid.getBidder().remove(this);
		}
		return answer;
	}

	public IAuctionItem setAuctionItemId(int auctionItemId) {
		this.auctionItemId = auctionItemId;
		return this;
	}

	public IAuctionItem setBids(List<IBid> bids) {
		this.bids = bids;
		return this;
	}

	public IAuctionItem setDescription(String description) {
		this.description = description;
		return this;
	}

	public IAuctionItem setEnds(LocalDate ends) {
		this.ends = ends;
		return this;
	}

	public IAuctionItem setSeller(IAuctionUser seller) {
		this.seller = seller;
		return this;
	}

	public IAuctionItem setSuccessfulBid(IBid successfulBid) {
		this.successfulBid = successfulBid;
		return this;
	}

	@Override
	public String toString() {
		return "AuctionItem [description=" + description + ", ends=" + ends
				+ ", seller=" + seller + "]";
	}

}

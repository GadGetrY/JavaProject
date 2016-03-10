package auction;

import java.time.LocalDate;
import javax.persistence.*;

import org.eclipse.persistence.annotations.TypeConverter;

@Entity
@Table(name = "Bid")
@NamedQueries({
	@NamedQuery(
			name = "bid.findBidUsingBidderUsername",
			query = "SELECT b FROM Bid b "
					+ "WHERE b.bidder.username = :username"
	)
})
public class Bid implements IBid {
	@Column(name = "AMOUNT", columnDefinition = "NUMBER(6, 2) CONSTRAINT Bid_Amount_NN NOT NULL")
	private float amount;

	@OneToOne(targetEntity = AuctionUser.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "BIDDER", columnDefinition = "NUMBER(4, 0)")
	private IAuctionUser bidder;
	
	@TypeConverter(
			name = "localDateToSqlDate",
			dataType = LocalDate.class,
			objectType = java.sql.Date.class)
	private LocalDate datetime;
	
	@Id
	@SequenceGenerator(name = "BID_SEQ", sequenceName = "BID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQ")
	@Column(name = "BIDID", columnDefinition = "NUMBER(4, 0)")
	protected int id;

	@OneToOne(targetEntity = AuctionItem.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEM", columnDefinition = "NUMBER(4, 0)")
	private IAuctionItem item;

	public Bid() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (bidder == null) {
			if (other.bidder != null)
				return false;
		} else if (!bidder.equals(other.bidder))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	public float getAmount() {
		return amount;
	}

	public IAuctionUser getBidder() {
		return bidder;
	}

	public LocalDate getDatetime() {
		return datetime;
	}

	public int getId() {
		return id;
	}

	public IAuctionItem getItem() {
		return item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((bidder == null) ? 0 : bidder.hashCode());
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	public IBid setAmount(float amount) {
		this.amount = amount;
		return this;
	}

	public IBid setBidder(IAuctionUser bidder) {
		this.bidder = bidder;
		return this;
	}

	public IBid setDatetime(LocalDate datetime) {
		this.datetime = datetime;
		return this;
	}

	public IBid setId(int id) {
		this.id = id;
		return this;
	}

	public IBid setItem(IAuctionItem item) {
		this.item = item;
		return this;
	}

	@Override
	public String toString() {
		return "Bid [bidder=" + bidder.getName() + "]";
	}
}

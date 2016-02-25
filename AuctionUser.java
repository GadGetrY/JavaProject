package auction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "AUCTIONUSER", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"USERNAME"
		})
})
@NamedQueries({
	@NamedQuery(
			name = "auctionuser.findPersonsUsingUsername",
			query = "SELECT u FROM Auctionuser u "
					+ "WHERE p.Username = :username"
	)
})
public class AuctionUser implements IAuctionUser {
	
	@ManyToMany(targetEntity = AuctionItem.class, mappedBy = "auctionuser")
	private List<IAuctionItem> auctions = new ArrayList<>();
	
	@ManyToMany(targetEntity = Bid.class, mappedBy = "auctionuser")
	private List<IBid> bids = new ArrayList<>();
	
	@Id
	@SequenceGenerator(name = "AUCTIONUSER_SEQ", sequenceName = "AUCTIONUSER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUCTIONUSER_SEQ")
	@Column(name = "AUCTIONUSERID", columnDefinition = "NUMBER(4, 0)")
	private int auctionUserId = 0;
	
	@Column(name = "MAIL", columnDefinition = "VARCHAR2(24) CONSTRAINT AuctionUser_Mail_NN NOT NULL")
	private String email;
	
	private Name name;
	
	@Column(name = "PASSWORD", columnDefinition = "VARCHAR2(24) CONSTRAINT AuctionUser_Password_NN NOT NULL")
	private String password;
	
	@Column(name = "USERNAME", columnDefinition = "VARCHAR2(24) CONSTRAINT AuctionUser_Username_NN NOT NULL")
	private String username;

	public AuctionUser() {
		super();
	}
	
	public AuctionUser(String email, Name name, String password, String username) {
		this();
		this.email = email;
		this.name = name;
		this.password = password;
		this.username = username;
	}

	@Override
	public boolean add(IAuctionItem auctionItem) {
		return this.getAuctions().add(auctionItem);
	}

	@Override
	public boolean add(IBid bid) {
		return this.getBids().add(bid);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuctionUser other = (AuctionUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public List<IAuctionItem> getAuctions() {
		return auctions;
	}

	public int getAuctionUserId() {
		return auctionUserId;
	}

	public List<IBid> getBids() {
		return bids;
	}

	public String getEmail() {
		return email;
	}

	public Name getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public boolean remove(IAuctionItem auctionItem) {
		return this.getBids().remove(auctionItem);
	}

	public boolean remove(IBid bid) {
		return this.getBids().remove(bid);
	}

	public IAuctionUser setAuctions(List<IAuctionItem> auctions) {
		this.auctions = auctions;
		return this;
	}

	public IAuctionUser setAuctionUserId(int auctionUserId) {
		this.auctionUserId = auctionUserId;
		return this;
	}

	public IAuctionUser setBids(List<IBid> bids) {
		this.bids = bids;
		return this;
	}

	public IAuctionUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public IAuctionUser setName(Name name) {
		this.name = name;
		return this;
	}

	public IAuctionUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public IAuctionUser setUsername(String username) {
		this.username = username;
		return this;
	}
}

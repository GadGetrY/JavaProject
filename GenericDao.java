package database;

import javax.persistence.EntityManager;

import auction.IAuctionUser;

public interface GenericDao<E> {
	public E find(E entity, EntityManager em);
	public E findById(E entity, EntityManager em); 
	public E persist(E entity, EntityManager em);
	public E remove(E entity, EntityManager em);
	//public IAuctionUser add(IAuctionUser entity, EntityManager em);
}

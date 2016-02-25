package database;

import javax.persistence.EntityManager;

public interface GenericDao<E> {
	public E find(E entity, EntityManager em);
	public E findById(E entity, EntityManager em); 
	public void persist(E entity, EntityManager em);
	public void remove(E entity, EntityManager em);
}

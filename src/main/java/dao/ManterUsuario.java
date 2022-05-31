package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;

public class ManterUsuario<E> {
	
	public void salvarUsuario(E entidade) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
		entityManager.close();
	}

}

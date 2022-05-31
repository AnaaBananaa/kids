package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelos.Crianca;
import modelos.Responsavel;
import modelos.Sala;

public class ManterResponsavel {

	public void salvarResponsavel(Responsavel entidade) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	
	public void salvarCrianca(Crianca entidade) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		
		entityTransaction.commit();
		entityManager.close();
	}
	
}

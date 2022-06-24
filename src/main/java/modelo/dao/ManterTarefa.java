package modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Tarefa;

public class ManterTarefa {

	public void salvarTarefa(Tarefa tarefa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(tarefa);
		entityTransaction.commit();
		entityManager.close();
	}
	
}

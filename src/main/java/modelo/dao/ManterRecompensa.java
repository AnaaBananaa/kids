package modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

public class ManterRecompensa {
	
	public void salvarRecompensa(Recompensa recompensa, long crianca) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		recompensa.setCrianca(entityManager.find(Crianca.class, crianca));
		entityTransaction.begin();
		recompensa.setSala(UsuarioLogado.getInstance().getUsuario().getSalas().get(0));
		entityManager.persist(recompensa);
		entityTransaction.commit();
		entityManager.close();
	}

}

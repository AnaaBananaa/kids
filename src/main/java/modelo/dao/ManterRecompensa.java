package modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Recompensa;
import modelo.entity.UsuarioLogado;

public class ManterRecompensa {
	
	public void salvarRecompensa(Recompensa recompensa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		recompensa.setSala(UsuarioLogado.getInstance().getUsuario().getSalas().get(0));
		entityManager.persist(recompensa);
		entityTransaction.commit();
		entityManager.close();
	}

}

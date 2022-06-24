package modelo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Tarefa;
import modelo.entity.UsuarioLogado;

public class ManterTarefa {

	public void salvarTarefa(Tarefa tarefa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		tarefa.setSala(UsuarioLogado.getInstance().getUsuario().getSalas().get(0));
		entityManager.persist(tarefa);
		entityTransaction.commit();
		entityManager.close();
	}
	
}

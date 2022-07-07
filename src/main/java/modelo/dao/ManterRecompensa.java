package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Crianca;
import modelo.entity.Recompensa;
import modelo.entity.Tarefa;
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
	
	public List<Recompensa> onBuscarRecompensas() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "SELECT c FROM Recompensa c inner join c.crianca as s where s.id = :pCrianca";
		List<Recompensa> recompensa = entityManager.createQuery(sql).setParameter("pCrianca",UsuarioLogado.getInstance().getUsuario().getId()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return recompensa;
	}
	
	public void atualizaStatus(Crianca crianca) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "UPDATE Crianca as t SET koin = :pKoin where t.id = :pId";
		entityManager.createQuery(sql).setParameter("pId", crianca.getId()).setParameter("pStatus", crianca.getKoin()).executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}
	
}

package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import factory.Factory;
import modelo.entity.Crianca;
import modelo.entity.Sala;
import modelo.entity.Tarefa;
import modelo.entity.UsuarioLogado;

public class ManterTarefa {

	public void salvarTarefa(Tarefa tarefa, long crianca) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		tarefa.setCrianca(entityManager.find(Crianca.class, crianca));
		entityTransaction.begin();
		tarefa.setSala(UsuarioLogado.getInstance().getUsuario().getSalas().get(0));
		entityManager.persist(tarefa);
		entityTransaction.commit();
		entityManager.close();
	}

	public List<Crianca> onBuscarCriancas() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "SELECT c FROM Crianca c inner join c.salas as s where s.idSala = :pSala and c.nome is not null";
		List<Crianca> crianca = entityManager.createQuery(sql)
				.setParameter("pSala", UsuarioLogado.getInstance().getUsuario().getSalas().get(0).getIdSala())
				.getResultList();
		entityTransaction.commit();
		entityManager.close();
		return crianca;
	}

	public List<Tarefa> onBuscarTarefas() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "SELECT c FROM Tarefa c inner join c.crianca as s where s.id = :pCrianca";
		List<Tarefa> tarefa = entityManager.createQuery(sql)
				.setParameter("pCrianca", UsuarioLogado.getInstance().getUsuario().getId()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return tarefa;
	}

	public void atualizaStatus(Tarefa tarefa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String status = "";
		if (tarefa.getStatus().equals("Disponível")) {
			status = "Em execução";
		} else if (tarefa.getStatus().equals("Em execução")) {
			status = "Aguardando Avaliação";
		}
		String sql = "UPDATE Tarefa as t SET status = :pStatus where t.id = :pId";
		entityManager.createQuery(sql).setParameter("pId", tarefa.getId()).setParameter("pStatus", status)
				.executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}

	public List<Tarefa> onBuscarTarefasEspecificas() {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String status = "Disponível";
		String sql = "SELECT c FROM Tarefa c where status = :pStatus";
		List<Tarefa> tarefa = entityManager.createQuery(sql).setParameter("pStatus", status).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return tarefa;
	}

	public void removerTarefa(Tarefa tarefa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "DELETE FROM Tarefa where id = :id";
		entityManager.createQuery(sql).setParameter("id", tarefa.getId()).executeUpdate();
		entityTransaction.commit();
		entityManager.close();

	}

}

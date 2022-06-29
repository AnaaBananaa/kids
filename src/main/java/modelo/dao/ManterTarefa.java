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

	public void salvarTarefa(Tarefa tarefa) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
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
		List<Crianca> crianca = entityManager.createQuery(sql).setParameter("pSala",UsuarioLogado.getInstance().getUsuario().getSalas().get(0).getIdSala()).getResultList();
		return crianca;
	}
	
}

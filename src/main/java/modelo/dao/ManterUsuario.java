package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.cfg.annotations.QueryBinder;

import factory.Factory;
import modelo.entity.Crianca;
import modelo.entity.Responsavel;
import modelo.entity.Usuario;

public class ManterUsuario {

	public void salvarResponsavel(Usuario entidade) {
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

	public List<Responsavel> onBuscarResp(String email, String senha) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		StringBuilder sql = new StringBuilder();
		String jpql = "SELECT r FROM Responsavel r where r.email = :pEmail and r.senha = :pSenha";
		List<Responsavel> resp = entityManager.createQuery(jpql).setParameter("pEmail", email).setParameter("pSenha", senha).getResultList();
		return resp;
	}
	
	public List<Crianca> onBuscarCrianca(String email, String senha) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String jpqlCrianca = "SELECT r FROM Crianca c where c.email = :pEmail and c.senha = :pSenha";
		List<Crianca> crianca = entityManager.createQuery(jpqlCrianca).setParameter("pEmail", email).setParameter("pSenha", senha).getResultList();
		return crianca;
	}

}

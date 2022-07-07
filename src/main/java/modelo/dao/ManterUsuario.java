package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.cfg.annotations.QueryBinder;

import factory.Factory;
import modelo.entity.Crianca;
import modelo.entity.Responsavel;
import modelo.entity.Sala;
import modelo.entity.Usuario;

public class ManterUsuario {

	public void salvarResponsavel(Usuario entidade) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Sala sala = new Sala();
		Random random = new Random();
		int numero = random.nextInt(1000);
		String token = numero + "A";
		sala.setToken(token);
		entityManager.persist(sala);
		entityTransaction.commit();
		entidade.setSalas(new ArrayList<Sala>());
		entidade.getSalas().add(sala);
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
		entityManager.close();
		return resp;
	}
	
	public List<Crianca> onBuscarCrianca(String email, String senha) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String jpqlCrianca = "SELECT c FROM Crianca c where c.email = :pEmail and c.senha = :pSenha";
		List<Crianca> crianca = entityManager.createQuery(jpqlCrianca).setParameter("pEmail", email).setParameter("pSenha", senha).getResultList();
		entityManager.close();
		return crianca;
	}

	public Sala onBuscarSala(String token) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		String sql = "SELECT s FROM Sala s where s.token = :pToken";
		List<Sala> sala = entityManager.createQuery(sql).setParameter("pToken", token).getResultList();
		entityManager.close();
		if(!sala.isEmpty()) {
			return sala.get(0);
		}
		return null;
	}
	
	public Crianca findCrianca(long id) {
		EntityManager entityManager = Factory.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		return entityManager.find(Crianca.class, id);
	}
}

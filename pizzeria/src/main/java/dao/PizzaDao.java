package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Ingrediente;
import model.Pizza;
import util.JPAUtil;

public class PizzaDao {
	public static List<Pizza> getPizzePerUtente(Integer userId) throws SQLException {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		List<Pizza> pizzePerUtente = new ArrayList<Pizza>();

		TypedQuery<Pizza> query = entityManager.createQuery("SELECT p FROM Pizza p WHERE utente_id = :userId",
				Pizza.class);
		query.setParameter("userId", userId);

		pizzePerUtente = query.getResultList();

		entityManager.getTransaction().commit();

		entityManager.close();

		return pizzePerUtente;

	}

	public static void create(Pizza pizza) throws SQLException {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(pizza);

		entityManager.getTransaction().commit();
	}

	public static void delete(String[] pizzeDaCancellare) throws SQLException {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		for (int i = 0; i < pizzeDaCancellare.length; i++) {

			Pizza pizza = entityManager.find(Pizza.class, Integer.parseInt(pizzeDaCancellare[i]));
			entityManager.remove(pizza);
			entityManager.getTransaction().commit();

		}
		entityManager.close();

	}
}
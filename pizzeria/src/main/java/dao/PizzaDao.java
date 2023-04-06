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
	public static Map<Pizza, List<Integer>> getPizzePerUtente(String userId) throws SQLException {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Map<Pizza, List<Integer>> pizzePerUtente = new HashMap<Pizza, List<Integer>>();

		TypedQuery<Pizza> query = entityManager.createQuery("SELECT p FROM Pizza p WHERE utente_id = :userId",
				Pizza.class);
		query.setParameter("userId", userId);

		List<Pizza> listaPizze = query.getResultList();

		for (Pizza p : listaPizze) {
			ArrayList<Integer> listaCodiciIngredienti = new ArrayList<Integer>();

			for (Ingrediente i : p.getIngredienti()) {
				listaCodiciIngredienti.add(i.getId());
			}
			pizzePerUtente.put(p, listaCodiciIngredienti);
		}

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
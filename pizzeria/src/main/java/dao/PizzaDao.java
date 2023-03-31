package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ingrediente;
import model.Pizza;
import model.Utente;
import util.JPAUtil;

public class PizzaDao {
	public Map<Pizza, ArrayList<Integer>> getPizzePerUtente(Utente utente) throws SQLException {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Map<Pizza, ArrayList<Integer>> pizzePerUtente = new HashMap<Pizza, ArrayList<Integer>>();

		Query query = entityManager.createQuery("SELECT p FROM Pizza p WHERE utente_id = :userId");
		query.setParameter("userId", utente.getId());

		ArrayList<Pizza> listaPizze = (ArrayList<Pizza>) query.getResultList();

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

	public void create(Pizza pizza) throws SQLException {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(pizza);

		entityManager.getTransaction().commit();
	}

	public void delete(String[] pizzeDaCancellare) throws SQLException {
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
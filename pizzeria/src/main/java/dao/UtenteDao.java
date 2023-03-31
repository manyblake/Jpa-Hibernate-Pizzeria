package dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Utente;
import util.JPAUtil;

public class UtenteDao {

	public Utente findUser(String user, String password) throws SQLException {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("SELECT u FROM Utente u WHERE username = :user AND pwd = :pwd");
		query.setParameter("user", user);
		query.setParameter("pwd", password);

		entityManager.getTransaction().commit();
		Utente utente = (Utente) query.getSingleResult();

		entityManager.close();

		return utente;
	}

}

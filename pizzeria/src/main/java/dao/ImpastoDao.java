package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Impasto;
import util.JPAUtil;

public class ImpastoDao {

	public List<Impasto> getImpasti() throws SQLException {
		List<Impasto> tipiImpasti = new ArrayList<Impasto>();

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		TypedQuery<Impasto> query = entityManager.createQuery("SELECT e FROM Impasto e", Impasto.class);

		tipiImpasti = (List<Impasto>) query.getResultList();

		entityManager.getTransaction().commit();

		entityManager.close();

		return tipiImpasti;
	}

}

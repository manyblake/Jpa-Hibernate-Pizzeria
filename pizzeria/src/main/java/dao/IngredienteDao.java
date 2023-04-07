package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Ingrediente;
import util.JPAUtil;

public class IngredienteDao {
	public List<Ingrediente> getIngredienti() throws SQLException {
		List<Ingrediente> tipiImpasti = new ArrayList<Ingrediente>();

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		TypedQuery<Ingrediente> query = entityManager.createQuery("SELECT e FROM Ingrediente e", Ingrediente.class);
		
		tipiImpasti = (List<Ingrediente>) query.getResultList();

		entityManager.getTransaction().commit();

		entityManager.close();

		return tipiImpasti;
	}

}

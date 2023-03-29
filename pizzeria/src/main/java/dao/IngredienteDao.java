package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Ingrediente;
import util.JPAUtil;

public class IngredienteDao {

//	public Map<Integer, String> getIngredienti() throws SQLException {
//		Connection dbConnection = ConnectionManager.getConnection();
//		PreparedStatement cmd = null;
//		Map<Integer, String> ingredienti = new HashMap<Integer, String>();
//		String updateTableSQL = "SELECT * FROM ingrediente";
//		cmd = dbConnection.prepareStatement(updateTableSQL);
//		ResultSet res = cmd.executeQuery();
//
//		while (res.next()) {
//			ingredienti.put(res.getInt("id"), res.getString("nome"));
//		}
//
//		dbConnection.close();
//
//		return ingredienti;
//
//	}

	public Map<Integer, String> getIngredienti() throws SQLException {
		Map<Integer, String> tipiImpasti = new HashMap<Integer, String>();

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("SELECT e FROM Ingrediente e");

		ArrayList<Ingrediente> listaIngredienti = (ArrayList<Ingrediente>) query.getResultList();

		for (Ingrediente ingrediente : listaIngredienti) {
			tipiImpasti.put(ingrediente.getId(), ingrediente.getName());
		}

		entityManager.getTransaction().commit();

		entityManager.close();

		return tipiImpasti;
	}

}

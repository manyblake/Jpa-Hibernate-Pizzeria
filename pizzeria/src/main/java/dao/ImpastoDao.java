package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Impasto;
import util.JPAUtil;

public class ImpastoDao {

//	public Map<Integer, String> getImpasti() throws SQLException {
//		Connection dbConnection = ConnectionManager.getConnection();
//		PreparedStatement cmd = null;
//		Map<Integer, String> tipiImpasti = new HashMap<Integer, String>();
//
//		String sql = "SELECT * FROM impasto";
//		cmd = dbConnection.prepareStatement(sql);
//		ResultSet res = cmd.executeQuery();
//
//		while (res.next()) {
//			tipiImpasti.put(res.getInt("id"), res.getString("nome"));
//		}
//
//		dbConnection.close();
//
//		return tipiImpasti;
//
//	}

	public Map<Integer, String> getImpasti() throws SQLException {
		Map<Integer, String> tipiImpasti = new HashMap<Integer, String>();

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("SELECT e FROM Impasto e");

		ArrayList<Impasto> listaImpasti = (ArrayList<Impasto>) query.getResultList();

		for (Impasto impasto : listaImpasti) {
			tipiImpasti.put(impasto.getId(), impasto.getName());
		}

		entityManager.getTransaction().commit();

		entityManager.close();

		return tipiImpasti;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.ConnectionManager;

public class IngredienteDao {

	public Map<Integer, String> getIngredienti() throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;
		Map<Integer, String> ingredienti = new HashMap<Integer, String>();
		String updateTableSQL = "SELECT * FROM ingrediente";
		cmd = dbConnection.prepareStatement(updateTableSQL);
		ResultSet res = cmd.executeQuery();

		while (res.next()) {
			ingredienti.put(res.getInt("id"), res.getString("nome"));
		}

		dbConnection.close();

		return ingredienti;

	}

}

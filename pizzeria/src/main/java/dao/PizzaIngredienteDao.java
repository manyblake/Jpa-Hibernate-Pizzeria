package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionManager;

public class PizzaIngredienteDao {

	public void create(int key, String[] ingredienti) throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;

		for (int i = 0; i < ingredienti.length; i++) {
			String sql = "INSERT INTO pizza_ingrediente(pizza_id, ingrediente_id) VALUES(?,?)";
			cmd = dbConnection.prepareStatement(sql);
			cmd.setInt(1, key);
			cmd.setInt(2, Integer.parseInt(ingredienti[i]));
			cmd.executeUpdate();
		}
	}
	
	public void delete(String ids) throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;

		String sql = "DELETE FROM pizza_ingrediente WHERE pizza_id IN (" + ids + ")";
		cmd = dbConnection.prepareStatement(sql);
		cmd.executeUpdate();

	}

}

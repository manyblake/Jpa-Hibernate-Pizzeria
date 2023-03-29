package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db.ConnectionManager;
import model.Pizza;

public class PizzaDao {
	public Map<Pizza, ArrayList<Integer>> getPizzePerUtente(int userId) throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;

		Map<Pizza, ArrayList<Integer>> pizzePerUtente = new HashMap<Pizza, ArrayList<Integer>>();

		String sql = "SELECT * FROM pizza WHERE utente_id = ?";
		cmd = dbConnection.prepareStatement(sql);
		cmd.setInt(1, userId);
		ResultSet res = cmd.executeQuery();

		while (res.next()) {
			Pizza pizza = new Pizza();
			ArrayList<Integer> listaCodiciIngredienti = new ArrayList<Integer>();
			pizza.setId(res.getInt("id"));
			pizza.setNome(res.getString("nome"));
			pizza.setUtenteId(res.getInt("utente_id"));
			pizza.setImpastoId(res.getInt("impasto_id"));

			String sqlTrovaIngredienti = "SELECT * FROM pizza_ingrediente WHERE pizza_id = ?";
			cmd = dbConnection.prepareStatement(sqlTrovaIngredienti);
			cmd.setInt(1, pizza.getId());
			ResultSet resIngredienti = cmd.executeQuery();

			while (resIngredienti.next()) {
				listaCodiciIngredienti.add(resIngredienti.getInt("ingrediente_id"));
			}

			pizzePerUtente.put(pizza, listaCodiciIngredienti);
		}

		dbConnection.close();

		return pizzePerUtente;

	}

	public void create(Pizza pizza, String[] ingredienti) throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;
		PizzaIngredienteDao pizzaIngredienteDao = new PizzaIngredienteDao();
		int key = 0;

		String sql = "INSERT INTO pizza(nome, impasto_id, utente_id) VALUES(?,?,?)";
		cmd = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		cmd.setString(1, pizza.getNome());
		cmd.setInt(2, pizza.getImpastoId());
		cmd.setInt(3, pizza.getUtenteId());
		cmd.executeUpdate();

		ResultSet res = cmd.getGeneratedKeys();
		if (res != null && res.next()) {
			key = res.getInt(1);
		}

		try {
			if (key != 0) {
				pizzaIngredienteDao.create(key, ingredienti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbConnection.close();
	}

	public void delete(String[] pizzeDaCancellare) throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;
		PizzaIngredienteDao pizzaIngredienteDao = new PizzaIngredienteDao();
		String ids = "";

//		List<String> ListaPizzeDaCancellare = Arrays.asList(pizzeDaCancellare);
//		Array ArrayPizzeDaCancellare = dbConnection.createArrayOf("String", ListaPizzeDaCancellare.toArray());		
//		String sql = "DELETE FROM pizza WHERE id IN (?)";
//		cmd = dbConnection.prepareStatement(sql);
//		cmd.setArray(1, ArrayPizzeDaCancellare);
//		cmd.executeUpdate();

		for (int i = 0; i < pizzeDaCancellare.length; i++) {
			ids += "'" + pizzeDaCancellare[i] + "',";
		}

		if (ids.endsWith(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		
		pizzaIngredienteDao.delete(ids);

		String sql = "DELETE FROM pizza WHERE id IN (" + ids + ")";
		cmd = dbConnection.prepareStatement(sql);
		cmd.executeUpdate();		

		dbConnection.close();
	}
}
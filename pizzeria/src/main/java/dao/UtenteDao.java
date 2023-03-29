package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionManager;
import model.Utente;

public class UtenteDao {

	public Utente findUser(String user, String password) throws SQLException {
		Utente utente = null;

		Connection dbConnection = ConnectionManager.getConnection();
		java.sql.PreparedStatement cmd = null;
		String sql = "SELECT * FROM utente WHERE username = ? AND pwd = ? ";
		
		cmd = dbConnection.prepareStatement(sql);
		cmd.setString(1, user);
		cmd.setString(2, password);
		ResultSet res = cmd.executeQuery();

		if (res.next()) {
			utente = new Utente(res.getInt("id"), res.getString("nome"), res.getString("username"),
					res.getString("pwd"));
		}
		
		dbConnection.close();

		return utente;
	}

}

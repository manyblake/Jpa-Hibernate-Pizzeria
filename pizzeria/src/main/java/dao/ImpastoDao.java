package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.ConnectionManager;

public class ImpastoDao {

	public Map<Integer, String> getImpasti() throws SQLException {
		Connection dbConnection = ConnectionManager.getConnection();
		PreparedStatement cmd = null;
		Map<Integer, String> tipiImpasti = new HashMap<Integer, String>();

		String sql = "SELECT * FROM impasto";
		cmd = dbConnection.prepareStatement(sql);
		ResultSet res = cmd.executeQuery();

		while (res.next()) {
			tipiImpasti.put(res.getInt("id"), res.getString("nome"));
		}

		dbConnection.close();

		return tipiImpasti;

	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


import jdbc.ConnectionProvider;

public class ItinerarioDao {

	public int insert(String nombre, int costo, double tiempo) {
		try {
			String sql = "INSERT INTO Itinerario (NombreUsuario, CostoTotal, DuracionTotal) VALUES (?, ?, ?))";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			statement.setInt(2, costo);
			statement.setDouble(3, tiempo);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}

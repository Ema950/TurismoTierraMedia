package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import jdbc.ConnectionProvider;
import tierra_media.TipoAtraccion;
import tierra_media.Usuario;

public class UsuarioDao implements GenericDao<Usuario> {

	public LinkedList<Usuario> findAll() throws SQLException {
		String sql = "SELECT * FROM Usuarios;";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		while (resultados.next()) {
			usuarios.add(toUsuario(resultados));
		}

		return usuarios;
	}

	public int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM Usuarios;";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	public int insert(Usuario t) {
		try {
			String sql = "INSERT INTO Usuarios (Nombre, AtraccionPreferida, Presupuesto, Tiempo) VALUES (?, ?, ?, ?))";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			statement.setString(2, t.getAtraccionPreferida().toString());
			statement.setInt(3, t.getPresupuesto());
			statement.setDouble(4, t.getTiempoDisponible());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int update(Usuario t) {
		try {
			String sql = "UPDATE Usuarios SET Presupuesto = ?, Tiempo = ?, WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getPresupuesto());
			statement.setDouble(2, t.getTiempoDisponible());
			statement.setString(3, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario t) {
		try {
			String sql = "DELETE FROM Usuario WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getString(2), TipoAtraccion.valueOf(resultados.getString(3).toUpperCase()),
				resultados.getInt(4), resultados.getDouble(5));
	}

}

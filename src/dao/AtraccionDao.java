package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import tierra_media.Atraccion;
import tierra_media.TipoAtraccion;

public class AtraccionDao implements GenericDao<Atraccion> {

	public LinkedList<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Atracciones;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Atracciones;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(Atraccion t) {
		try {
			String sql = "INSERT INTO Atracciones (Nombre, Costo, Tiempo, Cupo, Tipo) VALUES (?, ?, ?, ?, ?))";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			statement.setInt(2, t.costo());
			statement.setDouble(3, t.duracion());
			statement.setInt(4, t.getCupo());
			switch (t.getTipoDeAtraccion()) {
			case PAISAJE:
				statement.setInt(5, 1);
			case AVENTURA:
				statement.setInt(5, 2);
			case DEGUSTACION:
				statement.setInt(5, 3);
			}
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Atraccion t) {
		try {
			String sql = "UPDATE Atracciones SET Cupo = ?, WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getCupo());
			statement.setString(2, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Atraccion t) {
		try {
			String sql = "DELETE FROM Atraccion WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<Atraccion> findAllByPromocion(int idPromo) {
		try {
			String sql = "SELECT * FROM Atracciones WHERE IdPromocion = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromo);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public Atraccion findById(int id) {
		try {
			String sql = "SELECT * FROM Atracciones WHERE Id = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();
			Atraccion retorno = toAtraccion(resultados);
			return retorno;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int getId(String nombre) {
		try {
			String sql = "SELECT id FROM Atracciones WHERE Nombre = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();
			int retorno = resultados.getInt(1);
			return retorno;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getString(2), resultados.getInt(3), resultados.getDouble(4),
				resultados.getInt(5), TipoAtraccion.valueOf(resultados.getString(6).toUpperCase()));
	}

}

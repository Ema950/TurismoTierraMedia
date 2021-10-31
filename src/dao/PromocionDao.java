package dao;

import java.sql.*;
import java.util.*;

import jdbc.ConnectionProvider;
import tierra_media.*;

public class PromocionDao implements GenericDao<Promocion> {

	public LinkedList<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM Promociones;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Promociones;";
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

	public int insert(Promocion t) {
		try {
			String sql = "INSERT INTO Promociones (NombrePromo, Tipo, Descuento) VALUES (?, ?, ?))";
			Connection conn = ConnectionProvider.getConnection();
			AtraccionDao atraccionDao = new AtraccionDao();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			if (t instanceof Porcentual) {
				statement.setString(2, "Porcentual");
				statement.setDouble(3, ((Porcentual) t).getPorcentajeDescuento());
			} else {
				if (t instanceof Absoluta) {
					statement.setString(2, "Absoluta");
					statement.setDouble(3, ((Absoluta) t).getDescuento());
				} else {
					statement.setString(2, "AxB");
					statement.setDouble(3, (atraccionDao.getId(((AxB) t).getAtraccionGratis().getNombre())));
				}
			}
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Promocion t) {
		try {
			String sql = "UPDATE Promociones SET NombrePromo = ?, WHERE Id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			statement.setInt(2, this.getId(t.getNombre()));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Promocion t) {
		try {
			String sql = "DELETE FROM Promociones WHERE NombrePromo = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private int getId(String nombre) {
		try {
			String sql = "SELECT id FROM Promociones WHERE NombrePromo = ?;";
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

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		Promocion retorno;
		AtraccionDao atraccionDao = new AtraccionDao();
		if (resultados.getString(3).equalsIgnoreCase("Porcentual")) {
			retorno = new Porcentual(resultados.getString(2), atraccionDao.findAllByPromocion(resultados.getInt(1)),
					resultados.getDouble(4));
		} else {
			if (resultados.getString(3).equalsIgnoreCase("Absoluta")) {
				retorno = new Absoluta(resultados.getString(2), atraccionDao.findAllByPromocion(resultados.getInt(1)),
						(int) resultados.getDouble(4));
			} else {
				retorno = new AxB(resultados.getString(2), atraccionDao.findAllByPromocion(resultados.getInt(1)),
						atraccionDao.findById((int) resultados.getDouble(4)));
			}
		}
		return retorno;
	}

}

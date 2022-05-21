package clase15.patron_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompactoImpl implements DAO<Auto, String>, ConexionMySql {

	@Override
	public void save(Auto element) {

		String sql = "insert into compacto(numero, activa, color, marca, encendido, puestos) " + "values(?,?,?,?,?,?)";

		Compacto c = (Compacto) element;

		try (Connection con = getConexion();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, c.getPatente().getNumero());
			pst.setBoolean(2, c.getPatente().isActiva());
			pst.setString(3, c.getColor());
			pst.setString(4, c.getMarca());
			pst.setBoolean(5, c.isEncendido());
			pst.setInt(6, c.getPuestos());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Auto get(String key) {

		String sql = "select id, numero, activa, color, marca, encendido, puestos from compacto " + "where numero = ?";

		Compacto auto = null;

		try (Connection con = getConexion();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, key);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				auto = new Compacto();
				auto.setPatente(new Patente(rs.getString("numero"), rs.getBoolean("activa")));
				auto.setColor(rs.getString("color"));
				auto.setMarca(rs.getString("marca"));
				auto.setEncendido(rs.getBoolean("encendido"));
				auto.setPuestos(rs.getInt("puestos"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auto;
	}

	@Override
	public boolean update(Auto element) {

		String sql = "update compacto set activa = ? where numero = ?";

		try (Connection con = getConexion();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setBoolean(1, false);
			pst.setString(2, element.getPatente().getNumero());

			if (pst.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Auto element) {
		
		String sql = "delete from compacto where numero = ?";

		try (Connection con = getConexion();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, element.getPatente().getNumero());

			if (pst.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}

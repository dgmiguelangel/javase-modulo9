package clase16.tabla.discriminador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {

//		insertarCamion();
//		insertarPasajeros();

		consultarCamion();

	}

	private static void consultarCamion() {

		String sql = "select numero, activa, marca, color, encendido, licencia, tipo, ejes, capacidad "
				+ "from transporte where discriminador = ?";

		try (Connection con = conectarBD();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "C");
					
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				System.out.println("Numero patente: " + rs.getString(1) + ", activa: " + rs.getBoolean(2) + ", marca: "
						+ rs.getString(3) + ", color: " + rs.getString(4) + ", encendido: " + rs.getBoolean(5)
						+ ", licencia: " + rs.getString(6) + ", tipo: " + rs.getString(7) + ", ejes: " + rs.getInt(8)
						+ ", capacidad: " + rs.getDouble(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void insertarPasajeros() {

		String sql = "insert into transporte(numero, activa, marca, color, encendido, licencia"
				+ ", tipo, accesoEspecial, discriminador) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = conectarBD();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "CCC-001");
			pst.setBoolean(2, true);
			pst.setString(3, "Fiat");
			pst.setString(4, "verde");
			pst.setBoolean(5, false);
			pst.setString(6, "LIC01");
			pst.setString(7, "b");
			pst.setBoolean(8, true);
			pst.setString(9, "P");

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertarCamion() {

		String sql = "insert into transporte(numero, activa, marca, color, encendido, licencia"
				+ ", tipo, ejes, capacidad, discriminador) " + "values(?,?,?,?,?,?,?,?,?,?)";

		try (Connection con = conectarBD();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "CCC-001");
			pst.setBoolean(2, true);
			pst.setString(3, "Fiat");
			pst.setString(4, "verde");
			pst.setBoolean(5, false);
			pst.setString(6, "LIC01");
			pst.setString(7, "b");
			pst.setInt(8, 8);
			pst.setDouble(9, 50.56);
			pst.setString(10, "C");

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection conectarBD() {

		Connection con = null;

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/curso_javase";
		String usuario = "root";
		String clave = "root";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Hubo un error en la conexion...");
		}

		return con;
	}

}

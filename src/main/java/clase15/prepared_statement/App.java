package clase15.prepared_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {

		consultar("ABC-001");
//		consultar("ABC-003' OR '' = '");

//		insertar();

//		actualizar();

//		eliminar();

	}

	private static void eliminar() {

		String sql = "delete from compacto where numero = ?";

		try (Connection con = conectarBD(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "ABC-005");
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void actualizar() {

		String sql = "update compacto set activa = 1 where numero = ?";

		try (Connection con = conectarBD(); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, "ABC-005");
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertar() {

		String sql = "insert into compacto (numero, activa, color, marca, encendido, puestos)"
				+ " values (?, ?, ?, ?, ?, ?)";

		try (Connection con = conectarBD(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "ABC-005");
			pst.setBoolean(2, false);
			pst.setString(3, "rojo");
			pst.setString(4, "Ford");
			pst.setBoolean(5, true);
			pst.setInt(6, 2);

			System.out.println(sql);

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void consultar(String patente) {

		String sql = "select id, numero, activa, color, marca, encendido, puestos from compacto " + "where numero = ?";

		try (Connection con = conectarBD();) {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, patente);

			ResultSet rs = pst.executeQuery();

			System.out.println(sql);

			while (rs.next()) {
				System.out.println(rs.getString("numero"));
				System.out.println(rs.getBoolean("activa"));
				System.out.println(rs.getString("color"));
				System.out.println(rs.getString("marca"));
				System.out.println(rs.getBoolean("encendido"));
				System.out.println(rs.getInt("puestos"));
				System.out.println("---------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	private static Connection conectarBD() {
		Connection con = null;

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/curso_javase";
//		String driver = "org.mariadb.jdbc.Driver";
//		String url = "jdbc:mariadb://localhost:3306/curso_javase";
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

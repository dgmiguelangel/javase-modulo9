package clase15.inyeccion_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {

//		consultar("ABC-003");
		consultar("ABC-003' OR '' = '");
		
	}

	
	private static void consultar(String filtro) {

		String sql = "select id, numero, activa, color, marca, encendido, puestos from compacto "
				+ "where numero = '" + filtro + "'"; // 'ABC-003' OR '' = ''

		try (Connection con = conectarBD(); Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(sql);
			
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

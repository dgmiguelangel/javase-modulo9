package clase15.patron_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConexionMySql {
	
	default Connection getConexion() {
		
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

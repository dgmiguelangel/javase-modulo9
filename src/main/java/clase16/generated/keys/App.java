package clase16.generated.keys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) {
		informarKeys();		
	}
	
	private static void informarKeys() {
		
		String sql = "INSERT INTO compacto (numero, activa, color, marca, encendido, puestos) "
				+ "VALUES ('ABC-001', 1, 'AMARILLO', 'SEDAN', 1, 2),"
				+ "('ABC-002', 0, 'AZUL', 'SEDAN', 0, 4),"
				+ "('ABC-003', 0, 'ROJO', 'SEDAN', 1, 4),"
				+ "('ABC-004', 1, 'VERDE', 'SEDAN', 0, 2);";
		
		try(Connection con = conectarBD(); Statement st = con.createStatement();){
			
			st.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
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

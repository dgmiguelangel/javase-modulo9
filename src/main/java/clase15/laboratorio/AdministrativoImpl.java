package clase15.laboratorio;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministrativoImpl implements DAO<Integer, Persona>, ConexionMySql {

	@Override
	public boolean save(Persona p) {
		
		Administrativo admin = (Administrativo) p;
		
		String sql = "INSERT INTO administrativo (nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, fechaCargo, sueldo) "
				+ "VALUES  (?,?,?,?,?,?,?);";

		try (Connection conexion = getConexion()) {

			PreparedStatement declaracionPreparadaSQL = conexion.prepareStatement(sql);
			
			declaracionPreparadaSQL.setString(1, admin.getNombre());
			declaracionPreparadaSQL.setString(2, admin.getApellido());
			declaracionPreparadaSQL.setString(3, admin.getDocumento().getTipoDocumento());
			declaracionPreparadaSQL.setInt(4, admin.getDocumento().getNumeroDocumento());
			declaracionPreparadaSQL.setDate(5, Date.valueOf(admin.getFechaNacimiento()));
			declaracionPreparadaSQL.setDate(6, Date.valueOf(admin.getFechaCargo()));
			declaracionPreparadaSQL.setDouble(7, admin.getSueldo());
			
			if(declaracionPreparadaSQL.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

}

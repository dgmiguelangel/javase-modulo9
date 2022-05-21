package clase15.laboratorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class App {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Set<Persona> personas = new HashSet<>();
		List<Persona> personasOrdenadas;
		byte cc;

		String nombre, apellido;

		String tipoDocumento = null;
		int numeroDocumento;

		LocalDate fechaNacimiento;

		String[] cursos;

		Persona persona = null;
		int tipoPersona;

		LocalDate fechaCargo;
		double sueldo;
		String carrera;

		do {

			System.out.println("Ingrese el tipo de persona o cero para finalizar");
			System.out.println("1-Alumno");
			System.out.println("2-Director");
			System.out.println("3-Profesor");
			System.out.println("4-Administrativo");

			tipoPersona = sc.nextInt();

			if (tipoPersona == 0) {
				break;
			}

			System.out.println("Ingrese el nombre");
			nombre = sc.next();

			System.out.println("Ingrese el apellido");
			apellido = sc.next();

			/* EXCEPCION PROPIA */
			boolean isDocuValido = false;

			while (!isDocuValido) {
				System.out.println("Ingrese el tipo de documento");
				tipoDocumento = sc.next().toUpperCase();

				try {

					for (int i = 0; i < TipoDocumentoValidos.values().length; i++) {
						if (tipoDocumento.equals(TipoDocumentoValidos.values()[i].toString())) {
							isDocuValido = true;
							break;
						}
					}

					if (!isDocuValido) {
						throw new DocumentoException(1);
					}

					break;

				} catch (DocumentoException e) {
					System.out.println(e.getMessage());
				}
			}

			System.out.println("Ingrese el nro de documento");
			numeroDocumento = sc.nextInt();

			System.out.println("Ingrese la fecha de nacimiento");
			fechaNacimiento = ingresarFechaValida();

			switch (tipoPersona) {
			case 1: // alumno
				System.out.println("Ingrese la cantidad de cursos");
				cc = sc.nextByte();
				cursos = ingresarCursos(cc);

				persona = new Alumno(nombre, apellido, new Documento(tipoDocumento, numeroDocumento), fechaNacimiento,
						cursos);

				break;

			case 2: // director
				System.out.println("Ingrese la fecha de inicio del cargo: ");
				fechaCargo = ingresarFechaValida();

				System.out.print("Ingrese el sueldo: ");
				sueldo = sc.nextDouble();

				sc.nextLine();
				System.out.println("Ingrese la carrera: ");
				carrera = sc.nextLine();

				persona = new Director(nombre, apellido, new Documento(tipoDocumento, numeroDocumento), fechaNacimiento,
						fechaCargo, sueldo, carrera);

				/** AGREGAMOS UNA PERSONA A LA BASE DE DATOS */
				guardarDirector(persona);

				break;

			case 3: // profesor
				System.out.println("Ingrese la fecha de inicio del cargo: ");
				fechaCargo = ingresarFechaValida();

				System.out.print("Ingrese el sueldo: ");
				sueldo = sc.nextDouble();

				System.out.print("Ingrese la cantidad de cursos: ");
				cc = sc.nextByte();
				cursos = ingresarCursos(cc);

				persona = new Profesor(nombre, apellido, new Documento(tipoDocumento, numeroDocumento), fechaNacimiento,
						fechaCargo, sueldo, cursos);

				break;

			case 4: // administrativo
				System.out.println("Ingrese la fecha de inicio del cargo: ");
				fechaCargo = ingresarFechaValida();

				System.out.print("Ingrese el sueldo: ");
				sueldo = sc.nextDouble();

				persona = new Administrativo(nombre, apellido, new Documento(tipoDocumento, numeroDocumento),
						fechaNacimiento, fechaCargo, sueldo);

				/* AGREGAMOS UN ADMINISTRATIVO A LA BD */
				guardarAdministrativo(persona);

				break;

			}

			personas.add(persona);

		} while (true);

		System.out.println("\nDatos de las personas desordenadas");

		Iterator<Persona> it = personas.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		personasOrdenadas = new ArrayList<Persona>(personas);
		personasOrdenadas.sort(new OrdenDocumento());

		System.out.println("\nDatos de las personas ordenadas:");

		Iterator<Persona> ito = personasOrdenadas.iterator();
		while (ito.hasNext()) {
			System.out.println(ito.next());
		}

		asistencia(personasOrdenadas);

		sc.close();

	}

	private static void guardarAdministrativo(Persona p) {
		AdministrativoImpl adm = new AdministrativoImpl();
		adm.save(p);
	}

	private static Connection conectarBaseDeDatos() throws SQLException {

		Connection conexion = null;

		try {
//			String driver = "org.mariadb.jdbc.Driver";
//			String url = "jdbc:mariadb://localhost:3306/curso_javase";

			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/curso_javase";

			String usuario = "root";
			String clave = "root";

			Class.forName(driver);

			conexion = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conexion;
	}

	private static void guardarDirector(Persona p) {

		Director persona = (Director) p;

		try (Connection conexion = conectarBaseDeDatos(); Statement st = conexion.createStatement()) {

			String sql = "insert into director (nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, fechaCargo, sueldo, carrera) "
					+ "values ('" + persona.getNombre() + "', '" + persona.getApellido() + "', '"
					+ persona.getDocumento().getTipoDocumento() + "', " + persona.getDocumento().getNumeroDocumento()
					+ ", '" + Date.valueOf(persona.getFechaNacimiento()) + "', '"
					+ Date.valueOf(persona.getFechaCargo()) + "', " + persona.getSueldo() + ", '" + persona.getCarrera()
					+ "')";

			st.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void asistencia(List<Persona> personas) {
		Queue<Persona> cola = new PriorityQueue<Persona>(new OrdenEdad());
		cola.addAll(personas);

		System.out.println("\nASISTENCIA");
		while (!cola.isEmpty()) {
			System.out.println("Por atender a: " + cola.peek());
			System.out.println("Atendiendo a: " + cola.poll());
			System.out.println();
		}
	}

	private static LocalDate ingresarFechaValida() {
		String fechaUsuario = null;
		LocalDate fecha = null;

		while (true) {

			try {
				System.out.print("Formato Fecha[dd/mm/aaaa]: ");
				fechaUsuario = sc.next();

				fecha = UtilidadesFecha.getStringAsLocalDate(fechaUsuario);
				break;

			} catch (ParseException e) {
				System.err.println("debe ingresar un dato valido: " + e.getMessage());
			}

		}

		return fecha;
	}

	private static String[] ingresarCursos(int cantidad) {
		String[] cursos = new String[cantidad];
		String curso;

		for (int i = 0; i < cursos.length; i++) {

			while (true) {
				System.out.println("Ingrese el curso: ");
				curso = sc.next();

				if (Constantes.getCursos().containsValue(curso.toUpperCase())) {
					cursos[i] = curso;
					break;
				}

				System.err.println("debe ingresar un curso valido: " + Constantes.getCursos().values());
			}

		}

		return cursos;
	}

}

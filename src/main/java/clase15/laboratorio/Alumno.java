package clase15.laboratorio;

import java.time.LocalDate;
import java.util.Arrays;

public class Alumno extends Persona {
	
	private String[] cursos;

	public Alumno(String nombre, String apellido, Documento documento, LocalDate fechaNacimiento, String[] cursos) {
		super(nombre, apellido, documento, fechaNacimiento);
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return "Alumno [cursos=" + Arrays.toString(cursos) + ", toString()=" + super.toString() + "]";
	}

	public String[] getCursos() {
		return cursos;
	}

	public void setCursos(String[] cursos) {
		this.cursos = cursos;
	}

}

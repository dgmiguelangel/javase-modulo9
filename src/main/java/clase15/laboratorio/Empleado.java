package clase15.laboratorio;

import java.time.LocalDate;

public abstract class Empleado extends Persona {

	private LocalDate fechaCargo;
	private double sueldo;

	public Empleado(String nombre, String apellido, Documento documento, LocalDate fechaNacimiento,
			LocalDate fechaCargo, double sueldo) {
		super(nombre, apellido, documento, fechaNacimiento);
		this.fechaCargo = fechaCargo;
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Empleado [fechaCargo=" + UtilidadesFecha.getLocalDateAsString(fechaCargo) + ", sueldo=" + sueldo
				+ ", toString()=" + super.toString() + "]";
	}

	public LocalDate getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(LocalDate fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

}

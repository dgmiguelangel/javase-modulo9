package clase15.patron_dao;

public class Pasajero extends Transporte {

	private boolean accesoEspecial;

	public Pasajero(String color, String marca, Patente patente, boolean encendido, String licencia, String tipo,
			boolean accesoEspecial) {
		super(color, marca, patente, encendido, licencia, tipo);
		this.accesoEspecial = accesoEspecial;
	}

	@Override
	public String toString() {
		return "Pasajero [accesoEspecial=" + accesoEspecial + ", toString()=" + super.toString() + "]";
	}

	@Override
	public void vender() {
		System.out.println("pasajero vendido " + this);
	}


	public boolean isAccesoEspecial() {
		return accesoEspecial;
	}

	public void setAccesoEspecial(boolean accesoEspecial) {
		this.accesoEspecial = accesoEspecial;
	}

}

package clase15.laboratorio;

import java.util.Comparator;

public class OrdenEdad implements Comparator<Persona> {

	@Override
	public int compare(Persona o1, Persona o2) {
		return o2.calcularEdad() - o1.calcularEdad();
	}

}

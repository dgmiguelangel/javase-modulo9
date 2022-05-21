package clase15.laboratorio;

import java.util.Comparator;

public class OrdenDocumento implements Comparator<Persona> {

	@Override
	public int compare(Persona o1, Persona o2) {
		
		int tipo = o1.getDocumento().getTipoDocumento().compareTo(o2.getDocumento().getTipoDocumento());

		if (tipo == 0) {
			return o1.getDocumento().getNumeroDocumento() - o2.getDocumento().getNumeroDocumento();
		}

		return tipo;
	}
	
}

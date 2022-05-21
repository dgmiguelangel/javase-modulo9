package clase15.laboratorio;

public enum TipoDocumentoValidos {

	DNI("Documento Nacional de Identidad"), 
	PAS("Pasaporte"), 
	LE("Libreta de Enrolamiento"), 
	CI("Cedula de Identidad");

	private String descripcion;

	private TipoDocumentoValidos(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}

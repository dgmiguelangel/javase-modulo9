package clase15.laboratorio;

import java.util.Objects;

public final class Documento {

	private String tipoDocumento;
	private int numeroDocumento;

	Documento(String tipoDocumento, int numeroDocumento) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	@Override
	public String toString() {
		return "Documento [tipoDocumento=" + TipoDocumentoValidos.valueOf(tipoDocumento).getDescripcion()
				+ ", numeroDocumento=" + numeroDocumento + "]";
	}	
	

	@Override
	public int hashCode() {
		return Objects.hash(numeroDocumento, tipoDocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		return numeroDocumento == other.numeroDocumento && Objects.equals(tipoDocumento, other.tipoDocumento);
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}

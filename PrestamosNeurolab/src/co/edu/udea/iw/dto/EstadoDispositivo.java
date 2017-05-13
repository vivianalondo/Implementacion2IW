package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la información básica del estado del dispositivo, setters y getters POJO.
 * @author: Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class EstadoDispositivo {
	
	public int idEstadoDispositivo;
	public String tipoEstadoDispositivo;
	
	public int getIdEstadoDispositivo() {
		return idEstadoDispositivo;
	}
	public void setIdEstadoDispositivo(int idEstadoDispositivo) {
		this.idEstadoDispositivo = idEstadoDispositivo;
	}
	public String getTipoEstadoDispositivo() {
		return tipoEstadoDispositivo;
	}
	public void setTipoEstadoDispositivo(String tipoEstadoDispositivo) {
		this.tipoEstadoDispositivo = tipoEstadoDispositivo;
	}
	
	

}

package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la información básica del estado del usuario, setters y getters POJO.
 * @author: Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class EstadoReserva {
	
	public int idEstadoReserva;
	public String tipoEstadoReserva;
	
	public int getIdEstadoReserva() {
		return idEstadoReserva;
	}
	public void setIdEstadoReserva(int idEstadoReserva) {
		this.idEstadoReserva = idEstadoReserva;
	}
	public String getTipoEstadoReserva() {
		return tipoEstadoReserva;
	}
	public void setTipoEstadoReserva(String tipoEstadoReserva) {
		this.tipoEstadoReserva = tipoEstadoReserva;
	}
	
	

}

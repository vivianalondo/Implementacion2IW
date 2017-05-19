package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Dto de estado reserva
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */


@XmlRootElement
public class EstadoReservaJersey {

	private int idEstadoReserva;
	private String tipoEstadoReserva;
	
	public EstadoReservaJersey() {

	}
	
	public EstadoReservaJersey(int idEstadoReserva, String tipoEstadoReserva) {
		super();
		this.idEstadoReserva = idEstadoReserva;
		this.tipoEstadoReserva = tipoEstadoReserva;
	}

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

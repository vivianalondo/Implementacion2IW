package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la informaci�n b�sica del equipo por reserva, setters y getters POJO.
 * @author: Viviana Londo�o, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class EquipoXReserva {
	
	public EquipoXReservaId equiposXReservaId;
	public EstadoReserva estadoReserva;

	public EquipoXReservaId getEquiposXReservaId() {
		return equiposXReservaId;
	}

	public void setEquiposXReservaId(EquipoXReservaId equiposXReservaId) {
		this.equiposXReservaId = equiposXReservaId;
	}
	
	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	

}

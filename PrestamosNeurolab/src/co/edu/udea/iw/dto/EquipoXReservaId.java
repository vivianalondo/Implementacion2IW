
package co.edu.udea.iw.dto;

import java.io.Serializable;

/**
 * Esta es la clase que contiene la informaci�n del id de los equipo por reserva para la clave primaria de la clase EquiposXReserva.
 * @author: Viviana Londo�o, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class EquipoXReservaId implements Serializable{

	public Reserva idReserva;
	public Dispositivo idDispositivo;
	public EstadoReserva estadoReserva;
	
	public Reserva getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Reserva idReserva) {
		this.idReserva = idReserva;
	}
	public Dispositivo getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(Dispositivo idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	
}

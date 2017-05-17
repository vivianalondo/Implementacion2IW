package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EquipoXReservaJersey {

	private int idReserva;
	private int idDispositivo;
	private int estadoReserva;
	
	public EquipoXReservaJersey() {
		
	}
	
	public EquipoXReservaJersey(int idReserva, int idDispositivo, int estadoReserva) {
		super();
		this.idReserva = idReserva;
		this.idDispositivo = idDispositivo;
		this.estadoReserva = estadoReserva;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public int getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(int estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
	
}

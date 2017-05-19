package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Dto de Estado dispositivo
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@XmlRootElement
public class EstadoDispositivoJersey {

	private int idEstadoDispositivo;
	private String tipoEstadoDispositivo;
	
	public EstadoDispositivoJersey(){
		
	}
	
	public EstadoDispositivoJersey(int idEstadoDispositivo, String tipoEstadoDispositivo) {
		super();
		this.idEstadoDispositivo = idEstadoDispositivo;
		this.tipoEstadoDispositivo = tipoEstadoDispositivo;
	}

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

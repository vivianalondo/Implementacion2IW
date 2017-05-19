package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Dto de Estado Usuario
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */


@XmlRootElement
public class EstadoUsuarioJersey {

	private int idEstadoUsuario;
	private String tipoEstado;
	
	public EstadoUsuarioJersey(){
		
	}
	
	public EstadoUsuarioJersey(int idEstadoUsuario, String tipoEstado) {
		super();
		this.idEstadoUsuario = idEstadoUsuario;
		this.tipoEstado = tipoEstado;
	}

	public int getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(int idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}

	public String getTipoEstado() {
		return tipoEstado;
	}

	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}
	
	
	
}

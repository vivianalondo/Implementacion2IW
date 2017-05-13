package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la información básica del estado del usuario, setters y getters POJO.
 * @author: Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class EstadoUsuario {

	public int idEstadoUsuario;
	public String tipoEstado;
	
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

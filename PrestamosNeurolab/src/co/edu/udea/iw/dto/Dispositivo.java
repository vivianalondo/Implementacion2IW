package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la información básica del dispositivo, setters y getters POJO.
 * @author: Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class Dispositivo {
	
	public int idDispositivo;
	public String nombre;
	public EstadoDispositivo estadoDispositivo;
	public String descripcion;
	
	
	public int getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EstadoDispositivo getEstadoDispositivo() {
		return estadoDispositivo;
	}
	public void setEstadoDispositivo(EstadoDispositivo estadoDispositivo) {
		this.estadoDispositivo = estadoDispositivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}


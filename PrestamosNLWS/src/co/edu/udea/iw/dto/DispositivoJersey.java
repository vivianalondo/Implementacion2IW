package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DispositivoJersey {
	
	private int idDispositivo;
	private String nombre;
	private String estadoDispositivo;
	private String descripcion;
	
	public DispositivoJersey() {

	}
	
	public DispositivoJersey(int idDispositivo, String nombre, String estadoDispositivo,
			String descripcion) {
		super();
		this.idDispositivo = idDispositivo;
		this.nombre = nombre;
		this.estadoDispositivo = estadoDispositivo;
		this.descripcion = descripcion;
	}

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

	public String getEstadoDispositivo() {
		return estadoDispositivo;
	}

	public void setEstadoDispositivo(String estadoDispositivo) {
		this.estadoDispositivo = estadoDispositivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

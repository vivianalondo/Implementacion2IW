package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RolJersey {
	private int idRol;
	private String tipoRol;
	
	public RolJersey(){
		
	}
	
	public RolJersey(int idRol, String tipoRol) {
		super();
		this.idRol = idRol;
		this.tipoRol = tipoRol;
	}
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getTipoRol() {
		return tipoRol;
	}
	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}
	
	

}

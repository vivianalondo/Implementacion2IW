package co.edu.udea.iw.dto;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Clase
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@XmlRootElement
public class UsuarioJersey {

	private String identificacion;
	private String tipoDocumento;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String login;
	private int diasSanciones;
	private String estadoUsuario;
	private String rol;
	
	public UsuarioJersey() {

	}
	
	
	public UsuarioJersey(String identificacion, String tipoDocumento, String nombre, String apellido, String telefono,
			String email, String login, int diasSanciones, String estadoUsuario, String rol) {
		super();
		this.identificacion = identificacion;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.login = login;
		this.diasSanciones = diasSanciones;
		this.estadoUsuario = estadoUsuario;
		this.rol = rol;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public int getDiasSanciones() {
		return diasSanciones;
	}


	public void setDiasSanciones(int diasSanciones) {
		this.diasSanciones = diasSanciones;
	}


	public String getEstadoUsuario() {
		return estadoUsuario;
	}


	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
/**
 * Esta es la clase que contiene la información básica del usuario, setters y getters POJO.
 * @author: 
 * @version: 1.0
 */

package co.edu.udea.iw.dto;

/**
 * Esta es la clase que contiene la información básica del usuario, setters y getters POJO.
 * @author: Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version: 1.0
 */

public class Usuario {
	
	public String identificacion;
	public String tipoDocumento;
	public String nombre;
	public String apellido;
	public String telefono;
	public String email;
	public String login;
	public String password;
	public int diasSanciones;
	public EstadoUsuario estadoUsuario;
	public Rol rol;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getDiasSanciones() {
		return diasSanciones;
	}
	public void setDiasSanciones(int diasSanciones) {
		this.diasSanciones = diasSanciones;
	}
	public EstadoUsuario getEstadoUsuario() {
		return estadoUsuario;
	}
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
		

}

package co.edu.udea.iw.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.UsuarioBl;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.dto.UsuarioJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de Usuario
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@Path("Usuario")
@Component
public class UsuarioWS {

	@Autowired
	UsuarioBl usuarioBL;
	Usuario usuario;
	
	/***
	 * Método para implementar WS de obtener Ususario
	 * @param identificacion; Recibe el documento de identificacion del Ususario que se desea obtener
	 * @return usuarioJersey, retorna el usuario obtenido
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obtener")
	public UsuarioJersey obtener(@QueryParam("identificacion")String identificacion){
		UsuarioJersey usuarioJersey = null;
		
		try{
			usuario = usuarioBL.obtener(identificacion);
			
			usuarioJersey = new UsuarioJersey(
					
					usuario.getIdentificacion(),
					usuario.getTipoDocumento(),
					usuario.getNombre(),
					usuario.getApellido(),
					usuario.getTelefono(),
					usuario.getEmail(),
					usuario.getLogin(),
					usuario.getDiasSanciones(),
					usuario.getEstadoUsuario().getTipoEstado(),
					usuario.getRol().getTipoRol()
					
					);
			
			
		}catch(MyException e){
			e.getMessage();
		}
		return usuarioJersey;
	}
	
	/***
	 * Método que implementa WS para crear un nuevo usuario
	 * @param identificacion, Documento de identificacion del usuario
	 * @param tipoDocumento, tipo de documento de identificacion del usuario
	 * @param nombre, nombre del usuario
	 * @param apellido, apellido del usuario
	 * @param telefono, telefono del usuario
	 * @param email, direccion email del usuario
	 * @param login, login del usuario
	 * @param pw, password del usuario
	 * @param estadoUsuario, id del estado del usuario
	 * @param rol, id del rol del ususario
	 * @param loginCrea, el login del usuario que está creando el usuario
	 * @param pwCrea, la contraseña del usuario que estpa creando el usuario
	 * @throws RemoteException
	 * 
	 *  
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("registrar")
	public void registrar(@QueryParam("identificacion")String identificacion, 
			@QueryParam("tipoDocumento")String tipoDocumento,
			@QueryParam("nombre")String nombre,
			@QueryParam("apellido")String apellido, 
			@QueryParam("telefono")String telefono,
			@QueryParam("email")String email,
			@QueryParam("login")String login,
			@QueryParam("pw")String pw,
			@QueryParam("estadoUsuario")int estadoUsuario, 
			@QueryParam("rol")int rol, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			
			usuarioBL.registrarUsuario(identificacion, tipoDocumento, nombre, apellido, telefono, email, login, pw, estadoUsuario, rol, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el usuario", e);
		}
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los usuarios
	 * @return respuesta, retorna lista con todos los usuarios existetes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<UsuarioJersey> listaObtener() throws RemoteException{

		List<UsuarioJersey> respuesta = new ArrayList<UsuarioJersey>();
		
		try{
			for(Usuario usuario: usuarioBL.obtenerUsuarios()){
				UsuarioJersey usuarioJersey = new UsuarioJersey(
						usuario.getIdentificacion(),
						usuario.getTipoDocumento(),
						usuario.getNombre(),
						usuario.getApellido(),
						usuario.getTelefono(),
						usuario.getEmail(),
						usuario.getLogin(),
						usuario.getDiasSanciones(),
						usuario.getEstadoUsuario().getTipoEstado(),
						usuario.getRol().getTipoRol()
						);
				
				respuesta.add(usuarioJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los usuarios", e);
		}
		
		return respuesta;
	}
	
	
	/***
	 * Método que implementa WS para modificar usuario
	 * @param identificacion, Documento de identificacion del usuario
	 * @param tipoDocumento, tipo de documento de identificacion del usuario
	 * @param nombre, nombre del usuario
	 * @param apellido, apellido del usuario
	 * @param telefono, telefono del usuario
	 * @param email, direccion email del usuario
	 * @param login, login del usuario
	 * @param pw, password del usuario
	 * @param estadoUsuario, id del estado del usuario
	 * @param rol, id del rol del ususario
	 * @param loginCrea, el login del usuario que está creando el usuario
	 * @param pwCrea, la contraseña del usuario que estpa creando el usuario
	 * @throws RemoteException
	 * 
	 *  
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("identificacion")String identificacion, 
			@QueryParam("tipoDocumento")String tipoDocumento,
			@QueryParam("nombre")String nombre,
			@QueryParam("apellido")String apellido, 
			@QueryParam("telefono")String telefono,
			@QueryParam("email")String email,
			@QueryParam("login")String login,
			@QueryParam("pw")String pw,
			@QueryParam("estadoUsuario")int estadoUsuario, 
			@QueryParam("rol")int rol, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			usuarioBL.modificarUsuario(identificacion, tipoDocumento, nombre, apellido, telefono, email, login, pw, estadoUsuario, rol, loginCrea, pwCrea);;
		}catch(MyException e){
			throw new RemoteException("Error modificando el usuario", e);
		}
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("autenticar")
	public String autenticar(@QueryParam("login")String login, 
			@QueryParam("pass")String pass){
		
		try{
			System.out.println("usuario de validacion: " + login);
			
			usuarioBL.verificarLogin(login, pass);
			System.out.println("Se logueó correctamente");
		}catch(MyException e){
			return e.getMessage();
		}
		
		return "";
	}
}
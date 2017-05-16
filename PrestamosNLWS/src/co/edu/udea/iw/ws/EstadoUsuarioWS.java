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

import co.edu.udea.iw.bl.EstadoUsuarioBl;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.dto.EstadoUsuarioJersey;
import co.edu.udea.iw.exception.MyException;

@Path("EstadoUsuario")
@Component
public class EstadoUsuarioWS {
	
	@Autowired
	EstadoUsuarioBl estadoUsuarioBl;
	EstadoUsuario  estadoUsuario;
	
	/***
	 * Método para implementar WS de obtener estado del usuario
	 * @param idEstadoUsuario; Recibe el id del estado del usuario que se desea obtener
	 * @return retorno, String con el tipo de estado obtenido
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("obtener")
	public String autenticar(@QueryParam("idEstadoUsuario")int idEstadoUsuario){
		String retorno = "";
		
		try{
			estadoUsuario = estadoUsuarioBl.obtener(idEstadoUsuario);
			retorno = estadoUsuario.getTipoEstado();
			
		}catch(MyException e){
			return e.getMessage();
		}
		return retorno;
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los estados de un usuario
	 * @return respuesta, retorna lista con todos los estados de usuario existentes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listar")
	public List<EstadoUsuarioJersey> listaObtener() throws RemoteException{
		List<EstadoUsuarioJersey> respuesta = new ArrayList<EstadoUsuarioJersey>();
		
		try{
			for(EstadoUsuario estadoUsuario: estadoUsuarioBl.listaObtener()){
				EstadoUsuarioJersey estadoUsuarioJersey = new EstadoUsuarioJersey(
						estadoUsuario.getIdEstadoUsuario(),
						estadoUsuario.getTipoEstado()
						);
				
				respuesta.add(estadoUsuarioJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los estados de los usuarios", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para guardar un nuevo estado de usuario
	 * @param tipoRol, el tipo de estado del usuario que desea guardar
	 * @param loginCrea, el login del usuario que está creando el estado del usuario
	 * @param pwCrea, la contraseña del usuario que estpa creando el estado del usuario
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoUsuarioBl.guardar(tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el estado del usuario", e);
		}
	}
	
	/***
	 * Método que implementa WS para modificar un estado de un usuario
	 * @param id, el id del estado del usuario a modificar
	 * @param tipoRol, el cambio del tipo de estado del usuario
	 * @param loginCrea, el login del usuario que está modificando el estado del usuario
	 * @param pwCrea, la contraseña del usuario que está modificando el estado del usuario
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idEstadoUsuario")int idEstadoUsuario,
			@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoUsuarioBl.modificar(idEstadoUsuario, tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error modificando el estado del usuario", e);
		}
	}
	

}

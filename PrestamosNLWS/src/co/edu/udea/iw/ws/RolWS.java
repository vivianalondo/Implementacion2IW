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


import co.edu.udea.iw.bl.RolBl;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.RolJersey;
import co.edu.udea.iw.exception.MyException;


/***
 * Clase para implementar los servicios web de Rol
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@Path("Rol")
@Component
public class RolWS {
	
	@Autowired
	RolBl rolBl;
	Rol rol;
	
	/***
	 * Método para implementar WS de obtener Rol
	 * @param idRol; Recibe el id del rol que se desea obtener
	 * @return retorno, String con el tipo de rol obtenido
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("obtener")
	public String obtener(@QueryParam("idRol")int idRol){
		String retorno = "";
		
		try{
			rol = rolBl.obtener(idRol);
			retorno = rol.getTipoRol();
			
		}catch(MyException e){
			return e.getMessage();
		}
		return retorno;
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los roles
	 * @return respuesta, retorna lista con todos los roles existetes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listar")
	public List<RolJersey> listaObtener() throws RemoteException{
		List<RolJersey> respuesta = new ArrayList<RolJersey>();
		
		try{
			for(Rol rol: rolBl.listaObtener()){
				RolJersey rolJersey = new RolJersey(
						rol.getIdRol(),
						rol.getTipoRol()
						);
				
				respuesta.add(rolJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los roles", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para guardar un nuevo rol
	 * @param tipoRol, el tipo de rol que desea guardar
	 * @param loginCrea, el login del usuario que está creando el rol
	 * @param pwCrea, la contraseña del usuario que estpa creando el rol
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("tipoRol")String tipoRol, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			rolBl.guardar(tipoRol, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el rol", e);
		}
	}
	
	/***
	 * Método que implementa WS para modificar un rol
	 * @param id, el id del rol a modificar
	 * @param tipoRol, el cambio del tipo de rol
	 * @param loginCrea, el login del usuario que está modificando el rol
	 * @param pwCrea, la contraseña del usuario que está modificando el rol
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idRol")int id,
			@QueryParam("tipoRol")String tipoRol, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			rolBl.modificar(id, tipoRol, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error modificando el rol", e);
		}
	}
	
}

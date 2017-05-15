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

import co.edu.udea.iw.bl.EstadoDispositivoBl;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.EstadoDispositivoJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de EstadoDispositivo
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@Path("EstadoDispositivo")
@Component
public class EstadoDispositivoWS {
	
	@Autowired
	EstadoDispositivoBl estadoDispositivoBl;
	EstadoDispositivo  estadoDispositivo;
	
	/***
	 * Método para implementar WS de obtener estado del dispositivo
	 * @param idEstadoDispositivo; Recibe el id del estado del dispositivo que se desea obtener
	 * @return retorno, String con el tipo de estado obtenido
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("obtener")
	public String autenticar(@QueryParam("idEstadoDispositivo")int idEstadoDispositivo){
		String retorno = "";
		
		try{
			estadoDispositivo = estadoDispositivoBl.obtener(idEstadoDispositivo);
			retorno = estadoDispositivo.getTipoEstadoDispositivo();
			
		}catch(MyException e){
			return e.getMessage();
		}
		return retorno;
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los estados de un dispositivo
	 * @return respuesta, retorna lista con todos los estados de dispositivo existentes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listar")
	public List<EstadoDispositivoJersey> listaObtener() throws RemoteException{
		List<EstadoDispositivoJersey> respuesta = new ArrayList<EstadoDispositivoJersey>();
		
		try{
			for(EstadoDispositivo estadoDispositivo: estadoDispositivoBl.listaObtener()){
				EstadoDispositivoJersey estadoDispositivoJersey = new EstadoDispositivoJersey(
						estadoDispositivo.getIdEstadoDispositivo(),
						estadoDispositivo.getTipoEstadoDispositivo()
						);
				
				respuesta.add(estadoDispositivoJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los estados de los dispositivos", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para guardar un nuevo estado de dispositivo
	 * @param tipoRol, el tipo de estado del dispositivo que desea guardar
	 * @param loginCrea, el login del usuario que está creando el estado del dispositivo
	 * @param pwCrea, la contraseña del usuario que estpa creando el estado del dispositivo
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoDispositivoBl.guardar(tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el estado del dispositivo", e);
		}
	}
	
	/***
	 * Método que implementa WS para modificar un estado de un dispositivo
	 * @param id, el id del estado del dispositivo a modificar
	 * @param tipoRol, el cambio del tipo de estado del dispositivo
	 * @param loginCrea, el login del usuario que está modificando el estado del dispositivo
	 * @param pwCrea, la contraseña del usuario que está modificando el estado del dispositivo
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idEstadoDispositivo")int idEstadoDispositivo,
			@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoDispositivoBl.modificar(idEstadoDispositivo, tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error modificando el estado del dispositivo", e);
		}
	}
}

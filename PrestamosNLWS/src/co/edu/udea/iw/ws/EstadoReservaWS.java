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

import co.edu.udea.iw.bl.EstadoReservaBl;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.EstadoReservaJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de Estado reserva
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */


@Path("EstadoReserva")
@Component
public class EstadoReservaWS {

	@Autowired
	EstadoReservaBl estadoReservaBl;
	EstadoReserva  estadoReserva;
	
	/***
	 * Método para implementar WS de obtener estado de la reserva
	 * @param idEstadoUsuario; Recibe el id del estado de la reserva que se desea obtener
	 * @return retorno, String con el tipo de estado obtenido
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("obtener")
	public String autenticar(@QueryParam("idEstadoReserva")int idEstadoReserva){
		String retorno = "";
		
		try{
			estadoReserva = estadoReservaBl.obtener(idEstadoReserva);
			retorno = estadoReserva.getTipoEstadoReserva();
			
		}catch(MyException e){
			return e.getMessage();
		}
		return retorno;
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los estados de una reserva
	 * @return respuesta, retorna lista con todos los estados de reserva existentes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listar")
	public List<EstadoReservaJersey> listaObtener() throws RemoteException{
		List<EstadoReservaJersey> respuesta = new ArrayList<EstadoReservaJersey>();
		
		try{
			for(EstadoReserva estadoReserva: estadoReservaBl.listaObtener()){
				EstadoReservaJersey estadoReservaJersey = new EstadoReservaJersey(
						estadoReserva.getIdEstadoReserva(),
						estadoReserva.getTipoEstadoReserva()
						);
				
				respuesta.add(estadoReservaJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los estados de las reservas", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para guardar un nuevo estado de reserva
	 * @param tipoRol, el tipo de estado de la reserva que desea guardar
	 * @param loginCrea, el login del usuario que está creando el estado de la reserva
	 * @param pwCrea, la contraseña del usuario que está creando el estado de la reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoReservaBl.guardar(tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el estado de la reserva", e);
		}
	}
	
	/***
	 * Método que implementa WS para modificar un estado de una reserva
	 * @param id, el id del estado de la reserva a modificar
	 * @param tipoRol, el cambio del tipo de estado de la reserva
	 * @param loginCrea, el login del usuario que está modificando el estado de la reserva
	 * @param pwCrea, la contraseña del usuario que está modificando el estado de la reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idEstadoReserva")int idEstadoReserva,
			@QueryParam("tipoEstado")String tipoEstado, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			estadoReservaBl.modificar(idEstadoReserva, tipoEstado, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error modificando el estado de la reserva", e);
		}
	}
}

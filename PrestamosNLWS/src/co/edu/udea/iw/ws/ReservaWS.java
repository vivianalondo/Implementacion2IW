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

import co.edu.udea.iw.bl.ReservaBl;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.dto.ReservaJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de Reserva
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@Path("Reserva")
@Component
public class ReservaWS {

	@Autowired
	ReservaBl reservaBL;
	Reserva reserva;
	
	/***
	 * Método que implementa WS para obtener la lista de las reservas
	 * @return respuesta, retorna lista con todas las reservas existetes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<ReservaJersey> listaObtener() throws RemoteException{

		List<ReservaJersey> respuesta = new ArrayList<ReservaJersey>();
		
		try{
			for(Reserva reserva: reservaBL.listaObtener()){
				
				ReservaJersey reservaJersey = null;
				if (reserva.getFechaEntrega()!=null&&reserva.getHoraEntrega()!=null) {
					
					reservaJersey = new ReservaJersey(
							reserva.getIdReserva(),
							reserva.getEstadoReserva().getIdEstadoReserva(),
							reserva.getFechaRealizacion().toString(),
							reserva.getHoraInicio(),
							reserva.getHoraFinal(),
							reserva.getHoraRealizado(),
							reserva.getFechaReserva().toString(),
							reserva.getUsuario().getIdentificacion(),
							reserva.getFechaEntrega().toString(),
							reserva.getHoraEntrega()
							);
				}else{
					
					reservaJersey = new ReservaJersey(
							reserva.getIdReserva(),
							reserva.getEstadoReserva().getIdEstadoReserva(),
							reserva.getFechaRealizacion().toString(),
							reserva.getHoraInicio(),
							reserva.getHoraFinal(),
							reserva.getHoraRealizado(),
							reserva.getFechaReserva().toString(),
							reserva.getUsuario().getIdentificacion(),
							"",
							""
							);
				}
				
				
				respuesta.add(reservaJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando las reservas", e);
		}
		
		return respuesta;
	}
	
	
	/***
	 * Método que implementa WS para crear un nuevo usuario
	 * @param fechaReserva, fecha de la reserva
	 * @param horaInicio, hora inicial de la reserva
	 * @param horaFinal, hora final de la reserva
	 * @param loginCrea, el login del usuario que está creando la reserva
	 * @param psw, la contraseña del usuario que está creando la reserva
	 * @throws RemoteException
	 * 
	 *  
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("fechaReserva")String fechaReserva, 
			@QueryParam("horaInicio")String horaInicio,
			@QueryParam("horaFinal")String horaFinal, 
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("psw")String psw) throws RemoteException {
		
		try{
			
			reservaBL.guardar(fechaReserva, horaInicio, horaFinal, loginCrea, psw);
		}catch(MyException e){
			throw new RemoteException("Error creando la reserva", e);
		}
	}

	
	/***
	 * Método que implementa WS para modificar reserva
	 * @param idReserva, id de la reserva a modificar
	 * @param fechaReserva, fecha de reserva
	 * @param horaInicio, hora de inicio de la reserva
	 * @param horaFinal, hora final  de la reserva
	 * @param fechaEntrega, fecha de entrega de la reserva
	 * @param horaEntrega, hora de entrega de la reserva
	 * @param estadoreserva, estado de la reserva 
	 * @param loginCrea, login del usuario que crea la reserva
	 * @param psw, password del usuario que crea la reserva
	 * @throws RemoteException
	 * 
	 *  
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idReserva")int idReserva, 
			@QueryParam("fechaReserva")String fechaReserva,
			@QueryParam("horaInicio")String horaInicio,
			@QueryParam("horaFinal")String horaFinal, 
			@QueryParam("fechaEntrega")String fechaEntrega,
			@QueryParam("horaEntrega")String horaEntrega,
			@QueryParam("estadoreserva")int estadoreserva,
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("psw")String psw) throws RemoteException {
		try{
			reservaBL.modificar(idReserva, fechaReserva, horaInicio, horaFinal, fechaEntrega, horaEntrega, estadoreserva, loginCrea, psw);
		}catch(MyException e){
			throw new RemoteException("Error modificando la reserva", e);
		}
	}
}

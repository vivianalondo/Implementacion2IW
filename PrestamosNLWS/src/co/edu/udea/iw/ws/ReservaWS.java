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
import co.edu.udea.iw.dto.UsuarioJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de Reserva
 * @author Viviana Londo�o, Johanna Arenas, Oscar Lopera
 *
 */

@Path("Reserva")
@Component
public class ReservaWS {

	@Autowired
	ReservaBl reservaBL;
	Reserva reserva;
	
	
	/***
	 * M�todo para implementar WS de obtener Ususario
	 * @param identificacion; Recibe el documento de identificacion del Ususario que se desea obtener
	 * @return usuarioJersey, retorna el usuario obtenido
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obtener")
	public ReservaJersey obtener(@QueryParam("idReserva")int idReserva){
		ReservaJersey reservaJersey = null;
		String fEntrega;
		
		try{
			reserva = reservaBL.obtener(idReserva);
			
			if (reserva.getFechaEntrega()==null) {
				fEntrega = "";
			}else{
				fEntrega = reserva.getFechaEntrega().toString();
			}
			
			reservaJersey = new ReservaJersey(
				reserva.getIdReserva(),
				reserva.getEstadoReserva().getTipoEstadoReserva(),
				reserva.getFechaRealizacion().toString(),
				reserva.getHoraInicio(),
				reserva.getHoraFinal(),
				reserva.getHoraRealizado(),
				reserva.getFechaReserva().toString(),
				reserva.getUsuario().getIdentificacion(),
				fEntrega,
				reserva.getHoraEntrega()
					
			);
			
			
		}catch(MyException e){
			e.getMessage();
		}
		return reservaJersey;
	}
	
	/***
	 * M�todo que implementa WS para obtener la lista de las reservas
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
							reserva.getEstadoReserva().getTipoEstadoReserva(),
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
							reserva.getEstadoReserva().getTipoEstadoReserva(),
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
	 * M�todo que implementa WS para eliminar un dispositivo
	 * @param iddispositivo, el id del dispositivo a eliminar
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("eliminar")
	public void eliminar(@QueryParam("idReserva")int idReserva) throws RemoteException {
		try{
			reservaBL.eliminar(idReserva);
		}catch(MyException e){
			throw new RemoteException("Error cancelando la reserva", e);
		}
	}
	
	
	/***
	 * M�todo que implementa WS para crear un nuevo usuario
	 * @param fechaReserva, fecha de la reserva
	 * @param horaInicio, hora inicial de la reserva
	 * @param horaFinal, hora final de la reserva
	 * @param loginCrea, el login del usuario que est� creando la reserva
	 * @param psw, la contrase�a del usuario que est� creando la reserva
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
			@QueryParam("loginCrea")String loginCrea) throws RemoteException {
		
		try{
			
			reservaBL.guardar(fechaReserva, horaInicio, horaFinal, loginCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando la reserva", e);
		}
	}

	
	/***
	 * M�todo que implementa WS para modificar reserva
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
			@QueryParam("loginCrea")String loginCrea) throws RemoteException {
		try{
			reservaBL.modificar(idReserva, fechaReserva, horaInicio, horaFinal, fechaEntrega, horaEntrega, estadoreserva, loginCrea);
		}catch(MyException e){
			throw new RemoteException("Error modificando la reserva", e);
		}
	}
}

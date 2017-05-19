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

import co.edu.udea.iw.bl.EquipoXReservaBl;
import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.dto.EquipoXReservaJersey;
import co.edu.udea.iw.exception.MyException;


/***
 * Clase para implementar los servicios web de Equipo x Reserva
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */
@Path("EquipoXReserva")
@Component
public class EquipoXReservaWS {

	@Autowired
	EquipoXReservaBl equipoXReservaBl;
	
	/***
	 * Método que implementa WS para obtener los equipos x reservas
	 * @return respuesta, retorna lista con todos los equipos x reservas
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("listar")
	public List<EquipoXReservaJersey> listaObtener() throws RemoteException{
		List<EquipoXReservaJersey> respuesta = new ArrayList<EquipoXReservaJersey>();
		
		try{
			for(EquipoXReserva equipoXReserva: equipoXReservaBl.obtenerEquiposXReserva()){
				EquipoXReservaJersey equipoXReservaJersey = new EquipoXReservaJersey(
						equipoXReserva.getEquiposXReservaId().getIdReserva().getIdReserva(),
						equipoXReserva.getEquiposXReservaId().getIdDispositivo().getIdDispositivo(),
						equipoXReserva.getEquiposXReservaId().getEstadoReserva().getIdEstadoReserva()
						);
				
				respuesta.add(equipoXReservaJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los equipos x reserva", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para para guardar un equipo por reserva
	 * @param reserva, numero de identificación de la reserva
	 * @param dispositivo número de identificación del dispositivo al que se hizo una reserva
	 * @param estadoReserva estado de la reserva
	 * @param loginCrea, el login del usuario que está modificando el equipo por reserva
	 * @param pwCrea, la contraseña del usuario que está modificando el equipo por reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("registrar")
	public void registrar(@QueryParam("reserva")int reserva,
			@QueryParam("dispositivo")int dispositivo,
			@QueryParam("estadoReserva")int estadoReserva,
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			equipoXReservaBl.registrarEquipoXReserva(reserva, dispositivo, estadoReserva, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el equipo x reserva", e);
		}
	}
	
	
	/***
	 * Método que implementa WS para para modificar un equipo por reserva
	 * @param reserva, numero de identificación de la reserva
	 * @param dispositivo número de identificación del dispositivo al que se hizo una reserva
	 * @param estadoReserva estado de la reserva
	 * @param loginCrea, el login del usuario que está modificando el equipo por reserva
	 * @param pwCrea, la contraseña del usuario que está modificando el equipo por reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("reserva")int reserva,
			@QueryParam("dispositivo")int dispositivo,
			@QueryParam("estadoReserva")int estadoReserva,
			@QueryParam("loginCrea")String loginCrea,
			@QueryParam("pwCrea")String pwCrea) throws RemoteException {
		try{
			equipoXReservaBl.modificarBl(reserva, dispositivo, estadoReserva, loginCrea, pwCrea);
		}catch(MyException e){
			throw new RemoteException("Error creando el equipo x reserva", e);
		}
	}
	
}

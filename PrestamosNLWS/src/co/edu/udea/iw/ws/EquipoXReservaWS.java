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
 * @author Viviana Londo�o, Johanna Arenas, Oscar Lopera
 *
 */
@Path("EquipoXReserva")
@Component
public class EquipoXReservaWS {

	@Autowired
	EquipoXReservaBl equipoXReservaBl;
	
	/***
	 * M�todo que implementa WS para obtener los equipos x reservas
	 * @return respuesta, retorna lista con todos los equipos x reservas
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<EquipoXReservaJersey> listaObtener() throws RemoteException{
		List<EquipoXReservaJersey> respuesta = new ArrayList<EquipoXReservaJersey>();
		
		try{
			for(EquipoXReserva equipoXReserva: equipoXReservaBl.obtenerEquiposXReserva()){
				EquipoXReservaJersey equipoXReservaJersey = new EquipoXReservaJersey(
						equipoXReserva.getEquiposXReservaId().getIdReserva().getIdReserva(),
						equipoXReserva.getEquiposXReservaId().getIdDispositivo().getIdDispositivo(),
						equipoXReserva.getEstadoReserva().getIdEstadoReserva()
						);
				
				respuesta.add(equipoXReservaJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los equipos x reserva", e);
		}
		
		return respuesta;
	}
	
	/***
	 * M�todo que implementa WS para para guardar un equipo por reserva
	 * @param reserva, numero de identificaci�n de la reserva
	 * @param dispositivo n�mero de identificaci�n del dispositivo al que se hizo una reserva
	 * @param estadoReserva estado de la reserva
	 * @param loginCrea, el login del usuario que est� modificando el equipo por reserva
	 * @param pwCrea, la contrase�a del usuario que est� modificando el equipo por reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("registrar")
	public void registrar(@QueryParam("reserva")int reserva,
			@QueryParam("dispositivo")int dispositivo,
			@QueryParam("estadoReserva")int estadoReserva) throws RemoteException {
		try{
			equipoXReservaBl.registrarEquipoXReserva(reserva, dispositivo, estadoReserva);
		}catch(MyException e){
			throw new RemoteException("Error creando el equipo x reserva", e);
		}
	}
	
	
	/***
	 * M�todo que implementa WS para para modificar un equipo por reserva
	 * @param reserva, numero de identificaci�n de la reserva
	 * @param dispositivo n�mero de identificaci�n del dispositivo al que se hizo una reserva
	 * @param estadoReserva estado de la reserva
	 * @param loginCrea, el login del usuario que est� modificando el equipo por reserva
	 * @param pwCrea, la contrase�a del usuario que est� modificando el equipo por reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("reserva")int reserva,
			@QueryParam("dispositivo")int dispositivo,
			@QueryParam("estadoReserva")int estadoReserva) throws RemoteException {
		try{
			equipoXReservaBl.modificarBl(reserva, dispositivo, estadoReserva);
		}catch(MyException e){
			throw new RemoteException("Error creando el equipo x reserva", e);
		}
	}
	
	

	/***
	 * M�todo que implementa WS para obtener los equipos x reservas
	 * @return respuesta, retorna lista con todos los equipos x reservas
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarequipos")
	public List<EquipoXReservaJersey> listaObtenerXreserva(@QueryParam("reservaid")int reservaid) throws RemoteException{
		List<EquipoXReservaJersey> respuesta = new ArrayList<EquipoXReservaJersey>();
		
		try{
			for(EquipoXReserva equipoXReserva: equipoXReservaBl.obtenerXReserva(reservaid)){
				EquipoXReservaJersey equipoXReservaJersey = new EquipoXReservaJersey(
						equipoXReserva.getEquiposXReservaId().getIdReserva().getIdReserva(),
						equipoXReserva.getEquiposXReservaId().getIdDispositivo().getIdDispositivo(),
						equipoXReserva.getEstadoReserva().getIdEstadoReserva()
						);
				
				respuesta.add(equipoXReservaJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los equipos x reserva", e);
		}
		
		return respuesta;
	}
	
}

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
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.RolJersey;
import co.edu.udea.iw.exception.MyException;

@Path("EquipoXReserva")
@Component
public class EquipoXReservaWS {

	@Autowired
	EquipoXReservaBl equipoXReservaBl;
	
	/***
	 * Método que implementa WS para obtener la lista de los usuarios
	 * @return respuesta, retorna lista con todos los usuarios existetes
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
	 * Registrar
	 * @param reserva
	 * @param dispositivo
	 * @param estadoReserva
	 * @param loginCrea
	 * @param pwCrea
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
			throw new RemoteException("Error creando el rol", e);
		}
	}
	
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
			throw new RemoteException("Error creando el rol", e);
		}
	}
	
}

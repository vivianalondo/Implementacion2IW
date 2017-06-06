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

import co.edu.udea.iw.bl.DispositivoBl;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.DispositivoJersey;
import co.edu.udea.iw.exception.MyException;

/***
 * Clase para implementar los servicios web de Dispositivo
 * @author Viviana Londoño, Johanna Arenas, Oscar Lopera
 *
 */

@Path("Dispositivo")
@Component
public class DispositivoWS {

	@Autowired
	DispositivoBl dispositivoBl;
	Dispositivo  dispositivo;
	
	
	
	/***
	 * Método para implementar WS de obtener dispositivo
	 * @param idDispositivo, Recibe el id del dispositivo que desea obtener
	 * @return dispositivoJersey, retorna el dispositivo encontrado según el id que se ingresó
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obtener")
	public DispositivoJersey obtener(@QueryParam("idDispositivo")int idDispositivo){
		DispositivoJersey dispositivoJersey = null;
		
		try{
			dispositivo = dispositivoBl.obtener(idDispositivo);
			dispositivoJersey = new DispositivoJersey(
					dispositivo.getIdDispositivo(),
					dispositivo.getNombre(),
					dispositivo.getEstadoDispositivo().getTipoEstadoDispositivo(),
					dispositivo.getDescripcion()
					);
			
		}catch(MyException e){
			e.getMessage();
		}
		return dispositivoJersey;
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los dispositivos existentes
	 * @return respuesta, retorna lista con todos los dispositivos existentes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<DispositivoJersey> listaObtener() throws RemoteException{
		List<DispositivoJersey> respuesta = new ArrayList<DispositivoJersey>();
		
		try{
			for(Dispositivo dispositivo: dispositivoBl.listaObtener()){
				DispositivoJersey dispositivoJersey = new DispositivoJersey(
						dispositivo.getIdDispositivo(),
						dispositivo.getNombre(),
						dispositivo.getEstadoDispositivo().getTipoEstadoDispositivo(),
						dispositivo.getDescripcion()
						);
				
				respuesta.add(dispositivoJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los dispositivos", e);
		}
		
		return respuesta;
	}
	
	/***
	 * Método que implementa WS para guardar un nuevo dispositivo
	 * @param nombre, el nombre del dispositivo que desea guardar
	 * @param estado, el estado del dispositivo que desea guardar
	 * @param descripcion, la descripción del dispositivo que desea guardar
	 * @param loginCrea, el login del usuario que está creando el estado de la reserva
	 * @param pwCrea, la contraseña del usuario que está creando el estado de la reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("guardar")
	public void guardar(@QueryParam("nombre")String nombre,
			@QueryParam("estado")int estado,
			@QueryParam("descripcion")String descripcion)
			throws RemoteException {
		try{
			dispositivoBl.guardar(nombre, estado, descripcion);
		}catch(MyException e){
			throw new RemoteException("Error creando el dispositivo", e);
		}
	}
	
	/***
	 * Método que implementa WS para modificar un dispositivo
	 * @param idDispositivo, el id del dispositivo que se va a modificar
	 * @param nombre, nombre a modificar
	 * @param estado, estado a modificar
	 * @param descripcion, descripción con la modificación
	 * @param loginCrea, el login del usuario que está modificando el estado de la reserva
	 * @param pwCrea, la contraseña del usuario que está modificando el estado de la reserva
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("modificar")
	public void modificar(@QueryParam("idDispositivo")int idDispositivo,
			@QueryParam("nombre")String nombre,
			@QueryParam("estado")int estado,
			@QueryParam("descripcion")String descripcion) throws RemoteException {
		try{
			dispositivoBl.modificar(idDispositivo, nombre, estado, descripcion);
		}catch(MyException e){
			throw new RemoteException("Error modificando el dispositivo", e);
		}
	}
	
	
	
	/***
	 * Método que implementa WS para eliminar un dispositivo
	 * @param iddispositivo, el id del dispositivo a eliminar
	 * @throws RemoteException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Path("eliminar")
	public void eliminar(@QueryParam("idDispositivo")int iddispositivo) throws RemoteException {
		try{
			dispositivoBl.eliminar(iddispositivo);
		}catch(MyException e){
			throw new RemoteException("Error eliminando el dispositivo", e);
		}
	}
	
	/***
	 * Método que implementa WS para obtener la lista de los dispositivos existentes según el nombre ingresado
	 * @param listarPorNombre, nombre de los dispositivos que se quieren listar
	 * @return respuesta, retorna lista con todos los dispositivos existentes
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listarPorNombre")
	public List<DispositivoJersey> listarPorNombre(@QueryParam("nombreDispositivo")String nombreDispositivo) throws RemoteException{
		List<DispositivoJersey> respuesta = new ArrayList<DispositivoJersey>();
		
		try{
			for(Dispositivo dispositivo: dispositivoBl.listaObtenerPorNombre(nombreDispositivo)){
				DispositivoJersey dispositivoJersey = new DispositivoJersey(
						dispositivo.getIdDispositivo(),
						dispositivo.getNombre(),
						dispositivo.getEstadoDispositivo().getTipoEstadoDispositivo(),
						dispositivo.getDescripcion()
						);
				
				respuesta.add(dispositivoJersey);
			}
		}catch(MyException e){
			throw new RemoteException("Problema consultando los dispositivos", e);
		}
		
		return respuesta;
	}
	
}

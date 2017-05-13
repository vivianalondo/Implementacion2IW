package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.exception.MyException;

/**
 * Interface para definir los métodos de estado reserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public interface EstadoReservaDAO {
	public EstadoReserva obtener(int idEstadoReservaDAO) throws MyException;
	
	public List<EstadoReserva> listaObtener() throws MyException; 

	public void guardar(EstadoReserva estadoReserva) throws MyException;
	
	public void modificar(EstadoReserva estadoReserva) throws MyException;
}

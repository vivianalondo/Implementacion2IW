package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.exception.MyException;

/**
 * Interface para definir los métodos de Reserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public interface ReservaDAO {
	
	public Reserva obtener(int idReserva) throws MyException;
	
	public List<Reserva> listaObtener() throws MyException; 

	public void guardar(Reserva reserva) throws MyException;
	
	public void modificar(Reserva reserva) throws MyException;

}

package co.edu.udea.iw.dao;

import java.util.List;

/**
 * Interface para definir los métodos de EquipoXReserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.dto.EquipoXReservaId;
import co.edu.udea.iw.exception.MyException;

public interface EquipoXReservaDAO {
	
	public EquipoXReserva obtener(EquipoXReservaId equiposXReservaId) throws MyException;
	
	public List<EquipoXReserva> listaObtener() throws MyException; 

	public void guardar(EquipoXReserva equipoXReserva) throws MyException;
	
	public void modificar(EquipoXReserva equipoXReserva) throws MyException;

}

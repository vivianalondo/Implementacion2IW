package co.edu.udea.iw.dao;

import java.util.List;


/**
 * Interface para definir los métodos de EstadoDispositivo
 * @author 
 * @version 1.0
 */
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.exception.MyException;

public interface EstadoDispositivoDAO {

	public EstadoDispositivo obtener(int idEstadoDispositivo) throws MyException;
	
	public List<EstadoDispositivo> listaObtener() throws MyException; 

	public void guardar(EstadoDispositivo estadoDispositivo) throws MyException;
	
	public void modificar(EstadoDispositivo estadoDispositivo) throws MyException;

}

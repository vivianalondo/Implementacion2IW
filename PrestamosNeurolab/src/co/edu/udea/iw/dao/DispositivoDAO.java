package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.MyException;

/**
 * Interface para definir los métodos de Dispositivo
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public interface DispositivoDAO {
	
	public Dispositivo obtener(int idDispositivo) throws MyException;
	
	public List<Dispositivo> listaObtener() throws MyException; 

	public void guardar(Dispositivo dispositivo) throws MyException;
	
	public void modificar(Dispositivo dispositivo) throws MyException;
	
	public List<Dispositivo> listaObtenerPorNombre(String nombreDispositivo) throws MyException;
	
	public List<Dispositivo> listaObtenerActivosPorNombre(String nombreDispositivo, int idEstado) throws MyException;

}

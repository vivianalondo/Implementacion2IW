package co.edu.udea.iw.dao;

import java.util.List;

/**
 * Interface para definir los métodos de estado usuario
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.exception.MyException;

public interface EstadoUsuarioDAO {

	public EstadoUsuario obtener(int idEstadoUsuario) throws MyException;
	
	public List<EstadoUsuario> listaObtener() throws MyException; 

	public void guardar(EstadoUsuario estadoUsuario) throws MyException;
	
	public void modificar(EstadoUsuario estadoUsuario) throws MyException;

}

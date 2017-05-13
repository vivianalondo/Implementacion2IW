package co.edu.udea.iw.dao;

import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.MyException;
import java.util.List;

/**
 * Interface para definir los métodos de Rol
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public interface RolDAO {

	public Rol obtener(int idRol) throws MyException;
	
	public List<Rol> listaObtener() throws MyException; 

	public void guardar(Rol rol) throws MyException;
	
	public void modificar(Rol rol) throws MyException;
	
}

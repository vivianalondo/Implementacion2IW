package co.edu.udea.iw.dao;

import java.util.List;

import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Interface para definir los métodos de Usuario
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public interface UsuarioDAO {
	
	public Usuario obtener(String identificacion) throws MyException;
	
	public Usuario obtenerPorLogin(String login) throws MyException;
	
	public List<Usuario> listaObtener() throws MyException; 

	public void guardar(Usuario usuario) throws MyException;
	
	public void modificar(Usuario usuario) throws MyException;


}

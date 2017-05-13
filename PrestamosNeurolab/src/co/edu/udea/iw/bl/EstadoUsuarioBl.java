package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoUsuarioDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class EstadoUsuarioBl {
EstadoUsuarioDAO estadoUsuarioDAO;
private UsuarioDAO usuarioDAO;
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}



	public EstadoUsuarioDAO getEstadoUsuarioDAO() {
		return estadoUsuarioDAO;
	}


	public void setEstadoUsuarioDAO(EstadoUsuarioDAO estadoUsuarioDAO) {
		this.estadoUsuarioDAO = estadoUsuarioDAO;
	}


	/**
	 * Metodo que retorna una lista con los estados de los usuarios. 
	 * @return List<EstadoUsuario>
	 * @throws MyException
	 */
	public List<EstadoUsuario> listaObtener() throws MyException{
		
		return estadoUsuarioDAO.listaObtener();
	}
	
	/**
	 * Metodo que retorna un estado de usuario, valida que el id ingresado no sea nulo, que el estado del usuario exita.
	 * @param idEstadoUsuario
	 * @return EstadoUsuario
	 * @throws MyException
	 */
	public EstadoUsuario obtener(int idEstadoUsuario) throws MyException{
		
		
		if(idEstadoUsuario==0|| "".equals(idEstadoUsuario)){
			throw new MyException("Tiene que ingresar el id del usuario");
		}
		
		//Verificación de que el estado del usuario exista
		EstadoUsuario estadoUsuario = estadoUsuarioDAO.obtener(idEstadoUsuario);
		if (estadoUsuario==null) {
			throw new MyException("El estado del usuario no se encuentra en la base de datos");
		}
		return estadoUsuarioDAO.obtener(idEstadoUsuario);
	}
	
	/**
	 * Metodo que guarda un estado de usuario. Valida los campos ingresados
	 * @param estadoUsuario
	 * @throws MyException
	 */
	public void guardar(String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoUsuario estadoUsuario = null;		
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar el tipo de usuario");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para ingresar un nuevo estado de Dispositivo");
		}
		
		//Creamos un objeto estadoDispositivo y comenzamos a llenarlo
		estadoUsuario = new EstadoUsuario();
		estadoUsuario.setTipoEstado(tipoEstado);
		estadoUsuarioDAO.guardar(estadoUsuario);
		
	}
	/**
	 * Metodo que modifica un estado de usuario. Valida los campos ingresados
	 * @param estadoUsuario
	 * @throws MyException
	 */
	public void modificar(int id, String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoUsuario estadoUsuario = null;
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar el tipo de dispositivo");
		}
		
		//Verificación de que el usuario que esté modificando el registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para modificar el registro");
		}
		
		//Creamos un objeto estadoUsuario y comenzamos a llenarlo
		estadoUsuario = estadoUsuarioDAO.obtener(id);
		estadoUsuario.setTipoEstado(tipoEstado);
		estadoUsuarioDAO.modificar(estadoUsuario);
	}
	
	/**
	 * Método para verificar que el usuario esté logueado
	 */
	public Usuario verificarLogin(String loginC, String pwC) throws MyException{
		Usuario usuarioLogueado = null;
		
		try{
			usuarioLogueado = usuarioDAO.obtenerPorLogin(loginC);
			if (usuarioLogueado==null) {
				throw new MyException("Usuario no existe en el sistema");
			}else{
	
				if(usuarioLogueado.getPassword().equals(pwC)){
					System.out.println("Los datos ingresados son correctos");
				}else{
					throw new MyException("La contraseña no es correcta");
				}
					
			}
		}catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		return usuarioLogueado;
	}

}
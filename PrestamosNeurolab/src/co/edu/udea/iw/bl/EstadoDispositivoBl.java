package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoDispositivoDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class EstadoDispositivoBl {
	
	EstadoDispositivoDAO estadoDispositivoDAO;
	private UsuarioDAO usuarioDAO;
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	public EstadoDispositivoDAO getEstadoDispositivoDAO() {
		return estadoDispositivoDAO;
	}


	public void setEstadoDispositivoDAO(EstadoDispositivoDAO estadoDispositivoDAO) {
		this.estadoDispositivoDAO = estadoDispositivoDAO;
	}

	/**
	 * Metodo que retorna una lista con los estados de los dispositivo. 
	 * @return
	 * @throws MyException
	 */
	public List<EstadoDispositivo> listaObtener() throws MyException{
		
		return estadoDispositivoDAO.listaObtener();
	}
	
	/**
	 * Metodo que retorna un estado de dispositivo, valida que el id ingresado no sea nulo
	 * @param idEstadoDispositivo
	 * @return
	 * @throws MyException
	 */
	public EstadoDispositivo obtener(int idEstadoDispositivo) throws MyException{
		
		
		if(idEstadoDispositivo==0|| "".equals(idEstadoDispositivo)){
			throw new MyException("Tiene que ingresar el id del dispositivo");
		}
		
		//Verificación de que el estado del dispositivo exista
		EstadoDispositivo estadoDispositivo = estadoDispositivoDAO.obtener(idEstadoDispositivo);
		if (estadoDispositivo==null) {
			throw new MyException("El dispositivo no se encuentra en la base de datos");
		}
		return estadoDispositivoDAO.obtener(idEstadoDispositivo);
	}
	
	/**
	 * Metodo que guarda un estado de dispositivo. Valida los campos ingresados
	 * @param estadoDispositivo
	 * @throws MyException
	 */
	public void guardar(String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoDispositivo estadoDispositivo = null;		
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar el tipo de dispositivo");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para ingresar un nuevo estado de Dispositivo");
		}
		
		//Creamos un objeto estadoDispositivo y comenzamos a llenarlo
		estadoDispositivo = new EstadoDispositivo();
		estadoDispositivo.setTipoEstadoDispositivo(tipoEstado);
		estadoDispositivoDAO.guardar(estadoDispositivo);
		
	}
	/**
	 * Metodo que modifica un estado de dispositivo. Valida que los campos ingresados no sean nulos
	 * @param estadoDispositivo
	 * @throws MyException
	 */
	public void modificar(int id, String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoDispositivo estadoDispositivo = null;
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar el tipo de dispositivo");
		}
		
		//Verificación de que el usuario que esté modificando el registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para modificar el registro");
		}
		
		//Creamos un objeto estadoDispositivo y comenzamos a llenarlo
		estadoDispositivo = estadoDispositivoDAO.obtener(id);
		estadoDispositivo.setTipoEstadoDispositivo(tipoEstado);
		estadoDispositivoDAO.modificar(estadoDispositivo);
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

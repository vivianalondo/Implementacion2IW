package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import co.edu.udea.iw.dao.EstadoReservaDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class EstadoReservaBl {
	
EstadoReservaDAO estadoReservaDAO;
private UsuarioDAO usuarioDAO;
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	public EstadoReservaDAO getEstadoReservaDAO() {
		return estadoReservaDAO;
	}


	public void setEstadoReservaDAO(EstadoReservaDAO estadoReservaDAO) {
		this.estadoReservaDAO = estadoReservaDAO;
	}

	/**
	 * Metodo que retorna una lista con los estados de la reserva. 
	 * @return List<EstadoReserva>
	 * @throws MyException
	 */
	public List<EstadoReserva> listaObtener() throws MyException{
		
		return estadoReservaDAO.listaObtener();
	}
	
	/**
	 * Metodo que retorna un estado de reserva, valida que el id ingresado no sea nulo
	 * @param idEstadoReserva
	 * @return EstadoReserva
	 * @throws MyException
	 */
	public EstadoReserva obtener(int idEstadoReserva) throws MyException{
		
		
		if(idEstadoReserva==0|| "".equals(idEstadoReserva)){
			throw new MyException("Tiene que ingresar el id de la reserva");
		}
		
		//Verificación de que el estado de la reserva exista
		EstadoReserva estadoReserva = estadoReservaDAO.obtener(idEstadoReserva);
		if (estadoReserva==null) {
			throw new MyException("El estado de la reserva no se encuentra en la base de datos");
		}
		return estadoReservaDAO.obtener(idEstadoReserva);
	}
	
	/**
	 * Metodo que guarda un estado de reserva. Valida los campos ingresados
	 * @param estadoReserva
	 * @throws MyException
	 */
	public void guardar(String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoReserva estadoReserva = null;		
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar el tipo de estado de la reserva");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para ingresar un nuevo estado de Dispositivo");
		}
		
		//Creamos un objeto estadoReserva y comenzamos a llenarlo
		estadoReserva = new EstadoReserva();
		estadoReserva.setTipoEstadoReserva(tipoEstado);
		estadoReservaDAO.guardar(estadoReserva);
		
	}
	/**
	 * Metodo que modifica un estado de la reserva. Valida que los campos ingresados no sean nulos
	 * @param estadoDispositivo
	 * @throws MyException
	 */
	public void modificar(int id, String tipoEstado, String loginCrea, String pwCrea) throws MyException{
		
		EstadoReserva estadoReserva = null;
		
		if(tipoEstado==null|| "".equals(tipoEstado)){
			throw new MyException("Tiene que ingresar estado de la reserva");
		}
		
		//Verificación de que el usuario que esté modificando el registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para modificar el registro");
		}
		
		//Creamos un objeto estadoReserva y comenzamos a llenarlo
		estadoReserva = estadoReservaDAO.obtener(id);
		estadoReserva.setTipoEstadoReserva(tipoEstado);
		estadoReservaDAO.modificar(estadoReserva);
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

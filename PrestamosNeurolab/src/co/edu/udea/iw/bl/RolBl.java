package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class RolBl {
RolDAO rolDAO;
private UsuarioDAO usuarioDAO;
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}



	public RolDAO getRolDAO() {
		return rolDAO;
	}


	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}


	/**
	 * Metodo que retorna una lista con todos los roles. 
	 * @return List<Rol>
	 * @throws MyException
	 */
	public List<Rol> listaObtener() throws MyException{
		
		return rolDAO.listaObtener();
	}
	
	/**
	 * Metodo que retorna un rol, valida que el id ingresado no sea nulo
	 * @param idRol
	 * @return Rol
	 * @throws MyException
	 */
	public Rol obtener(int idRol) throws MyException{
		
		
		if(idRol==0|| "".equals(idRol)){
			throw new MyException("Tiene que ingresar el id del rol");
		}
		
		//Verificación de que el rol exista
		Rol rol = rolDAO.obtener(idRol);
		if (rol==null) {
			throw new MyException("El rol no se encuentra en la base de datos");
		}
		return rolDAO.obtener(idRol);
	}
	
	/**
	 * Metodo que guarda un rol. Valida los campos ingresados
	 * @param tipoEstado
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
	 */
	public void guardar(String tipoRol, String loginCrea, String pwCrea) throws MyException{
		Rol rol = null;		
		
		if(tipoRol==null|| "".equals(tipoRol)){
			throw new MyException("Tiene que ingresar el tipo de rol");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para ingresar un nuevo estado de Dispositivo");
		}
		
		//Creamos un objeto rol y comenzamos a llenarlo
		rol = new Rol();
		rol.setTipoRol(tipoRol);
		rolDAO.guardar(rol);	
	}
	
	/**
	 * Metodo que modifica un rol. Valida que los campos ingresados no sean nulos
	 * @param id
	 * @param tipoEstado
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
	 */
	public void modificar(int id, String tipoRol, String loginCrea, String pwCrea) throws MyException{
		
		Rol rol = null;
		
		if(tipoRol==null|| "".equals(tipoRol)){
			throw new MyException("Tiene que ingresar el tipo de dispositivo");
		}
		
		//Verificación de que el usuario que esté modificando el registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para modificar el registro");
		}
		
		//Creamos un objeto rol y comenzamos a llenarlo
		rol = rolDAO.obtener(id);
		rol.setTipoRol(tipoRol);
		rolDAO.modificar(rol);
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
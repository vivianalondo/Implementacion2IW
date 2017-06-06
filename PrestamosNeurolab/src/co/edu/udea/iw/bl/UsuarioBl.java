package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoUsuarioDAO;
import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase con la l�gica del negocio de Usuario
 * @author Viviana Londo�o, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@Transactional
public class UsuarioBl {
	
	private UsuarioDAO usuarioDAO;
	private EstadoUsuarioDAO estadoUsuarioDAO;
	private RolDAO rolDAO;
	
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
	public RolDAO getRolDAO() {
		return rolDAO;
	}
	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}
	
	/**
	 * Metodo que retorna una lista con todos los usuarios por el nombre. 
	 * @return List<Usuario> 
	 * @param nombreDispositivo
	 * @throws MyException
	 */
	public List<Usuario> obtenerUsuarios() throws MyException{
		return usuarioDAO.listaObtener();
	}
	
	/**
	 * Metodo que retorna un rol, valida que el id ingresado no sea nulo
	 * @param idRol
	 * @return Rol
	 * @throws MyException
	 */
	public Usuario obtener(String identificacion) throws MyException{
		
		if(identificacion==""|| "".equals(identificacion)){
			throw new MyException("Tiene que ingresar el id del usuario");
		}
		
		//Verificaci�n de que el usuario exista
		System.out.println(identificacion);
		Usuario usuario = usuarioDAO.obtener(identificacion);
		if (usuario==null) {
			throw new MyException("El rol no se encuentra en la base de datos");
		}
		return usuarioDAO.obtener(identificacion);
	}
	
	
	/**
	 * M�todo para registrar un usuario. Hacemos las validaciones de campos no nulos
	 * @param identificacion
	 * @param tipoDocumento
	 * @param nombre
	 * @param tipoDocumento
	 * @param apellido
	 * @param telefono
	 * @param email
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
	 */
	public void registrarUsuario(String identificacion, String tipoDocumento, String nombre, String apellido,
			String telefono, String email, String login, String pw, int estadoUsuario, int rol)throws MyException{
		
		Usuario usuarioIngresar = null;
		Rol rolIngresar = null;
		EstadoUsuario estadoIngresar = null;
		
		//Validar que los campos no sean nulos
		if(identificacion==null|| "".equals(identificacion)){
			throw new MyException("La identificaci�n no puede estar vac�a");
		}
		if(tipoDocumento==null|| "".equals(tipoDocumento)){
			throw new MyException("El tipo de documento no puede estar vac�a");
		}
		if(nombre==null|| "".equals(nombre)){
			throw new MyException("El nombre no puede estar vac�o");
		}
		if(apellido==null|| "".equals(apellido)){
			throw new MyException("El apellido no puede estar vac�o");
		}
		if(telefono==null|| "".equals(telefono)){
			throw new MyException("El tel�fono no puede estar vac�o");
		}
		if(email==null|| "".equals(email)){
			throw new MyException("El email no puede ser vac�o");
		}
		if(login==null|| "".equals(login)){
			throw new MyException("El login no puede ser vac�o");
		}
		if(pw==null|| "".equals(pw)){
			throw new MyException("La contrase�a no puede estar vac�a");
		}
		
		if(estadoUsuario==0){
			throw new MyException("El estado del usuario no puede estar vac�o");
		}
		if(rol==0){
			throw new MyException("El rol no puede estar vac�a");
		}
		

		
		
		//Verificaci�n de la existencia del usuario, para no ingresar usuarios repetidos
		Usuario usuario = usuarioDAO.obtener(identificacion);
		if (usuario==null) {
			usuarioIngresar = new Usuario();
			rolIngresar = new Rol();
			estadoIngresar = new EstadoUsuario();
			
			usuarioIngresar.setIdentificacion(identificacion);
			usuarioIngresar.setTipoDocumento(tipoDocumento);
			usuarioIngresar.setNombre(nombre);
			usuarioIngresar.setApellido(apellido);
			usuarioIngresar.setTelefono(telefono);
			usuarioIngresar.setEmail(email);
			usuarioIngresar.setLogin(login);
			usuarioIngresar.setPassword(pw);
			rolIngresar = rolDAO.obtener(rol);
			usuarioIngresar.setRol(rolIngresar);
			estadoIngresar = estadoUsuarioDAO.obtener(estadoUsuario);
			usuarioIngresar.setEstadoUsuario(estadoIngresar);
			usuarioIngresar.setDiasSanciones(0);
			
			//Guardar el usuario utilizando m�todo guardar del DAO
			usuarioDAO.guardar(usuarioIngresar);
			
		}else{
			throw new MyException("No se puede registrar usuario, la identificaci�n ingresada ya existe");
		}
			
	}
	
	/**
	 * M�todo para modificar un usuario. Hacemos las validaciones de campos no nulos
	 * @param identificacion
	 * @param tipoDocumento
	 * @param nombre
	 * @param tipoDocumento
	 * @param apellido
	 * @param telefono
	 * @param email
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
	 */
	public void modificarUsuario(String identificacion, String tipoDocumento, String nombre, String apellido,
			String telefono, String email, String login, String pw, int estadoUsuario, int rol)throws MyException{
		
		Usuario usuarioIngresar = null;
		Rol rolModificar = null;
		EstadoUsuario estadoModificar = null;
		
		//Validar que los campos no sean nulos
		if(identificacion==null|| "".equals(identificacion)){
			throw new MyException("La identificaci�n no puede estar vac�a");
		}
		if(tipoDocumento==null|| "".equals(tipoDocumento)){
			throw new MyException("El tipo de documento no puede estar vac�a");
		}
		if(nombre==null|| "".equals(nombre)){
			throw new MyException("El nombre no puede estar vac�o");
		}
		if(apellido==null|| "".equals(apellido)){
			throw new MyException("El apellido no puede estar vac�o");
		}
		if(telefono==null|| "".equals(telefono)){
			throw new MyException("El tel�fono no puede estar vac�o");
		}
		if(email==null|| "".equals(email)){
			throw new MyException("El email no puede ser vac�o");
		}
		if(login==null|| "".equals(login)){
			throw new MyException("El login no puede ser vac�o");
		}
		if(pw==null|| "".equals(pw)){
			throw new MyException("La contrase�a no puede estar vac�a");
		}
		
		if(estadoUsuario==0){
			throw new MyException("El estado del usuario no puede estar vac�o");
		}
		if(rol==0){
			throw new MyException("El rol no puede estar vac�a");
		}
		
		
		//Verificaci�n de la existencia del usuario, para no ingresar usuarios repetidos
		Usuario usuarioModificar = usuarioDAO.obtener(identificacion);
		if (usuarioModificar==null) {
			throw new MyException("El usuario a modificar no existe");
		}
			
			rolModificar = new Rol();
			estadoModificar = new EstadoUsuario();
			
			usuarioModificar.setIdentificacion(identificacion);
			usuarioModificar.setTipoDocumento(tipoDocumento);
			usuarioModificar.setNombre(nombre);
			usuarioModificar.setApellido(apellido);
			usuarioModificar.setTelefono(telefono);
			usuarioModificar.setEmail(email);
			usuarioModificar.setLogin(login);
			usuarioModificar.setPassword(pw);
			rolModificar = rolDAO.obtener(rol);
			usuarioModificar.setRol(rolModificar);
			estadoModificar = estadoUsuarioDAO.obtener(estadoUsuario);
			usuarioModificar.setEstadoUsuario(estadoModificar);
			usuarioModificar.setDiasSanciones(0);
			
			//Guardar el usuario utilizando m�todo guardar del DAO
			usuarioDAO.modificar(usuarioModificar);
			
	}
	
	
	/**
	 * M�todo para verificar que el usuario est� logueado
	 * @param loginC
	 * @param pwC
	 * @return Usuario
	 * @throws MyException
	 */
	public Usuario verificarLogin(String loginC, String pwC) throws MyException{
		Usuario usuarioLogueado = null;
		
		//Validar que los campos no sean nulos
		
		if(loginC==null|| "".equals(loginC)){
			throw new MyException("El login no puede estar vac�a");
		}
		if(pwC==null|| "".equals(pwC)){
			throw new MyException("La contrase�a no puede estar vac�a");
		}
				
		
			usuarioLogueado = usuarioDAO.obtenerPorLogin(loginC);
			if (usuarioLogueado==null) {
				throw new MyException("Usuario no existe en el sistema");
			}else{
				
				if(usuarioLogueado.getPassword().equals(pwC)){
					System.out.println("Los datos ingresados son correctos");
				}else{
					throw new MyException("La contrase�a no es correcta");
				}		
			}
		
		
		
		return usuarioLogueado;
	}

	public void eliminar(String identificacion)throws MyException{
		
		//Validar que los campos no sean nulos
				
				if(identificacion == null||identificacion.equals("")){
					throw new MyException("ID del usuario no puede ser vacio");
				}
		
				Usuario usuario = new Usuario();
				usuario = usuarioDAO.obtener(identificacion);
				if(usuario == null){
					throw new MyException("El usuario a eliminar no existe");
				}
		
		
				EstadoUsuario  estadoUsuario = usuario.getEstadoUsuario();
				if(estadoUsuario.getIdEstadoUsuario()==2){
					throw new MyException("El usuario ya se encuentra inhabilitado");
				}
				
				
				estadoUsuario = estadoUsuarioDAO.obtener(2);
				usuario.setEstadoUsuario(estadoUsuario);
								
				usuarioDAO.modificar(usuario);
		
		
	}


}

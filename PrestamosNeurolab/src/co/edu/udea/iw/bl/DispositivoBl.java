package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dao.EstadoDispositivoDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase con la lógica del negocio de Dispositivo
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@Transactional
public class DispositivoBl {
	
	private DispositivoDAO dispositivoDAO;
	private EstadoDispositivoDAO estadodispositivoDAO;
	private UsuarioDAO usuarioDAO;

	public DispositivoDAO getDispositivoDao() {
		return dispositivoDAO;
	}

	public void setDispositivoDao(DispositivoDAO dispositivoDao) {
		this.dispositivoDAO = dispositivoDao;
	}
	
	
	public EstadoDispositivoDAO getEstadodispositivoDAO() {
		return estadodispositivoDAO;
	}

	public void setEstadodispositivoDAO(EstadoDispositivoDAO estadodispositivoDAO) {
		this.estadodispositivoDAO = estadodispositivoDAO;
	}
	

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	/**
	 * Metodo que retorna un dispositivo, valida que el id ingresado no sea nulo
	 * @param idDispositivo
	 * @return Dispositivo
	 * @throws MyException
	 */
	public Dispositivo obtener(int idDispositivo) throws MyException{
		
		if(idDispositivo==0|| "".equals(idDispositivo)){
			throw new MyException("Tiene que ingresar el id del rol");
		}
		
		//Verificación de que el dispositivo exista
		Dispositivo dispositivo = dispositivoDAO.obtener(idDispositivo);
		if (dispositivo==null) {
			throw new MyException("El dispositivo no se encuentra en la base de datos");
		}
		return dispositivoDAO.obtener(idDispositivo);
	}

	/**
	 * Método que guarda un dispositivo. Valida los campos ingresados y verifica el usuario
	 * que guarda el nuevo registro
	 * @param nombre
	 * @param descripcion
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
 	*/
	public void guardar(String nombre, int estado, String descripcion, String loginCrea, String pwCrea)throws MyException{
		//Validar que los campos no sean nulos
				if(nombre==null|| "".equals(nombre)){
					throw new MyException("El nombre de dispositivo no puede estar vacio");
				}
				EstadoDispositivo  estadoDispositivo = estadodispositivoDAO.obtener(estado);
				if(estadoDispositivo == null){
					throw new MyException("Debe seleccionar un estado");
				}
				if(descripcion==null|| "".equals(descripcion)){
					throw new MyException("La descripcion del dispositivo no puede estar vacia");
				}
				//Verificación de que el usuario que esté creando un nuevo registro sea administrador
				  Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
				  if (usuarioCrea.getRol().getIdRol()!=1) {
				   throw new MyException("No tiene permisos para ingresar un nuevo usuario");
				  }
				
				Dispositivo dispositivo = new Dispositivo();
				
				dispositivo.setNombre(nombre);
				dispositivo.setEstadoDispositivo(estadoDispositivo);
				dispositivo.setDescripcion(descripcion);
				
				dispositivoDAO.guardar(dispositivo);
		
		
	}
	
	/**
	 * Método que permite imhabilitar dispositivos. Valida los campos ingresados, que el dispositivo exista y verifica el usuario
	 * que elimina el dispositivo.
	 * @param iddispositivo
	 * @throws MyException
 	*/
	public void eliminar(int iddispositivo)throws MyException{
		
		//Validar que los campos no sean nulos
				
				if(iddispositivo == 0){
					throw new MyException("ID del dispositivo no puede ser vacio");
				}
		
				Dispositivo dispositivo = new Dispositivo();
				dispositivo = dispositivoDAO.obtener(iddispositivo);
				if(dispositivo == null){
					throw new MyException("El dispositivo a eliminar no existe");
				}
		
		
				EstadoDispositivo  estadoDispositivo = dispositivo.getEstadoDispositivo();
				if(estadoDispositivo.idEstadoDispositivo==2){
					throw new MyException("El dispositivo ya se encuentra inhabilitado");
				}
				
				
				estadoDispositivo = estadodispositivoDAO.obtener(2);
				dispositivo.setEstadoDispositivo(estadoDispositivo);
								
				dispositivoDAO.modificar(dispositivo);
		
		
	}
	
	/**
	 * Metodo que retorna una lista con los dispositivo por el nombre. 
	 * @return dispositivos
	 * @param nombreDispositivo
	 * @throws MyException
	 */
	public List<Dispositivo> listaObtenerPorNombre(String nombreDispositivo) throws MyException{
		
		if(nombreDispositivo==null|| "".equals(nombreDispositivo)){
			throw new MyException("El nombre de dispositivo no puede estar vacio");
		}
		
		List<Dispositivo> dispositivos = new ArrayList();
		
		dispositivos = dispositivoDAO.listaObtenerPorNombre(nombreDispositivo);
		return dispositivos;
	}
	
	/**
	 * Método para verificar que el usuario esté logueado
	 * @param loginC
	 * @param pwC
	 * @return Usuario
	 * @throws MyException
	 */
	 public Usuario verificarLogin(String loginC, String pwC) throws MyException{
		 System.out.println("pasó por aquí");
		 Usuario usuarioLogueado = null;
		 
		 try{
			 usuarioLogueado = usuarioDAO.obtenerPorLogin(loginC);
			 System.out.println(usuarioLogueado);
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
	 
	 
	 public void modificar(int idDispositivo , String nombre, int estado, String descripcion, String loginCrea, String pwCrea)throws MyException{
					//Validar que los campos no sean nulos
					
					if (idDispositivo==0) {
							throw new MyException("El campo idDispositivo del dispositivo no puede estar vacio");
						}
						
					Dispositivo  dispositivo = dispositivoDAO.obtener(idDispositivo);
					if(dispositivo == null){
						throw new MyException("El dispositivo no existe en el sistema");
						}
		 			
		 			if(nombre==null|| "".equals(nombre)){
						throw new MyException("El nombre de dispositivo no puede estar vacio");
					}
		 			
		 			if (estado==0) {
		 				throw new MyException("El estado del dispositivo no puede estar vacio");
					}
		 			
					EstadoDispositivo  estadoDispositivo = estadodispositivoDAO.obtener(estado);
					if(estadoDispositivo == null){
						throw new MyException("Debe seleccionar un estado valido");
					}
					if(descripcion==null|| "".equals(descripcion)){
						throw new MyException("La descripcion del dispositivo no puede estar vacia");
					}
					//Verificación de que el usuario que esté modificando un nuevo registro sea administrador
					  Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
					  if (usuarioCrea.getRol().getIdRol()!=1) {
					   throw new MyException("No tiene permisos para ingresar un nuevo usuario");
					  }
					
					dispositivo.setNombre(nombre);
					dispositivo.setEstadoDispositivo(estadoDispositivo);
					dispositivo.setDescripcion(descripcion);
					
					dispositivoDAO.modificar(dispositivo);
			
			
		}
	 
	 	//Metodo para listar dispositivos
		public List<Dispositivo> listaObtener() throws MyException{

			
			List<Dispositivo> dispositivos = new ArrayList();
			
			dispositivos = dispositivoDAO.listaObtener();
			return dispositivos;
		}

}

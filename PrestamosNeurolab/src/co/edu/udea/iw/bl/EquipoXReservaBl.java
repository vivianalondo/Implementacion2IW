package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dao.EquipoXReservaDAO;
import co.edu.udea.iw.dao.EstadoReservaDAO;
import co.edu.udea.iw.dao.ReservaDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.dto.EquipoXReservaId;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@Transactional
public class EquipoXReservaBl {

	private EquipoXReservaDAO equipoXReservaDAO;
	private ReservaDAO reservaDAO;
	private DispositivoDAO dispositivoDAO;
	private EstadoReservaDAO estadoReservaDAO;
	private UsuarioDAO usuarioDAO;
	
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public EquipoXReservaDAO getEquipoXReservaDAO() {
		return equipoXReservaDAO;
	}
	public void setEquipoXReservaDAO(EquipoXReservaDAO equipoXReservaDAO) {
		this.equipoXReservaDAO = equipoXReservaDAO;
	}
	public ReservaDAO getReservaDAO() {
		return reservaDAO;
	}
	public void setReservaDAO(ReservaDAO reservaDAO) {
		this.reservaDAO = reservaDAO;
	}
	public DispositivoDAO getDispositivoDAO() {
		return dispositivoDAO;
	}
	public void setDispositivoDAO(DispositivoDAO dispositivoDAO) {
		this.dispositivoDAO = dispositivoDAO;
	}
	public EstadoReservaDAO getEstadoReservaDAO() {
		return estadoReservaDAO;
	}
	public void setEstadoReservaDAO(EstadoReservaDAO estadoReservaDAO) {
		this.estadoReservaDAO = estadoReservaDAO;
	}
	
	/***
	 * 
	 * @return
	 * @throws MyException
	 */
	public List<EquipoXReserva> obtenerEquiposXReserva() throws MyException{
		return equipoXReservaDAO.listaObtener();
	}

	/**
	 * M�todo para registrar un usuario. Hacemos las validaciones de campos no nulos
	 * 
	 **/
	public void registrarEquipoXReserva(int reserva, int dispositivo, int estadoReserva, String loginCrea,String pwCrea)throws MyException{
		
		Reserva reservaIngresar = null;
		Dispositivo dispositivoIngresar = null;
		EstadoReserva estadoReservaIngresar = null;
		Usuario usuario=null;
		//Validar que los campos no sean nulos

		if(estadoReserva==0){
			throw new MyException("El estado de la Reserva no puede estar vac�o");
		}
		if(reserva==0){
			throw new MyException("La reserva no puede estar vac�a");
		}
		if(dispositivo==0){
			throw new MyException("El sispositivo no puede estar vac�a");
		}

		//Verificacion de que el usuario que est creando un nuevo registro sea administrador
		Usuario usuarioCrea = verificarLogin(loginCrea, pwCrea);
		if (usuarioCrea.getRol().getIdRol()!=1) {
			throw new MyException("No tiene permisos para ingresar un nuevo usuario");
		}
		
		EquipoXReserva equipoXReserva = null;
		EquipoXReservaId equipoXReservaId = null;
		Reserva registrarReserva = null;
		Dispositivo registrarDispositivo = null;
		EstadoReserva registrarEstadoReserva = null;
		
		equipoXReserva = new EquipoXReserva();
		equipoXReservaId = new EquipoXReservaId();
		registrarReserva = new Reserva();
		registrarDispositivo = new Dispositivo();
		registrarEstadoReserva = new EstadoReserva();
		
		registrarReserva.setIdReserva(reserva);
		registrarDispositivo.setIdDispositivo(dispositivo);
		registrarEstadoReserva.setIdEstadoReserva(estadoReserva);
		
		equipoXReservaId.setIdReserva(registrarReserva);
		equipoXReservaId.setIdDispositivo(registrarDispositivo);
		equipoXReservaId.setEstadoReserva(registrarEstadoReserva);

		equipoXReserva.setEquiposXReservaId(equipoXReservaId);
		
		equipoXReservaDAO.guardar(equipoXReserva);
		
	}
	
public void modificarBl(int reserva, int dispositivo, int estadoReserva, String loginCrea,String pwCrea) throws MyException{
		
		EstadoDispositivo estadoDispositivo = null;
		Reserva reservaIngresar = null;
		Dispositivo dispositivoIngresar = null;
		EstadoReserva estadoReservaIngresar = null;
		Usuario usuario=null;
		
		//Validar que los campos no sean nulos
		if(estadoReserva==0){
			throw new MyException("El estado de la Reserva no puede estar vac�o");
		}
		if(reserva==0){
			throw new MyException("La reserva no puede estar vac�a");
		}
		if(dispositivo==0){
			throw new MyException("El sispositivo no puede estar vac�a");
		}

		EquipoXReserva equipoXReserva = null;
		EquipoXReservaId equipoXReservaId = null;
		Reserva registrarReserva = null;
		Dispositivo registrarDispositivo = null;
		EstadoReserva registrarEstadoReserva = null;
		
		equipoXReserva = new EquipoXReserva();
		equipoXReservaId = new EquipoXReservaId();
		registrarReserva = new Reserva();
		registrarDispositivo = new Dispositivo();
		registrarEstadoReserva = new EstadoReserva();
		
		registrarReserva.setIdReserva(reserva);
		registrarDispositivo.setIdDispositivo(dispositivo);
		registrarEstadoReserva.setIdEstadoReserva(estadoReserva);
		
		equipoXReservaId.setIdReserva(registrarReserva);
		equipoXReservaId.setIdDispositivo(registrarDispositivo);
		equipoXReservaId.setEstadoReserva(registrarEstadoReserva);

		equipoXReserva.setEquiposXReservaId(equipoXReservaId);
		
		equipoXReservaDAO.modificar(equipoXReserva);

	}

public EquipoXReserva obtenerBl(EquipoXReservaId idEquipoXReserva) throws MyException{
	
	
	if(idEquipoXReserva==null|| "".equals(idEquipoXReserva)){
		throw new MyException("Tiene que ingresar el id de Equipo Por Reserva");
	}
	
	if(equipoXReservaDAO.obtener(idEquipoXReserva)!=null){
		return equipoXReservaDAO.obtener(idEquipoXReserva);
	}else{
		throw new MyException("El Id de Equipos por Reserva que busca, No existe");
	}
	
}
	
	
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
						throw new MyException("La contrase�a no es correcta");
					}
						
				}
			}catch (MyException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}
			
			return usuarioLogueado;
		}
		

}

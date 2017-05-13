package co.edu.udea.iw.bl;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoReservaDAO;
import co.edu.udea.iw.dao.ReservaDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase con la lógica del negocio de Reserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@Transactional
public class ReservaBl {
	
	private ReservaDAO reservaDAO;
	private EstadoReservaDAO estadoReservaDAO;
	private UsuarioDAO usuarioDAO;
	
	public ReservaDAO getReservaDAO() {
		return reservaDAO;
	}
	public void setReservaDAO(ReservaDAO reservaDAO) {
		this.reservaDAO = reservaDAO;
	}
	public EstadoReservaDAO getEstadoReservaDAO() {
		return estadoReservaDAO;
	}
	public void setEstadoReservaDAO(EstadoReservaDAO estadoReservaDAO) {
		this.estadoReservaDAO = estadoReservaDAO;
	}
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	/**
	 * Método para obtener la lista de las reservas
	 * @return reservas
	 * @throws MyException
	 */
	public List<Reserva> listaObtener() throws MyException{

		List<Reserva> reservas = new ArrayList();
		
		reservas = reservaDAO.listaObtener();
		return reservas;
	}
	
	
	/**
	 * Método que guarda una reserva. Valida los campos ingresados 
	 *no es necesario pedir el estado
	 *fecha de realizado es calcuada
	 * @param fechaReserva
	 * @param horaInicio
	 * @param horaFinal
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
 	*/
	public void guardar( String fechaReserva, String horaInicio, String horaFinal,String login,String psw)throws MyException{
		//Validar que los campos no sean nulo
		if(fechaReserva==null|| "".equals(fechaReserva)){
			throw new MyException("Debe ser igresada una fecha de reserva");
		}
			
		if(horaInicio==null|| "".equals(horaInicio)){
			throw new MyException("Debe ser ingerasada una hora inicial de la reserva");
		}
		
		if(horaFinal==null|| "".equals(horaFinal)){
			throw new MyException("Debe ser ingerasada una hora inicial de la reserva");
		}
		
		if(login==null|| "".equals(login)){
			throw new MyException("el campo usuario no puede estar vacio");
		}
		
		if(psw==null|| "".equals(psw)){
			throw new MyException("el campo contrasena no puede estar vacio");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		  Usuario usuarioCrea = verificarLogin(login, psw);
		  if (usuarioCrea.getRol().getIdRol()!=1) {
		   throw new MyException("No tiene permisos para ingresar un nuevo usuario");
		  }
		  
		Usuario  usuario = usuarioDAO.obtenerPorLogin(login);
		if(usuario == null){
			throw new MyException("usuario no existe");
		}
		
		EstadoReserva  estadoReserva = estadoReservaDAO.obtener(1);
		Date fechaRealizado = new Date();
		String horaRealizado= generarHora();
		Date fechaReservanueva = convertirFecha(fechaReserva);
		
		int horasinicio = Integer.parseInt(horaInicio.substring(0, 2));
		int minutosinicio = Integer.parseInt(horaInicio.substring(3, 5));
		
		int horasfinal = Integer.parseInt(horaFinal.substring(0, 2));
		int minutosfinal = Integer.parseInt(horaFinal.substring(3, 5));
		int horastotal = horasfinal-horasinicio;
		System.out.println(horasinicio);
		System.out.println(horasfinal);
		System.out.println(horastotal);
	
		
			if (((horastotal)==8)&&(minutosfinal>minutosinicio)||horastotal>8) {
				throw new MyException("Debe tene maximo 8 horas");
			}
		 
		
		
		Reserva reserva = new Reserva();
		reserva.setEstadoReserva(estadoReserva);
		reserva.setFechaRealizacion(fechaRealizado);
		reserva.setHoraRealizado(horaRealizado);
		reserva.setFechaReserva(fechaReservanueva);
		reserva.setHoraInicio(horaInicio);
		reserva.setHoraFinal(horaFinal);
		reserva.setUsuario(usuario);
		
		reservaDAO.guardar(reserva);
		
					
		
	}
	
	/**
	 * Método que modifica una reserva. Valida los campos ingresados
	 * @param idReserva
	 * @param fechaReserva
	 * @param horaInicio
	 * @param horaFinal
	 * @param fechaEntrega
	 * @param horaEntrega
	 * @param estadoreserva
	 * @param loginCrea
	 * @param pwCrea
	 * @throws MyException
 	*/
	public void modificar(int idReserva, String fechaReserva, String horaInicio, String horaFinal,
			String fechaEntrega,String horaEntrega,int estadoreserva, String login,String psw)throws MyException{
		//Validar que los campos no sean nulo
		
		
		if(idReserva==0){
			throw new MyException("Debe ser igresada un id de reserva");
		}
		
		
		if(fechaReserva==null|| "".equals(fechaReserva)){
			throw new MyException("Debe ser igresada una fecha de reserva");
		}
			
		if(horaInicio==null|| "".equals(horaInicio)){
			throw new MyException("Debe ser ingerasada una hora inicial de la reserva");
		}
		
		if(horaFinal==null|| "".equals(horaFinal)){
			throw new MyException("Debe ser ingerasada una hora inicial de la reserva");
		}
		
		if(login==null|| "".equals(login)){
			throw new MyException("el campo usuario no puede estar vacio");
		}
		
		if(psw==null|| "".equals(psw)){
			throw new MyException("el campo contrasena no puede estar vacio");
		}
		
		//Verificación de que el usuario que esté creando un nuevo registro sea administrador
		  Usuario usuarioCrea = verificarLogin(login, psw);
		  if (usuarioCrea.getRol().getIdRol()!=1) {
		   throw new MyException("No tiene permisos para ingresar un nuevo usuario");
		  }
		  
		Usuario  usuario = usuarioDAO.obtenerPorLogin(login);
		if(usuario == null){
			throw new MyException("usuario no existe");
		}
		
		
		Date fechaRealizado = new Date();
		String horaRealizado= generarHora();
		Date fechaReservanueva = convertirFecha(fechaReserva);
		
		int horasinicio = Integer.parseInt(horaInicio.substring(0, 2));
		int minutosinicio = Integer.parseInt(horaInicio.substring(3, 5));
		
		int horasfinal = Integer.parseInt(horaFinal.substring(0, 2));
		int minutosfinal = Integer.parseInt(horaFinal.substring(3, 5));
		int horastotal = horasfinal-horasinicio;
		
		EstadoReserva  estadodeReserva = estadoReservaDAO.obtener(estadoreserva);
		
		//calculo de multa
		int multa =0;
		if (estadodeReserva.getIdEstadoReserva()==3) {
			if (horaEntrega!=null&&!"".equals(horaEntrega)) {
				int horasEntrega = Integer.parseInt(horaEntrega.substring(0, 2));
				int minutosEntrega = Integer.parseInt(horaEntrega.substring(3, 5));
				
				if (horasEntrega>=horasfinal) {
					multa = multa+ 4*(horasEntrega-horasfinal);
					if (minutosfinal<minutosEntrega) {
						multa = multa + (minutosEntrega-minutosfinal)/15;
						System.out.println("Tiene " + multa + "días de sanción");
					}
					
				}
			}
		}
		
		usuario.setDiasSanciones(multa);
		usuarioDAO.modificar(usuario);
		
		System.out.println(horasinicio);
		System.out.println(horasfinal);
		System.out.println(horastotal);
	
		
			if (((horastotal)==8)&&(minutosfinal>minutosinicio)||horastotal>8) {
				throw new MyException("Debe tene maximo 8 horas");
			}
		 
		
		
		Reserva reserva = reservaDAO.obtener(idReserva);
		
		if (reserva==null) {
			throw new MyException("reserva no existe");
		}
	 
		reserva.setEstadoReserva(estadodeReserva);
		reserva.setFechaRealizacion(fechaRealizado);
		reserva.setHoraRealizado(horaRealizado);
		reserva.setFechaReserva(fechaReservanueva);
		reserva.setHoraInicio(horaInicio);
		reserva.setHoraFinal(horaFinal);
		reserva.setUsuario(usuario);
		reserva.setFechaEntrega(convertirFecha(fechaEntrega));
		reserva.setHoraEntrega(horaEntrega);
		
		reservaDAO.modificar(reserva);
		
	}
	
	/**
	 * Método que cancela una reserva automaticamente
	 * @param reserva
	 * @throws MyException
 	*/
	public void cancelacionautomatica(Reserva reserva) throws MyException {
		//se buscan las reservas del dia
		String hora = generarHora();
		int horasActual = Integer.parseInt(hora.substring(0, 2));
		int minutosActual = Integer.parseInt(hora.substring(3, 5));
		
		String horareserva = reserva.getHoraInicio();
		int horasReserva = Integer.parseInt(horareserva.substring(0, 2));
		int minutosReserva = Integer.parseInt(horareserva.substring(3, 5));
		
		if (horasActual>horasReserva||(horasActual>horasReserva&&(minutosActual-minutosReserva)>15)) {
			reserva.setEstadoReserva(estadoReservaDAO.obtener(4));
			reservaDAO.modificar(reserva);
		}
		
		
	}
	
	
	/**
	 * Método que convertir la fecha de string a date
	 * @param fecha
	 * @throws MyException
 	*/
	public Date convertirFecha(String fecha){
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	    Date fechaEnviar = null;
	    try {
	            fechaEnviar = formato.parse(fecha);        
	    }catch (ParseException ex) {
	            ex.printStackTrace();
	    }
	        return(fechaEnviar);
	 }
	
	/**
	 * Método que genera la hora
	 * @param reserva
	 * @throws MyException
 	*/
	public String generarHora(){
		Date hora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
	    String shora =formateador.format(hora);
	    return(shora);
	}
	
	/**
	 * Método para verificar que el usuario esté logueado
	 * @param loginC
	 * @param pwC
	 * @return Usuario
	 * @throws MyException
	 */
	public Usuario verificarLogin(String loginC, String pwC) throws MyException{
	 Usuario usuarioLogueado = null;
	 
	 try{
	  usuarioLogueado = usuarioDAO.obtenerPorLogin(loginC);
	  if (usuarioLogueado==null) {
	   throw new MyException("Usuario no existe en el sistema");
	  }else{
	   System.out.println("El password es: "+usuarioLogueado.getPassword());
	   System.out.println("El password ingresado es: "+pwC);
	   
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

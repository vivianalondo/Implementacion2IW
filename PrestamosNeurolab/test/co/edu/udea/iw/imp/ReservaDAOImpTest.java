package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.ReservaDAO;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class ReservaDAOImpTest {

	@Autowired //Para indicar la inyeccion
	private ReservaDAO reservaDAO;
	
	@Test
	public void testObtener() {
		Reserva reserva = null;
		try
		{
			reserva = reservaDAO.obtener(2);
			System.out.print("Increible pero da: "+reserva.getIdReserva());
			System.out.print("Increible pero da: "+reserva.getUsuario());
			System.out.print("Increible pero da: "+reserva.getHoraInicio());
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListaObtener() {
		List<Reserva> resultado = null;
		
		try{
			resultado = reservaDAO.listaObtener();
			for(Reserva reserva:resultado){
			    System.out.println("El id es: "+reserva.getIdReserva());
			    System.out.println("La hora de su reserva es: "+reserva.horaInicio);
			    System.out.println("La fecha de su reserva es: "+reserva.fechaReserva);
			    System.out.println("La fecha en la que hizo la reserva es: "+reserva.fechaRealizacion);
			    System.out.println("La hora en la que hizo la reserva es: "+reserva.horaRealizado);
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testGuardar() {
		Reserva reserva = null;
		EstadoReserva estadoReserva = null;
		Usuario usuario = null;
		
		try{
			reserva = new Reserva();
			estadoReserva = new EstadoReserva();
			usuario = new Usuario();
		   
			reserva.setFechaRealizacion(new Date());
			reserva.setHoraInicio("06:00");
			reserva.setHoraFinal("06:00");
			
	        String horaG = generarHora();
			reserva.setHoraRealizado(horaG);
			
			Date fecha = convertirFecha("2017-05-04");
			reserva.setFechaReserva(fecha);
			
			estadoReserva.setIdEstadoReserva(1);
			reserva.setEstadoReserva(estadoReserva);
			
			usuario.setIdentificacion("111");
			reserva.setUsuario(usuario);
			
			reservaDAO.guardar(reserva);
			   
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}
	
	
	@Test
	public void testModificar() {
		Reserva reserva = null;
		EstadoReserva estadoReserva = null;
		Usuario usuario = null;
		
		try{
			reserva = new Reserva();
			estadoReserva = new EstadoReserva();
			usuario = new Usuario();
		   
			reserva.setIdReserva(2);
			reserva.setFechaRealizacion(new Date());
			reserva.setHoraInicio("05:00");
			reserva.setHoraFinal("06:00");
			
			String horaG = generarHora();
			reserva.setHoraRealizado(horaG);
			
			Date fecha = convertirFecha("2017-05-10");
			reserva.setFechaReserva(fecha);
			
			estadoReserva.setIdEstadoReserva(1);
			reserva.setEstadoReserva(estadoReserva);
						
			usuario.setIdentificacion("222");
			reserva.setUsuario(usuario);
			
			reservaDAO.modificar(reserva);
			   
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}
	
	//Método para convertir fecha
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
	
	//Método para generar hora
	public String generarHora(){
		Date hora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        String shora =formateador.format(hora);
        return(shora);
	}

}

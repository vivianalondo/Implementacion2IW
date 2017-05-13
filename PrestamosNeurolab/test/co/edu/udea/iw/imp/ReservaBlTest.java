package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.ReservaBl;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para hacer la pruebas de los metodos de ReservaBl
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class ReservaBlTest {
	
	@Autowired
	private ReservaBl reservaBl;

	/**
	 * Test para crear un usuario
	 * @throws MyException
	 */
	//@Test
	public void testCrearReserva() {
		
		try{	
			reservaBl.guardar("2017-05-25", "06:06:00", "06:08:00","sanvilc","12345");			
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Se guardó la reserva correctamente ");
	}
	
	/**
	 * Test para modificar una reserva
	 * @throws MyException
	 */
	@Test
	public void testModificarReserva() {
		
		try{	
			reservaBl.modificar(2,"2017-05-25", "06:06:00", "06:08:00","2017-05-25", "07:06:00",2,"sanvilc","12345");			
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Se modificó la reserva correctamente ");
	}
	
	
	@Test
		public void testListarReservas() {
			List<Reserva> resultado = null;
			try{
				
				
				resultado = reservaBl.listaObtener();
				for(Reserva reserva:resultado){
				    System.out.println("Código reserva: "+reserva.getIdReserva());
				    System.out.println("Fecha de reserva: "+reserva.getFechaReserva());
				}
				
			}catch(MyException e){
				e.printStackTrace();
				fail(e.getMessage());
			}
	}

}

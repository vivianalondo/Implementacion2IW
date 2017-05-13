package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.EstadoReservaBl;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoReservaBlTest {

	@Autowired //Para decirle que lo inyecte -- Inicializa la variables
	private EstadoReservaBl estadoReservaBl;
    
	@Test
	public void testGuardar() {

		try{
			estadoReservaBl.guardar("Cancelada","sanvilc", "12345");
			System.out.println("Se ha guardado un nuevo estado");
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Las reservas existentes son:");
		testListaObtener();
	}
    
    
   
    @Test
	public void testObtener() {
		EstadoReserva estadoReserva = null;
		try
		{
		estadoReserva = estadoReservaBl.obtener(2);
		System.out.print("La reserva es: "+ estadoReserva.getTipoEstadoReserva());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testListaObtener() {
		List<EstadoReserva> resultado = null;
		
		try{
			resultado = estadoReservaBl.listaObtener();
			for(EstadoReserva estadoReserva:resultado){
			    System.out.println(estadoReserva.getTipoEstadoReserva());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}




	@Test
	public void testModificar() {
	
		try{
			estadoReservaBl.modificar(2, "Aprovada","sanvilc","12345");
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Las reservas existentes son:");
		testListaObtener();
	}

}

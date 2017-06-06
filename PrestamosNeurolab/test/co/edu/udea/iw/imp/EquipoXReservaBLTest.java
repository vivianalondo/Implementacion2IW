package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.EquipoXReservaBl;
import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotaciï¿½n para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotaciï¿½n para decirle a spring donde estï¿½ el archivo de configuraciï¿½n de spring y cargarlo al inicio
public class EquipoXReservaBLTest {

	@Autowired
	private EquipoXReservaBl equipoXReservaBl;

	//@Test
	public void testObtenerEquiposXReserva() {
		
		try{
			
			List<EquipoXReserva> resultado = null;
			resultado = equipoXReservaBl.obtenerEquiposXReserva();
			for(EquipoXReserva equipoXReserva:resultado){
			    System.out.println("Reserva número: "+equipoXReserva.getEquiposXReservaId().getIdReserva().getIdReserva());
			    System.out.println("Dispositivo: "+equipoXReserva.getEquiposXReservaId().getIdDispositivo().getNombre());
			    System.out.println("Estado de la reserva: "+equipoXReserva.getEstadoReserva().getTipoEstadoReserva());
			}
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	/**
	 * Test registrar nuevo dispositivo
	 */
	@Test
	public void testRegistrarEquipoXReserva() {
		try{	
			equipoXReservaBl.registrarEquipoXReserva(14, 7, 1);
			System.out.println("Se ha guardado el registro");		
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	//@Test
	public void testModificarBl() {
		fail("Not yet implemented");
	}

	//@Test
	public void testObtenerBl() {
		fail("Not yet implemented");
	}

}

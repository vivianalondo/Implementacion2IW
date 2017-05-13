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
@Transactional //Anotaci�n para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotaci�n para decirle a spring donde est� el archivo de configuraci�n de spring y cargarlo al inicio
public class EquipoXReservaBLTest {

	@Autowired
	private EquipoXReservaBl equipoXReservaBl;

	@Test
	public void testObtenerEquiposXReserva() {
		
		try{
			
			
			List<EquipoXReserva> resultado = null;
			resultado = equipoXReservaBl.obtenerEquiposXReserva();
			for(EquipoXReserva equipoXReserva:resultado){
			    System.out.println(equipoXReserva.getEquiposXReservaId());
			}
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testRegistrarEquipoXReserva() {
		fail("Not yet implemented");
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

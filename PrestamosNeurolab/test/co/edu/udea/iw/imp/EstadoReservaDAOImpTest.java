package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoReservaDAO;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotacion para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoReservaDAOImpTest {
	
	@Autowired //Para indicar la inyeccion
	private EstadoReservaDAO estadoReservaDAO;

	@Test
	public void testObtener() {
		
		EstadoReserva estadoReserva = null;
	
		try
		{
			estadoReserva = estadoReservaDAO.obtener(2);
		    System.out.print("Increible pero da:"+estadoReserva.getTipoEstadoReserva());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testListaObtener() {

		List<EstadoReserva> resultado = null;
		
		try{
			resultado = estadoReservaDAO.listaObtener();
			for(EstadoReserva estadoReserva:resultado){
			    System.out.println(estadoReserva.getTipoEstadoReserva());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}

	@Test
	public void testGuardar() {
		EstadoReserva estadoReserva = null;
		
		try{
			estadoReserva = new EstadoReserva();
			estadoReserva.setIdEstadoReserva(3);
			estadoReserva.setTipoEstadoReserva("Es de Lopera");
			estadoReservaDAO.guardar(estadoReserva);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();

	}

	@Test
	public void testModificar() {

		EstadoReserva estadoReserva = null;
		
		try{
			
			estadoReserva = estadoReservaDAO.obtener(2);
			estadoReserva.setTipoEstadoReserva("Cancelado");
			estadoReservaDAO.modificar(estadoReserva);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}

}

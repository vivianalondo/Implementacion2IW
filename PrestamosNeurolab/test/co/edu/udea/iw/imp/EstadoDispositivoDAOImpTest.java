package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoDispositivoDAO;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoDispositivoDAOImpTest {
	
	@Autowired //Para indicar la inyeccion
	private EstadoDispositivoDAO estadoDispositivoDAO;

	@Test
	public void testObtener() {
		EstadoDispositivo estadoDispositivo = null;
		try
		{
			estadoDispositivo = estadoDispositivoDAO.obtener(2);
		System.out.print("Increible pero da:"+estadoDispositivo.getTipoEstadoDispositivo());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testListaObtener() {
		List<EstadoDispositivo> resultado = null;
		
		try{
			resultado = estadoDispositivoDAO.listaObtener();
			for(EstadoDispositivo estadoDispositivo:resultado){
			    System.out.println(estadoDispositivo);
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}

	@Test
	public void testGuardar() {
		EstadoDispositivo estadoDispositivo = null;
		
		try{
			estadoDispositivo = new EstadoDispositivo();
			estadoDispositivo.setIdEstadoDispositivo(3);
			estadoDispositivo.setTipoEstadoDispositivo("Dado de baja");
			estadoDispositivoDAO.guardar(estadoDispositivo);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}

	@Test
	public void testModificar() {
		EstadoDispositivo estadoDispositivo = null;
		
		try{
			
			estadoDispositivo = estadoDispositivoDAO.obtener(2);
			estadoDispositivo.setTipoEstadoDispositivo("Malo");
			estadoDispositivoDAO.modificar(estadoDispositivo);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}

}

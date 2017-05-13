package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class DispositivoDAOImpTest {
	
	@Autowired //Para indicar la inyeccion
	private DispositivoDAO dispositivoDAO;

	@Test
	public void testObtener() {
		Dispositivo dispositivo = null;
		try
		{
			dispositivo = dispositivoDAO.obtener(2);
			System.out.print("Increible pero da: "+dispositivo.getNombre());
			System.out.println("Descripcion: "+dispositivo.getDescripcion());
			System.out.println("Estado dispositivo: "+dispositivo.getEstadoDispositivo());
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListaObtener() {
		List<Dispositivo> resultado = null;
		
		try{
			resultado = dispositivoDAO.listaObtener();
			for(Dispositivo dispositivo:resultado){
			    System.out.println(dispositivo.getNombre());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGuardar() {
		Dispositivo dispositivo = null;
		EstadoDispositivo estadoDispositivo= null;
		
		try{
			dispositivo = new Dispositivo();
			dispositivo.setNombre("Computadora");
			dispositivo.setDescripcion("Computador de escritorio");

			
			estadoDispositivo = new EstadoDispositivo();
			estadoDispositivo.setIdEstadoDispositivo(1);
			dispositivo.setEstadoDispositivo(estadoDispositivo);
			dispositivoDAO.guardar(dispositivo);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}
	
	@Test
	public void testModificar() {
		Dispositivo dispositivo = null;
		EstadoDispositivo estadoDispositivo= null;
		
		try{
			dispositivo = dispositivoDAO.obtener(2);
			dispositivo.setDescripcion("Nueva computadora");
			
			estadoDispositivo = new EstadoDispositivo();
			estadoDispositivo.setIdEstadoDispositivo(1);
			dispositivo.setEstadoDispositivo(estadoDispositivo);
			dispositivoDAO.modificar(dispositivo);

		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testObtener();
	}

}

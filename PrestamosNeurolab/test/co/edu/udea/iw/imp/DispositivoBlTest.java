package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.DispositivoBl;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para hacer la pruebas de los metodos de DispositivoBl
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class DispositivoBlTest {

	@Autowired
	private DispositivoBl dispositivoBl;
	
	/**
	 * Test para crear un dispositivo
	 * @throws MyException
	 */
	@Test
	public void testCrearDispositivo() {
		
		try{
			
			dispositivoBl.guardar("otro tubo de ensayo", 1, "no se como se escribe completo","sanvilc","12345");			
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test para eliminar un dispositivo
	 * @throws MyException
	 */
	@Test
	public void testEliminarDispositivo() {
		try{
			
			dispositivoBl.eliminar(1);
			System.out.println(dispositivoBl.getDispositivoDao().obtener(1).getEstadoDispositivo().getTipoEstadoDispositivo());
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test para modificar un dispositivo
	 * @throws MyException
	 */
	@Test
	public void testModificarDispositivo() {
		
		try{
			dispositivoBl.modificar(1,"otro tubo de ensayo", 1, "no se como se escribe completo","sanvilc","12345");			
			testBuscarDispositivoPorNombre();
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test para buscar un dispositivo por el nombre
	 * @throws MyException
	 */
	@Test
	public void testBuscarDispositivoPorNombre() {
		List<Dispositivo> resultado = null;
		try{
			
			
			resultado = dispositivoBl.listaObtenerPorNombre("computador");
			for(Dispositivo dispositivo:resultado){
			    System.out.println(dispositivo.getNombre());
			}
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		
		
	}
	
	/**
	 * Test para buscar un dispositivo por el nombre
	 * @throws MyException
	 */
	@Test
	public void testListarInventario() {
		List<Dispositivo> resultado = null;
		try{
			
			
			resultado = dispositivoBl.listaObtener();
			for(Dispositivo dispositivo:resultado){
			    System.out.println(dispositivo.getNombre());
			}
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	
	
	
}

}

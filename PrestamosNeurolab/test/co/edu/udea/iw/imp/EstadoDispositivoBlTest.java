package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.EstadoDispositivoBl;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoDispositivoBlTest {

	@Autowired //Para decirle que lo inyecte -- Inicializa la variables
	private EstadoDispositivoBl estadoDispositivoBl;
    
	@Test
	public void testGuardar() {

		try{
			estadoDispositivoBl.guardar("Dado de baja","sanvilc", "12345");
			System.out.println("Se ha guardado un nuevo estado");
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
    
    
   
    @Test
	public void testObtener() {
		EstadoDispositivo estadoDispositivo = null;
		try
		{
		estadoDispositivo = estadoDispositivoBl.obtener(1);
		System.out.print("Increible pero da: "+ estadoDispositivo.getTipoEstadoDispositivo());	
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
			resultado = estadoDispositivoBl.listaObtener();
			for(EstadoDispositivo estadoDispositivo:resultado){
			    System.out.println(estadoDispositivo.getTipoEstadoDispositivo());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}




	@Test
	public void testModificar() {
	
		try{
			estadoDispositivoBl.modificar(1, "Malo","sanvilc","12345");
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}

}

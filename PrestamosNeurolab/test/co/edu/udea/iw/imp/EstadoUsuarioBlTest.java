package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.EstadoUsuarioBl;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoUsuarioBlTest {

	@Autowired //Para decirle que lo inyecte -- Inicializa la variables
	private EstadoUsuarioBl estadoUsuarioBl;
    
	@Test
	public void testGuardar() {

		try{
			estadoUsuarioBl.guardar("Inhabilitado","sanvilc", "12345");
			System.out.println("Se ha guardado un nuevo estado");
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Los estados existentes son:");
		testListaObtener();
	}
    
    
   
    @Test
	public void testObtener() {
		EstadoUsuario estadoUsuario = null;
		try
		{
		estadoUsuario = estadoUsuarioBl.obtener(2);
		System.out.print("El estado es: "+ estadoUsuario.getTipoEstado());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testListaObtener() {
		List<EstadoUsuario> resultado = null;
		
		try{
			resultado = estadoUsuarioBl.listaObtener();
			for(EstadoUsuario estadoUsuario:resultado){
			    System.out.println(estadoUsuario.getTipoEstado());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}




	@Test
	public void testModificar() {
	
		try{
			estadoUsuarioBl.modificar(1, "Pendiente","sanvilc","12345");
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Los estados existentes son:");
		testListaObtener();
	}

}

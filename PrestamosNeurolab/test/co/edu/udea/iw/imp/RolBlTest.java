package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.RolBl;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class RolBlTest {

	@Autowired //Para decirle que lo inyecte -- Inicializa la variables
	private RolBl rolBl;
    
	@Test
	public void testGuardar() {

		try{
			rolBl.guardar("NuevoRol","sanvilc", "12345");
			System.out.println("Se ha guardado un nuevo rol");
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Los roles existentes son: ");
		testListaObtener();
	}
    
    
   
	@Test
	public void testObtener() {
		Rol rol = null;
		try
		{
		rol = rolBl.obtener(2);
		System.out.print("El rol es: "+ rol.getTipoRol());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testListaObtener() {
		List<Rol> resultado = null;
		
		try{
			resultado = rolBl.listaObtener();
			for(Rol rol:resultado){
			    System.out.println(rol.getTipoRol());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}


	@Test
	public void testModificar() {
	
		try{
			rolBl.modificar(1, "Docente","sanvilc","12345");
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("Los roles existentes son: ");
		testListaObtener();
	}

}
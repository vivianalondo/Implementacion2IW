package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.bl.UsuarioBl;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase para hacer la pruebas de los metodos de UsuarioBl
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class UsuarioBlTest {
	
	@Autowired //Para decirle que lo inyecte -- Inicializa la variables(Spring lo hace)
	private UsuarioBl usuarioBl;
	
	/**
	 * Test para registrar un usuario
	 * @throws MyException
	 */
	@Test
	public void testRegistrarUsuario() {
		try {
			usuarioBl.registrarUsuario("3333", "Cedula", "Sandra Viviana", "Londoño", "333355", "prueba1@prueba1.com", "sanvil", "sanvilp", 1, 2);
			System.out.println("Se ha registrado correctamente el usuario");
		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test para obtener una lista con todos los usuarios
	 * @throws MyException
	 */
	@Test
	public void testObtenerUsuarios(){
		List<Usuario> resultado = null;
		
		try{
			resultado = usuarioBl.obtenerUsuarios();
			for(Usuario usuario:resultado){
			    System.out.println(usuario.getNombre());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test para modificar un usuario
	 * @throws MyException
	 */
	@Test
	public void testModificarUsuario() {
		try {
			usuarioBl.modificarUsuario("3333", "Cedula", "Oscar", "Lopera", "333355", "prueba1@prueba1.com", "sanvil", "sanvilp", 1, 2);
			System.out.println("Se ha modificado correctamente el usuario");
		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}

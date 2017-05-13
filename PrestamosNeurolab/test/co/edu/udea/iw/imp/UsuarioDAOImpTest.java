package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class UsuarioDAOImpTest {
	
	@Autowired //Para indicar la inyeccion
	private UsuarioDAO usuarioDAO;

	//@Test
	public void testObtener() {
		Usuario usuario = null;
		try
		{
			usuario = usuarioDAO.obtener("111");
			System.out.print("Increible pero da: "+usuario.getNombre());
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testListaObtener() {
		List<Usuario> resultado = null;
		
		try{
			resultado = usuarioDAO.listaObtener();
			for(Usuario usuario:resultado){
			    System.out.println(usuario.getNombre());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}
	
	//@Test
	public void testGuardar() {
		Usuario usuario = null;
		EstadoUsuario estadoUsuario = null;
		Rol rol = null;
		
		try{
			usuario = new Usuario();
			estadoUsuario = new EstadoUsuario();
			rol = new Rol();
		   
			usuario.setIdentificacion("1038115559");
			usuario.setTipoDocumento("CC");
			usuario.setNombre("Yeiffer");
			usuario.setApellido("Herrera");
			usuario.setTelefono("3187215055");
			usuario.setEmail("yeiferhh@gmail.com");
			usuario.setLogin("yeifer");
			usuario.setPassword("12345");
			usuario.setDiasSanciones(0);
			   
			estadoUsuario.setIdEstadoUsuario(1);
			usuario.setEstadoUsuario(estadoUsuario);
			
			rol.setIdRol(2);
			usuario.setRol(rol);
			  
			usuarioDAO.guardar(usuario);
			   
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}
	
	
	//@Test
		public void testModificar() {
			Usuario usuario = null;
			EstadoUsuario estadoUsuario = null;
			Rol rol = null;
			
			try{
				usuario = new Usuario();
				estadoUsuario = new EstadoUsuario();
				rol = new Rol();
				
				usuario.setIdentificacion("111");
				usuario.setTipoDocumento("CC");
				usuario.setNombre("Alexander");
				usuario.setApellido("Herrera");
				usuario.setTelefono("3187215055");
				usuario.setEmail("yeiferhh@gmail.com");
				usuario.setLogin("yeifer");
				usuario.setPassword("12345");
				usuario.setDiasSanciones(0);
				
				estadoUsuario.setIdEstadoUsuario(1);
				usuario.setEstadoUsuario(estadoUsuario);
				
				rol.setIdRol(2);
				usuario.setRol(rol);
				
				usuarioDAO.modificar(usuario);
				
			}catch(MyException e){
				e.printStackTrace();
				fail(e.getMessage());
			}
			testListaObtener();
		}
		
		@Test
		public void testObtenerPorLogin() {
			Usuario usuario = null;
			try
			{
				usuario = usuarioDAO.obtenerPorLogin("sanvilc");
				System.out.print("Increible pero da: "+usuario.getNombre());
				System.out.print("Increible pero da: "+usuario.getPassword());
			}
			catch(MyException e)
			{
				e.printStackTrace();
				fail(e.getMessage());
			}
		}

}

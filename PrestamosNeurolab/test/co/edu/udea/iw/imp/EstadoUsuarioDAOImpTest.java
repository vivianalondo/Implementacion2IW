package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EstadoUsuarioDAO;
import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EstadoUsuarioDAOImpTest {

	@Autowired //Para indicar la inyeccion
	private EstadoUsuarioDAO estadoUsuarioDAO;

	@Test
	public void testObtener() {
		
		EstadoUsuario estadoUsuario = null;
		
		try
		{
			estadoUsuario = estadoUsuarioDAO.obtener(2);
		    System.out.print("Increible pero da:"+estadoUsuario.getTipoEstado());	
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
			resultado = estadoUsuarioDAO.listaObtener();
			for(EstadoUsuario estadoUsuario:resultado){
			    System.out.println(estadoUsuario.getTipoEstado());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testGuardar() {
      EstadoUsuario estadoUsuario = null;
		
		try{
			estadoUsuario = new EstadoUsuario();
			estadoUsuario.setIdEstadoUsuario(5);
			estadoUsuario.setTipoEstado("Con Sueño");
			estadoUsuarioDAO.guardar(estadoUsuario);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}

	@Test
	public void testModificar() {
		EstadoUsuario estadoUsuario = null;
		
		try{
			
			estadoUsuario = estadoUsuarioDAO.obtener(2);
			estadoUsuario.setTipoEstado("Repaila Hermano");
			estadoUsuarioDAO.modificar(estadoUsuario);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
}

}

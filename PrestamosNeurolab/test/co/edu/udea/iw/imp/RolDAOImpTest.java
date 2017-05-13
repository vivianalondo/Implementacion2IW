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

import co.edu.udea.iw.exception.MyException;
import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.Rol;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class RolDAOImpTest {

	
	@Autowired //Para indicar la inyeccion
	private RolDAO rolDAO;
	
	//@Test
	public void testObtener() {
		
		Rol rol = null;
		try
		{
			rol = rolDAO.obtener(2);
		System.out.print("Increible pero da:"+rol.getTipoRol());	
		}
		catch(MyException e)
		{
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	public void testGuardar() {
		Rol rol = null;
		
		try{
			rol = new Rol();
			//rol.setIdRol(3);
			rol.setTipoRol("Docente");
			rolDAO.guardar(rol);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}
	
	//@Test
	public void testListaObtener() {
		List<Rol> resultado = null;
		
		try{
			resultado = rolDAO.listaObtener();
			for(Rol rol:resultado){
			    System.out.println(rol.getTipoRol());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			fail(e.getMessage());
		}
	}

	//@Test
	public void testModificar() {
		Rol rol = null;
		
		try{
			
			rol = rolDAO.obtener(2);
			rol.setTipoRol("Alumno");
			rolDAO.modificar(rol);
			
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
		testListaObtener();
	}


}




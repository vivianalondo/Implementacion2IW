package co.edu.udea.iw.imp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.EquipoXReservaDAO;
import co.edu.udea.iw.dao.ReservaDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.dto.EquipoXReservaId;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.exception.MyException;

@RunWith(SpringJUnit4ClassRunner.class)//Anotacion para correr la prueba con otro runner
@Transactional //Anotación para indicar que la clase es de tipo transaccional 
@ContextConfiguration(locations="classpath:SpringConfiguration.xml") //Anotación para decirle a spring donde está el archivo de configuración de spring y cargarlo al inicio
public class EquipoXReservaDAOImpTest {
	
	@Autowired //Para indicar la inyeccion
	private EquipoXReservaDAO equipoXReservaDAO;

	//@Test
	public void testObtener() {
		
		EquipoXReserva equipoXReserva = null;
		
		Reserva reserva = null;
		Dispositivo dispositivo = null;
		EstadoReserva estadoReserva = null;
		EquipoXReservaId equipoXReservaId = null;
		
		try{
			reserva = new Reserva();
			dispositivo = new Dispositivo();
			estadoReserva = new EstadoReserva();
			equipoXReservaId = new EquipoXReservaId();
			equipoXReserva = new EquipoXReserva();
			
			reserva.setIdReserva(2);
			dispositivo.setIdDispositivo(1);
			estadoReserva.setIdEstadoReserva(1);
		
			equipoXReservaId.setIdReserva(reserva);
			equipoXReservaId.setIdDispositivo(dispositivo);
			equipoXReservaId.setEstadoReserva(estadoReserva);
			
			equipoXReserva = equipoXReservaDAO.obtener(equipoXReservaId);
			
			equipoXReservaId = equipoXReserva.getEquiposXReservaId();
			reserva = equipoXReservaId.getIdReserva();
			dispositivo = equipoXReservaId.getIdDispositivo();
			
			System.out.println("Este es tu número de reserva: "+reserva.getIdReserva());
			System.out.println("Este es el dispositivo que prestaste: "+dispositivo.getNombre());
			
			//System.out.print("Increible pero da:"+ equipoXReserva.getEquiposXReservaId().getEstadoReserva().getTipoEstadoReserva());	
		}catch(MyException e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	@Test
	public void testListaObtener() {
		
		List<EquipoXReserva> resultado = null;
		
		try{
			resultado = equipoXReservaDAO.listaObtener();
			for(EquipoXReserva equipoXReserva:resultado){
				System.out.println("El id de la reserva es: "+equipoXReserva.getEquiposXReservaId().getIdReserva().getIdReserva());
				System.out.println("El id del dispositivo es: "+equipoXReserva.getEquiposXReservaId().getIdDispositivo().getIdDispositivo());
				System.out.println("El estado de la reserva es: "+equipoXReserva.getEquiposXReservaId().getEstadoReserva().getTipoEstadoReserva());
			}
			assertTrue(resultado.size()>0);
		}catch(MyException e){
			System.out.println("Pasó por donde yeifer dijo que iba a pasar");
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	//@Test
	public void testGuardar(){
		
		Reserva reserva = null;
		Dispositivo dispositivo = null;
		EstadoReserva estadoReserva = null;
		EquipoXReservaId equipoXReservaId = null;
		EquipoXReserva equipoXReserva = null;
		
		try{
			reserva = new Reserva();
			dispositivo = new Dispositivo();
			estadoReserva = new EstadoReserva();
			equipoXReservaId = new EquipoXReservaId();
			equipoXReserva = new EquipoXReserva();
			
			reserva.setIdReserva(2);
			dispositivo.setIdDispositivo(1);
			estadoReserva.setIdEstadoReserva(2);
		
			equipoXReservaId.setIdReserva(reserva);
			equipoXReservaId.setIdDispositivo(dispositivo);
			equipoXReservaId.setEstadoReserva(estadoReserva);
			
			equipoXReserva.setEquiposXReservaId(equipoXReservaId);
		
			equipoXReservaDAO.guardar(equipoXReserva);
			}catch(MyException e){
				e.printStackTrace();
				fail(e.getMessage());
			}
			testListaObtener();

		}

	
	//@Test
		public void testModificar(){
			
			Reserva reserva = null;
			Dispositivo dispositivo = null;
			EstadoReserva estadoReserva = null;
			EquipoXReservaId equipoXReservaId = null;
			EquipoXReserva equipoXReserva = null;
			
			try{
				reserva = new Reserva();
				dispositivo = new Dispositivo();
				estadoReserva = new EstadoReserva();
				equipoXReservaId = new EquipoXReservaId();
				equipoXReserva = new EquipoXReserva();
				
				reserva.setIdReserva(2);
				dispositivo.setIdDispositivo(2);
				estadoReserva.setIdEstadoReserva(2);
			
				equipoXReservaId.setIdReserva(reserva);
				equipoXReservaId.setIdDispositivo(dispositivo);
				equipoXReservaId.setEstadoReserva(estadoReserva);
				
				equipoXReserva.setEquiposXReservaId(equipoXReservaId);
			
				equipoXReservaDAO.modificar(equipoXReserva);
				}catch(MyException e){
					e.printStackTrace();
					fail(e.getMessage());
				}
				//testListaObtener();

			}
}

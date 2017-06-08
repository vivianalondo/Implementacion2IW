package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.EquipoXReservaDAO;
import co.edu.udea.iw.dto.EquipoXReserva;
import co.edu.udea.iw.dto.EquipoXReservaId;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de EquipoXReserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class EquipoXReservaDAOImplement implements EquipoXReservaDAO {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Implementación del método que obtiene un equipoxreserva a partir de un id ingresado.
	 * @param equiposxReservaId
	 * @return EquipoxReserva
	 * @throws MyException
	 */
	@Override
	public EquipoXReserva obtener(EquipoXReservaId equiposXReservaId) throws MyException {
		EquipoXReserva equipoXReserva = new EquipoXReserva();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			equipoXReserva = (EquipoXReserva) session.get(EquipoXReserva.class, equiposXReservaId);
			System.out.println(equipoXReserva.getEstadoReserva().getTipoEstadoReserva());
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el estado del dispositivo", e);
		}
		return equipoXReserva;
	}

	/**
	 * Implementación del método que entrega una lista con todos los EquipoxReserva en la BD
	 * @param 
	 * @return List<EquipoxReserva>
	 * @throws MyException
	 */
	@Override
	public List<EquipoXReserva> listaObtener() throws MyException {
		List<EquipoXReserva> equiposXReserva = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EquipoXReserva.class);
			equiposXReserva = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los estados de los dispositivos", e);
			}
		return equiposXReserva;
	}

	/**
	 * Implementación del método que permite guardar un equipoxreserva.
	 * @param equipoxreserva
	 * @throws MyException
	 */
	@Override
	public void guardar(EquipoXReserva equipoXReserva) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.save(equipoXReserva);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error guardando el dispositivoxreserva", e);
		}
	}

	/**
	 * Implementación del método que permite modificar un equipoxreserva.
	 * @param equipoxreserva
	 * @throws MyException
	 */
	@Override
	public void modificar(EquipoXReserva equipoXReserva) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(equipoXReserva);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Rol", e);
		}
	}

	/**
	 * Implementación del método que obtiene un equipoxreserva a partir de un id de reserva ingresado.
	 * @param ReservaId
	 * @return EquipoxReserva
	 * @throws MyException
	 */
	@Override
	public List<EquipoXReserva> obtenerPorReserva(int ReservaId) throws MyException {
		List<EquipoXReserva> equiposXReserva = new ArrayList();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EquipoXReserva.class).add(Restrictions.like("equiposXReservaId.idReserva.idReserva", ReservaId));
			equiposXReserva = criteria.list();
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando la lista de esquipos asignados a la reserva", e);
		}
		return equiposXReserva;
	}

	@Override
	public List<EquipoXReserva> obtenerPorDispositivo(int dispositivoId) throws MyException {
		List<EquipoXReserva> equiposXReserva = new ArrayList();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EquipoXReserva.class).add(Restrictions.like("equiposXReservaId.idDispositivo.idDispositivo", dispositivoId));
			equiposXReserva = criteria.list();
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando la lista de esquipos asignados a la reserva", e);
		}
		return equiposXReserva;
	}



}

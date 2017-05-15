package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.EstadoReservaDAO;
import co.edu.udea.iw.dto.EstadoReserva;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de EstadoReservaDAO
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class EstadoReservaDAOImplement implements EstadoReservaDAO {
	
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Implementación del método que obtiene un estado de Reserva a partir de un id ingresado.
	 * @param idEstadoReserva
	 * @return EstadoReserva
	 * @throws MyException
	 */
	@Override
	public EstadoReserva obtener(int idEstadoReservaDAO) throws MyException {
		EstadoReserva estadoReserva = new EstadoReserva();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			estadoReserva = (EstadoReserva) session.get(EstadoReserva.class, idEstadoReservaDAO);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el Estado de Reservas", e);
		}
		return estadoReserva;
	}

	/**
	 * Implementación del método que entrega una lista con todos los estados de reserva en la BD
	 * @param 
	 * @return List<EstadoReserva>
	 * @throws MyException
	 */
	@Override
	public List<EstadoReserva> listaObtener() throws MyException {
		List<EstadoReserva> estadoReservas = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EstadoReserva.class);
			estadoReservas = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los Estados de Reservas", e);
			}
		return estadoReservas;
	}

	/**
	 * Implementación del método que permite guardar un estado de reserva.
	 * @param estadoReserva
	 * @throws MyException
	 */
	@Override
	public void guardar(EstadoReserva estadoReserva) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.save(estadoReserva);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error guardando el Estado de Reserva", e);
		}

	}

	/**
	 * Implementación del método que permite modificar un estado de reserva.
	 * @param estadoReserva
	 * @throws MyException
	 */
	@Override
	public void modificar(EstadoReserva estadoReserva) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(estadoReserva);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Estado de Reserva", e);
		}

	}

}

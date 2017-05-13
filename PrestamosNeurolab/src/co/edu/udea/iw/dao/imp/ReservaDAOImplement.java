package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.ReservaDAO;
import co.edu.udea.iw.dto.Reserva;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de ReserdaDAO
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */
public class ReservaDAOImplement implements ReservaDAO {
	
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Implementación del método que obtiene una reserva a partir de un id ingresado.
	 * @param idReserva
	 * @return reserva
	 * @throws MyException
	 */
	@Override
	public Reserva obtener(int idReserva) throws MyException {
		Reserva reserva = new Reserva();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			reserva = (Reserva) session.get(Reserva.class, idReserva);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando la reserva", e);
		}
		return reserva;
	}

	/**
	 * Implementación del método que entrega una lista con todos las reservas en la BD
	 * @param 
	 * @return List<Reserva>
	 * @throws MyException
	 */
	@Override
	public List<Reserva> listaObtener() throws MyException {
		List<Reserva> reservas = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Reserva.class);
			reservas = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando las reservas", e);
			}
		return reservas;
	}

	/**
	 * Implementación del método que permite guardar una reserva.
	 * @param reserva
	 * @throws MyException
	 */
	@Override
	public void guardar(Reserva reserva) throws MyException {
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.save(reserva);
			session.flush();
		}catch(HibernateException e)
		{
			throw new MyException("Ocurrió un error guardando la reserva",e);
		}

	}

	/**
	 * Implementación del método que permite modificar una reserva.
	 * @param reserva
	 * @throws MyException
	 */
	@Override
	public void modificar(Reserva reserva) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(reserva);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando la reserva", e);
		}
		
	}

}

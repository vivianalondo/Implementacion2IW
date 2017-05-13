package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.EstadoDispositivoDAO;
import co.edu.udea.iw.dto.EstadoDispositivo;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de EstadoDispositivoDAO
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class EstadoDispositivoDAOImplement implements EstadoDispositivoDAO{
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Implementación del método que obtiene un estado de dispositivo a partir de un id ingresado.
	 * @param idEstadoDispositivo
	 * @return EstadoDispositivo
	 * @throws MyException
	 */
	@Override
	public EstadoDispositivo obtener(int idEstadoDispositivo) throws MyException {
		EstadoDispositivo estadoDispositivo = new EstadoDispositivo();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			estadoDispositivo = (EstadoDispositivo) session.get(EstadoDispositivo.class, idEstadoDispositivo);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el estado del dispositivo", e);
		}
		return estadoDispositivo;
	}

	/**
	 * Implementación del método que entrega una lista con todos los estados de dispositivo en la BD
	 * @param 
	 * @return List<EstadoDispositivo>
	 * @throws MyException
	 */
	@Override
	public List<EstadoDispositivo> listaObtener() throws MyException {
		List<EstadoDispositivo> estadosDispositivo = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(EstadoDispositivo.class);
			estadosDispositivo = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los estados de los dispositivos", e);
			}
		return estadosDispositivo;
	}

	/**
	 * Implementación del método que permite guardar un estado de dispositivo.
	 * @param estadoDispositivo
	 * @throws MyException
	 */
	@Override
	public void guardar(EstadoDispositivo estadoDispositivo) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.save(estadoDispositivo);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error guardando el estado del dispositivo", e);
		}
	}

	/**
	 * Implementación del método que permite modificar un estado de dispositivo.
	 * @param estadoDispositivo
	 * @throws MyException
	 */
	@Override
	public void modificar(EstadoDispositivo estadoDispositivo) throws MyException {
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(estadoDispositivo);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Rol", e);
		}
	}

}

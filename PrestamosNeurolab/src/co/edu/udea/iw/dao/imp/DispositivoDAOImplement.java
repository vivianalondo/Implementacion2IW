package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.DispositivoDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los m�todos de DispositivoDAO
 * @author Viviana Londo�o, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class DispositivoDAOImplement implements DispositivoDAO {
	
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
	 * Implementaci�n del m�todo que obtiene un dispositivo a partir de un id ingresado.
	 * @param idDispositivo
	 * @return Dispositivo
	 * @throws MyException
	 */
	@Override
	public Dispositivo obtener(int idDispositivo) throws MyException {
		// TODO Auto-generated method stub
		Dispositivo dispositivo = new Dispositivo();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			dispositivo = (Dispositivo) session.get(Dispositivo.class, idDispositivo);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el Dispositivo", e);
		}
		return dispositivo;
	}
	
	/**
	 * Implementaci�n del m�todo que entrega una lista con todos los dispositivos en la BD
	 * @param 
	 * @return List<Dispositivo>
	 * @throws MyException
	 */
	@Override
	public List<Dispositivo> listaObtener() throws MyException {
		// TODO Auto-generated method stub
		List<Dispositivo> dispositivos = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dispositivo.class);
			dispositivos = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los dispositivos", e);
			}
		return dispositivos;
	}

	/**
	 * Implementaci�n del m�todo que permite que guardar un dispositivo.
	 * @param Dispositivo
	 * @throws MyException
	 */
	@Override
	public void guardar(Dispositivo dispositivo) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
		//Transaction tx = null;
		
		try{
			session = sessionFactory.openSession();
			session.save(dispositivo);
			session.flush();
		}catch(HibernateException e)
		{
			throw new MyException("Ocurri� un error guardando el dispositivo",e);
		}

	}
	
	/**
	 * Implementaci�n del m�todo que permite modificar un dispositivo
	 * @param Dispositivo
	 * @throws MyException
	 */
	@Override
	public void modificar(Dispositivo dispositivo) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(dispositivo);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Rol", e);
		}
	}
	
	/**
	 * Implementaci�n del m�todo que entrega una lista por nombre con todos los dispositivos en la BD
	 * @param 
	 * @return List<Dispositivo>
	 * @throws MyException
	 */
	@Override
	public List<Dispositivo> listaObtenerPorNombre(String nombreDispositivo) throws MyException {
		List<Dispositivo> dispositivos = new ArrayList();
		  Session session = null;
		  
		  try {
		   session = sessionFactory.getCurrentSession();
		   Criteria criteria = session.createCriteria(Dispositivo.class).add(Restrictions.like("nombre", nombreDispositivo));
		   dispositivos = criteria.list();
		  } 
		  catch (HibernateException e) {
		   throw new MyException("Error consultando los dispositivos", e);
		   }
		  return dispositivos;
	}

	
	/**
	 * Implementaci�n del m�todo que entrega una lista por nombre con todos los dispositivos en la BD
	 * @param 
	 * @return List<Dispositivo>
	 * @throws MyException
	 */
	@Override
	public List<Dispositivo> listaObtenerActivosPorNombre(String nombreDispositivo, int idEstado) throws MyException {
		List<Dispositivo> dispositivos = new ArrayList();
		  Session session = null;
		  
		  try {
		   session = sessionFactory.getCurrentSession();
		   Criteria criteria = session.createCriteria(Dispositivo.class).add(Restrictions.like("nombre", nombreDispositivo)).add(Restrictions.like("estadoDispositivo.idEstadoDispositivo", idEstado));
		   dispositivos = criteria.list();
		  } 
		  catch (HibernateException e) {
		   throw new MyException("Error consultando los dispositivos", e);
		   }
		  return dispositivos;
	}

	
	/**
	 * Implementaci�n del m�todo que entrega una lista con todos los dispositivos en la BD
	 * @param 
	 * @return List<Dispositivo>
	 * @throws MyException
	 */
	@Override
	public List<Dispositivo> listaObtenerActivos(int idEstado) throws MyException {
		// TODO Auto-generated method stub
		List<Dispositivo> dispositivos = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Dispositivo.class).add(Restrictions.like("estadoDispositivo.idEstadoDispositivo", idEstado));
			dispositivos = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los dispositivos", e);
			}
		return dispositivos;
	}
	
}

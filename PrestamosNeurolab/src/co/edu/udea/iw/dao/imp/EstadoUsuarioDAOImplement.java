package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.EstadoUsuarioDAO;
import co.edu.udea.iw.dto.EstadoUsuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de EstadoUsuario
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class EstadoUsuarioDAOImplement implements EstadoUsuarioDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Implementación del método que obtiene un estado de usuario a partir de un id ingresado.
	 * @param idEstadoUsuario
	 * @return EstadoUsuario
	 * @throws MyException
	 */
	@Override
	public EstadoUsuario obtener(int idEstadoUsuario) throws MyException {
		
		EstadoUsuario estadoUsuario = new EstadoUsuario();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			estadoUsuario = (EstadoUsuario) session.get(EstadoUsuario.class, idEstadoUsuario);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el Estado del Usuario", e);
		}
		return estadoUsuario;
	}

	/**
	 * Implementación del método que entrega una lista con todos los estados de usuario en la BD
	 * @param 
	 * @return List<EstadoUsuario>
	 * @throws MyException
	 */
	@Override
	public List<EstadoUsuario> listaObtener() throws MyException {
		
		List<EstadoUsuario> estadoUsuarios = new ArrayList();
		Session session = null;
		
	try {
		session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EstadoUsuario.class);
		estadoUsuarios = criteria.list();
	} 
	catch (HibernateException e) {
		throw new MyException("Error consultando los Estados de Usuario", e);
		}
	return estadoUsuarios;	
	}

	/**
	 * Implementación de método que permite guardar un estado de usuario.
	 * @param estadoUsuario
	 * @throws MyException
	 */
	@Override
	public void guardar(EstadoUsuario estadoUsuario) throws MyException {

		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.save(estadoUsuario);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error guardando el Estado de Usuario", e);
		}	

	}

	/**
	 * Implementación de método que permite modificar un estado de usuario.
	 * @param estadoUsuario
	 * @throws MyException
	 */
	@Override
	public void modificar(EstadoUsuario estadoUsuario) throws MyException {

		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(estadoUsuario);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Estado del Usuario", e);
		}	

	}	

}

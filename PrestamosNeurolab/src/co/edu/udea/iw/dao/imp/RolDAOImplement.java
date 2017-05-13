package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.iw.dao.RolDAO;
import co.edu.udea.iw.dto.Rol;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de RolDAO
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */
public class RolDAOImplement implements RolDAO {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Implementación del método que obtiene un rl a partir de un id ingresado.
	 * @param idRol
	 * @return rol
	 * @throws MyException
	 */
	@Override
	public Rol obtener(int idRol) throws MyException {
		
		Rol rol = new Rol();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			rol = (Rol) session.get(Rol.class, idRol);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el Rol", e);
		}
		return rol;
	}

	/**
	 * Implementación del método que entrega una lista con todos los roles en la BD
	 * @param 
	 * @return List<Rol>
	 * @throws MyException
	 */
	@Override
	public List<Rol> listaObtener() throws MyException {
		
		List<Rol> roles = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Rol.class);
			roles = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los Roles", e);
			}
		return roles;	
	}

	/**
	 * Implementación del método que permite guardar un rol.
	 * @param rol
	 * @throws MyException
	 */
	@Override
	public void guardar(Rol rol) throws MyException {

		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.save(rol);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error guardando el Rol", e);
		}	

	}

	/**
	 * Implementación del método que permite modificar un rol.
	 * @param rol
	 * @throws MyException
	 */
	@Override
	public void modificar(Rol rol) throws MyException {

		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(rol);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el Rol", e);
		}	

	}	

}

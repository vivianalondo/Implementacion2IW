package co.edu.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Dispositivo;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

/**
 * Clase donde se implementa los métodos de EquipoXReserva
 * @author Viviana Londoño, Oscar Lopera, Johanna Arenas
 * @version 1.0
 */

public class UsuarioDAOImplement implements UsuarioDAO {

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
	 * Implementación del método que obtiene un usuario a partir de un id ingresado.
	 * @param identificacion
	 * @return usuario
	 * @throws MyException
	 */
	@Override
	public Usuario obtener(String identificacion) throws MyException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		Session session = null;
		
		try{
			session = sessionFactory.getCurrentSession();
			usuario = (Usuario) session.get(Usuario.class, identificacion);
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el usuario", e);
		}
		return usuario;
	}

	/**
	 * Implementación del método que entrega una lista con todos los usuarios en la BD
	 * @param 
	 * @return List<Usuario>
	 * @throws MyException
	 */
	@Override
	public List<Usuario> listaObtener() throws MyException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = new ArrayList();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Usuario.class);
			usuarios = criteria.list();
		} 
		catch (HibernateException e) {
			throw new MyException("Error consultando los usuarios", e);
			}
		return usuarios;
	}

	/**
	 * Implementación del método que permite guardar un usuario.
	 * @param usuario
	 * @throws MyException
	 */
	@Override
	public void guardar(Usuario usuario) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
	
		try{
			session = sessionFactory.openSession();
			session.save(usuario);
			session.flush();
		}catch(HibernateException e)
		{
			throw new MyException("Ocurrió un error guardando el usuario",e);
		}

	}

	/**
	 * Implementación del método que permite modificar un usuario.
	 * @param usuario
	 * @throws MyException
	 */
	@Override
	public void modificar(Usuario usuario) throws MyException {
		// TODO Auto-generated method stub
		Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();
			session.update(usuario);
			session.flush();
			}
		catch(HibernateException e)
		{
			throw new MyException("Error modificando el usuario", e);
		}
	}

	/**
	 * Implementación del método que entrega  un usuario con el login ingresado
	 * @param login
	 * @return Usuario 
	 * @throws MyException
	 */
	@Override
	public Usuario obtenerPorLogin(String login) throws MyException {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		Session session = null;
				
		try{
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Usuario.class).add(Restrictions.like("login", login));
			
			if (criteria.list().size()==0) {
				usuario = null;
			}else{
				usuario = (Usuario) criteria.list().get(0);
			}
			
		}
		catch(HibernateException e)
		{
			throw new MyException("Error consultando el usuario", e);
		}
		return usuario;
	}

}

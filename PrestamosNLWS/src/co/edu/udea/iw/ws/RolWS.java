package co.edu.udea.iw.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import co.edu.udea.iw.bl.RolBl;
import co.edu.udea.iw.exception.MyException;


/***
 * Clase para crear los servicios web de Rol
 * @author Viviana
 *
 */

@Path("Rol")
@Component
public class RolWS {
	
	@Autowired
	RolBl rolBl;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String autenticar(@QueryParam("idRol")int idRol){
		String retorno = "";
		
		try{
			rolBl.obtener(idRol);
		}catch(MyException e){
			return e.getMessage();
		}
		return retorno;
	}

}

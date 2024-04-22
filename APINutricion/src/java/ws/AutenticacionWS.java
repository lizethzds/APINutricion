
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.RespuestaLoginAplicacion;
import modelo.pojo.RespuestaLoginEscritorio;


@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

  
    public AutenticacionWS() {
    }

    @POST
    @Path("loginMedico")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio loginEscritorio(@FormParam("numeroDePersonal") String numeroDePersonal, 
                                  @FormParam("password") String password){
        RespuestaLoginEscritorio respuestaLogin = null;
        if(numeroDePersonal != null && !numeroDePersonal.isEmpty() && password != null && !password.isEmpty()){
            respuestaLogin = AutenticacionDAO.verificarSesionEscritorio(numeroDePersonal, 
                                                                        password);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuestaLogin;
    }
    
    @POST
    @Path("loginPaciente")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginAplicacion loginAplicacion(@FormParam("correo") String correo, 
                                  @FormParam("password") String password){
        RespuestaLoginAplicacion respuestaLogin = null;
        if(correo != null && !correo.isEmpty() && password != null && !password.isEmpty()){
            respuestaLogin = AutenticacionDAO.verificarSesionApp(correo, password);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuestaLogin;
    }
}

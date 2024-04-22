package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.DomicilioDAO;
import modelo.pojo.Domicilio;
import modelo.pojo.Mensaje;

@Path("domicilio")
public class DomicilioWS {
    
    @Context
    private UriInfo context;
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarDomicilio(String jsonParam){
        Mensaje msj = new Mensaje();
        try {
            Gson gson = new Gson();
            Domicilio domicilio = gson.fromJson(jsonParam, Domicilio.class);
            if(domicilio != null && domicilio.getIdPaciente() != null 
                    && domicilio.getIdPaciente() > 0){
                msj = DomicilioDAO.registarDomicilio(domicilio);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Error: "+e.getMessage());
        }
        return msj;
    }
    
    @Path("obtenerDomicilio/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Domicilio obtenerDomicilioPaciente(@PathParam("idPaciente") 
                                                Integer idPaciente){
        if(idPaciente != null && idPaciente > 0){
            return DomicilioDAO.obtenerDomicilioPaciente(idPaciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}

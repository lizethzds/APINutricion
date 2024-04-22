package ws;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.PacientesDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;
import modelo.pojo.PacienteAlimento;

@Path("pacientes")
public class PacientesWS {
    
    @Context
    private UriInfo context;
    
    @GET
    @Path("obtenerPacientesMedico/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> obtenerPaciente(@PathParam("idMedico") Integer idMedico){
        List<Paciente> pacientes = new ArrayList<>();
        if(idMedico != null && idMedico > 0){
            pacientes = PacientesDAO.obtenerPacientesMedico(idMedico);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return pacientes;
    }
   
    
    //Modificar request para que reciba un JSON
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaciente(@FormParam("nombre") String nombre,
                                     @FormParam("apellidoPaterno") String apellidoPaterno,
                                     @FormParam("apellidoMaterno") String apellidoMaterno,
                                     @FormParam("fechaNacimiento") String fechaNacimiento,
                                     @FormParam("sexo") String sexo,
                                     @FormParam("peso") Float peso,
                                     @FormParam("estatura") Integer estatura,
                                     @FormParam("talla") Integer talla,
                                     @FormParam("email") String email,
                                     @FormParam("telefono") String telefono,
                                     @FormParam("password") String password,
                                     @FormParam("idMedico") Integer idMedico){
        Mensaje msj = new Mensaje();
        boolean infoValida = true;
        if(nombre.isEmpty()){
            infoValida = false;
        }
        if(apellidoPaterno.isEmpty()){
            infoValida = false;
        }
        if(apellidoMaterno.isEmpty()){
            infoValida = false;
        }
        if(fechaNacimiento.isEmpty()){
            infoValida = false;
        }
        if (sexo.isEmpty() || sexo.length() != 1) {
            infoValida = false;
        }
        if(peso == null || peso == 0){
            infoValida = false;
        }
        if(estatura == null || estatura == 0){
            infoValida = false;
        }
        if(talla == null || talla == 0){
            infoValida = false;
        }
        if(email.isEmpty()){
            infoValida = false;
        }
        if(telefono.isEmpty() || telefono.length() != 10){
            infoValida = false;
        }
        if(password.isEmpty()){
            infoValida = false;
        }
        if(idMedico == null || idMedico == 0){
            infoValida = false;
        }
        
        if(infoValida){
            Paciente paciente = new Paciente();
            paciente.setNombre(nombre);
            paciente.setApellidoPaterno(apellidoPaterno);
            paciente.setApellidoMaterno(apellidoMaterno);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setSexo(sexo);
            paciente.setPeso(peso);
            paciente.setEstatura(estatura);
            paciente.setTalla(talla);
            paciente.setEmail(email);
            paciente.setTelefono(telefono);
            paciente.setPassword(password);
            paciente.setIdMedico(idMedico);
            msj = PacientesDAO.registrarPaciente(paciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    @PUT
    @Path("editarApp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarPacienteApp(String json){
        if(json == null || json.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        Gson gson = new Gson();
        Paciente paciente = gson.fromJson(json, Paciente.class);
        return PacientesDAO.editarPaciente(paciente);
    }
    
    @Path("subirFoto/{idPaciente}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFotoPaciente(@PathParam("idPaciente") Integer idPaciente, 
                                     byte[] foto){
        Mensaje msj = new Mensaje();
        if(idPaciente > 0 && foto != null){
            msj = PacientesDAO.registrarFotoPaciente(idPaciente, foto);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    @Path("obtenerFoto/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paciente obtenerFotoPaciente(@PathParam("idPaciente") Integer idPaciente){
        Paciente paciente = null;
        if(idPaciente != null && idPaciente > 0){
            paciente = PacientesDAO.obtenerFotografiaPaciente(idPaciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return paciente;
    }
    
    @Path("obtenerAlimentos/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PacienteAlimento> obtenerAlimentosPaciente(@PathParam("idPaciente") Integer idPaciente){
        if(idPaciente != null && idPaciente > 0){
            return PacientesDAO.obtenerAlimentos(idPaciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
}

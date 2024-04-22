package modelo.pojo;


public class RespuestaLoginAplicacion {
    
    private Boolean error;
    private String mensaje;
    private Paciente pacienteSesion;

    public RespuestaLoginAplicacion() {
    }

    public RespuestaLoginAplicacion(Boolean error, String mensaje, Paciente pacienteSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.pacienteSesion = pacienteSesion;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Paciente getPacienteSesion() {
        return pacienteSesion;
    }

    public void setPacienteSesion(Paciente pacienteSesion) {
        this.pacienteSesion = pacienteSesion;
    }
    
    
}

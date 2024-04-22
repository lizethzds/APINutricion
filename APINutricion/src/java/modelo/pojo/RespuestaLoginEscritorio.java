package modelo.pojo;


public class RespuestaLoginEscritorio {
    
    private Boolean error;
    private String mensaje;
    private Medico medicoSesion;

    public RespuestaLoginEscritorio() {
    }

    public RespuestaLoginEscritorio(Boolean error, String mensaje, Medico medicoSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.medicoSesion = medicoSesion;
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

    public Medico getMedicoSesion() {
        return medicoSesion;
    }

    public void setMedicoSesion(Medico medicoSesion) {
        this.medicoSesion = medicoSesion;
    }
    
    
}

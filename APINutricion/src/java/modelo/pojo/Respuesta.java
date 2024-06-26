package modelo.pojo;


public class Respuesta {
    
    private Boolean error;
    private String mensaje;
    private Object contenido;

    public Respuesta() {
    }

    public Respuesta(Boolean error, String mensaje, Object contenido) {
        this.error = error;
        this.mensaje = mensaje;
        this.contenido = contenido;
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

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }
    
}

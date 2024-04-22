package modelo.pojo;


public class PacienteAlimento {

    private Integer idAlimento;
    private String nombre;
    private Integer calorias;
    private Integer cantidad;
    private Integer idPorcion;
    private String porcion;

    public PacienteAlimento() {
    }

    public PacienteAlimento(Integer idAlimento, String nombre, Integer calorias, Integer cantidad, Integer idPorcion, String porcion) {
        this.idAlimento = idAlimento;
        this.nombre = nombre;
        this.calorias = calorias;
        this.cantidad = cantidad;
        this.idPorcion = idPorcion;
        this.porcion = porcion;
    }

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdPorcion() {
        return idPorcion;
    }

    public void setIdPorcion(Integer idPorcion) {
        this.idPorcion = idPorcion;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }
    
    
}

package logica;

public class Propuesta {
    private String titulo;
    private String descripcion;
    //private imagen - como implementarlo?
    private String lugar;
    private String fechaPublicacion;
    private float precioEntrada;
    private float montoNecesario;

    public Propuesta() {
    }

    public Propuesta(String titulo, String descripcion, String lugar, String fechaPublicacion, float precioEntrada, float montoNecesario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaPublicacion = fechaPublicacion;
        this.precioEntrada = precioEntrada;
        this.montoNecesario = montoNecesario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public float getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public float getMontoNecesario() {
        return montoNecesario;
    }

    public void setMontoNecesario(float montoNecesario) {
        this.montoNecesario = montoNecesario;
    }
}

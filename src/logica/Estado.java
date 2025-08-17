package logica;

import java.util.List;

public class Estado {
    private String fecha;
    private List<TipoEstado> estado;
    private Categoria categoria;

    public Estado() {}
    public Estado(String fecha, List<TipoEstado> estado, Categoria categoria) {
        this.fecha = fecha;
        this.estado = estado;
        this.categoria = categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<TipoEstado> getEstado() {
        return estado;
    }

    public void setEstado(List<TipoEstado> estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

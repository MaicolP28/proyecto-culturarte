package logica;

import java.time.LocalDate;

public class Estado {
    private LocalDate fecha;
    private TipoEstado estado;

    public Estado() {}

    public Estado(LocalDate fecha, TipoEstado estado) {
        this.fecha = fecha;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

}

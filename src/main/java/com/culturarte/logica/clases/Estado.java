package com.culturarte.logica.clases;

import com.culturarte.logica.enums.TipoEstado;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

@Entity
public class Estado {
 
    private LocalDate fecha;
    @Id
    @Enumerated(EnumType.STRING)
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

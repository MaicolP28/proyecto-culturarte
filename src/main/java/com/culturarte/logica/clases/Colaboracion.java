package com.culturarte.logica.clases;

import com.culturarte.logica.enums.TipoRetorno;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Colaboracion {
    private float monto;
    private LocalDate fechaAporte;
    @Enumerated(EnumType.STRING)
    private TipoRetorno tipoRetorno;
    private Propuesta propuesta;
    private Colaborador colaborador;
   
    public Colaboracion(){}

    public Colaboracion(float monto, LocalDate fechaAporte, TipoRetorno tipoRetorno, Propuesta propuesta, Colaborador colaborador) {
        this.monto = monto;
        this.fechaAporte = fechaAporte;
        this.tipoRetorno = tipoRetorno;
        this.propuesta = propuesta;
        this.colaborador = colaborador;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public LocalDate getFechaAporte() {
        return fechaAporte;
    }

    public void setFechaAporte(LocalDate fechaAporte) {
        this.fechaAporte = fechaAporte;
    }

    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public Propuesta getPropuesta() {
        return getPropuesta();
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    public Colaborador getColaborador() {
        return getColaborador();
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    public String getColaboradorNick() {
        return colaborador.getNickname();
    }
}

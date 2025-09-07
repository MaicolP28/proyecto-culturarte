package com.culturarte.logica.clases;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class ColaboracionId implements Serializable {

    @OneToOne
    private Propuesta propuesta;

    @OneToMany
    private Colaborador colaborador;

    public ColaboracionId() {}

    public ColaboracionId(Propuesta propuesta, Colaborador colaborador) {
        this.propuesta = propuesta;
        this.colaborador = colaborador;
    }

    public Propuesta getPropuesta() {
        return propuesta;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }
}
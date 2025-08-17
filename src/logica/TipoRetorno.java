package logica;

import java.util.List;

public class TipoRetorno {
    private Boolean entradaGratis;
    private int porcentajeGanancia;
    private List<Propuesta> propuestas;

    public TipoRetorno() {}

    public TipoRetorno(Boolean entradaGratis, int porcentajeGanancia, List<Propuesta> propuestas) {
        this.entradaGratis = entradaGratis;
        this.porcentajeGanancia = porcentajeGanancia;
        this.propuestas = propuestas;
    }
}

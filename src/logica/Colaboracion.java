package logica;

public class Colaboracion {
    private int monto;
    private String fechaAporte;
    private Boolean esColaborador;
    private TipoRetorno tipoRetorno;

    public Colaboracion(){}
    public Colaboracion(int monto, String fechaAporte, Boolean esColaborador) {
        this.monto = monto;
        this.fechaAporte = fechaAporte;
        this.esColaborador = esColaborador;
    }

    public int getMonto() {
        return monto;
    }

    public String getFechaAporte() {
        return fechaAporte;
    }

    public Boolean getEsColaborador() {
        return esColaborador;
    }

    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public void setFechaAporte(String fechaAporte) {
        this.fechaAporte = fechaAporte;
    }

    public void setEsColaborador(Boolean esColaborador) {
        this.esColaborador = esColaborador;
    }

    public void setTipoRetorno(TipoRetorno tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }
}

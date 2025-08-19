package logica.clases;

import logica.enums.TipoEstado;
import logica.enums.TipoRetorno;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;

public class Propuesta {
    private String titulo;
    private String descripcion;
    private String lugar;
    private LocalDate fechaPrevista;
    private float precioEntrada;
    private float montoNecesario;
    private EnumSet<TipoRetorno> tipoRetornos;
    private File imagen;
    private ArrayList<Colaboracion> colaboraciones;
    private Proponente proponente;
    private ArrayList<Estado> historialEstados;
    private Estado estadoActual;
    private Categoria categoria;

    public Propuesta() {
    }

    public Propuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrevista, float precioEntrada, float montoNecesario, EnumSet<TipoRetorno> tipoRetornos, File imagen, Proponente proponente, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaPrevista = fechaPrevista;
        this.precioEntrada = precioEntrada;
        this.montoNecesario = montoNecesario;
        this.tipoRetornos = tipoRetornos;
        this.imagen = imagen;
        this.proponente = proponente;
        this.categoria = categoria;
        this.colaboraciones = new ArrayList<>();

        this.estadoActual = new Estado(LocalDate.now(), TipoEstado.INGRESADA);
        this.historialEstados = new ArrayList<>();
        this.historialEstados.add(this.estadoActual);
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

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(LocalDate fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
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

    public Proponente getProponente() {
        return proponente;
    }

    public void setProponente(Proponente proponente) {
        this.proponente = proponente;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

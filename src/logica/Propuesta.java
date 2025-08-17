package logica;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
}

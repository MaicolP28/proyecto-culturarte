package com.culturarte.logica.clases;

import com.culturarte.logica.enums.TipoEstado;
import com.culturarte.logica.enums.TipoRetorno;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Entity
public class Propuesta {
    @Id
    private String titulo;
    private String descripcion;
    private String lugar;
    private LocalDate fechaPrevista;
    private float precioEntrada;
    private float montoNecesario;
    @Enumerated(EnumType.STRING)
    private EnumSet<TipoRetorno> tipoRetornos;
    private File imagen;
//    private String imagenPath; 
    @OneToMany(mappedBy="propuesta")
    private List<Colaboracion> colaboraciones;
    @ManyToOne
    private Proponente proponente;
    
    @OneToMany
    private List<Estado> historialEstados;
    @OneToOne
    private Estado estadoActual;
    
    @ManyToOne
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
    

//    @PostLoad
//    private void postLoad() {
//    if (imagenPath != null) {
//        imagen = new File(imagenPath);
//        }
//    }
//
//    @PrePersist
//    @PreUpdate
//    private void preSave() {
//    imagenPath = (imagen != null) ? imagen.getPath() : null;
//    }
    
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
    
    public File getImagen(){
        return imagen;
    }
    
    public List<Colaboracion> getColaboraciones(){
        return colaboraciones;
    }
    
    public void addColaboracion(Colaboracion colab){
        this.colaboraciones.add(colab);
    }
    
    public ArrayList<String> getNicknameColaboradores(){
        ArrayList<String> listaNicks = new ArrayList<>();
        for(Colaboracion c : colaboraciones){
            listaNicks.add(c.getColaboradorNick());
        }
        return listaNicks;
    }
    
    public String getProponenteNick() {
        return proponente.getNickname();
    }
    
    public float getMontoRecaudado() {
        float montoRecuadado = 0;
        for(Colaboracion c : colaboraciones){
            montoRecuadado += c.getMonto();
        }
        return montoRecuadado;
    }
}

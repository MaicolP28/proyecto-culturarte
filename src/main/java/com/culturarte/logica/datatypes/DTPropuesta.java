/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.datatypes;

import com.culturarte.logica.clases.Categoria;
import com.culturarte.logica.clases.Colaboracion;
import com.culturarte.logica.clases.Estado;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class DTPropuesta {
    private String titulo;
    private String descripcion;
    private String lugar;
    private LocalDate fechaPrevista;
    private float precioEntrada;
    private float montoNecesario;
    private File imagen;
    private ArrayList<Colaboracion> colaboraciones;
    private ArrayList<String> colaboradores;
    private Estado estadoActual;
    private Categoria categoria;
    
    public DTPropuesta(){}
    
    public DTPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrevista, float precioEntrada, float montoNecesario, File imagen, ArrayList<Colaboracion> colaboraciones, ArrayList<String> colaboradores, Estado estadoActual, Categoria categoria){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaPrevista = fechaPrevista;
        this.precioEntrada = precioEntrada;
        this.montoNecesario = montoNecesario;
        this.imagen = imagen;
        this.colaboraciones = colaboraciones;
        this.colaboradores = colaboradores;
        this.estadoActual = estadoActual;
        this.categoria = categoria;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Descripcion: ").append(descripcion).append("\n");
        sb.append("Lugar: ").append(lugar).append("\n");
        sb.append("Fecha Prevista: ").append(fechaPrevista).append("\n");
        sb.append("Precio Entrada: ").append(precioEntrada).append("\n");
        sb.append("Monto Necesario: ").append(montoNecesario).append("\n");
        sb.append("Colaboraciones: ").append(colaboraciones).append("\n");
        sb.append("Colaboradores: ").append(colaboradores).append("\n");
        sb.append("Estado: ").append(estadoActual).append("\n");
        sb.append("Categoria: ").append(categoria).append("\n");
        //Falta el monto Recaurdado
        return sb.toString();
    }
}

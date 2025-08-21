/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.datatypes;

import com.culturarte.logica.clases.Categoria;
import com.culturarte.logica.clases.Colaboracion;
import com.culturarte.logica.clases.Colaborador;
import com.culturarte.logica.clases.Estado;
import com.culturarte.logica.clases.Proponente;
import com.culturarte.logica.enums.TipoRetorno;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

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
}

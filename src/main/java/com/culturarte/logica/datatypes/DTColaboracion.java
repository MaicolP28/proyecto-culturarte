/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.datatypes;
import com.culturarte.logica.enums.TipoRetorno;
import java.time.LocalDate;

/**
 *
 * @author fabriciorivero
 */
public class DTColaboracion {
    
    private float monto;
    private LocalDate fecha;
    private TipoRetorno tipoRetorno;
    private String tituloPropuesta;
    private String nickColaborador;
    
    public DTColaboracion(String nickColaborador,String tituloPropuesta, LocalDate fecha, float monto, TipoRetorno tipoRetorno) {
        this.tituloPropuesta = tituloPropuesta;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoRetorno = tipoRetorno;
        this.nickColaborador = nickColaborador;
    }

    public String getPropuestaTitulo() {
        return tituloPropuesta;
    }
    
    public String getNickColaborador() {
        return nickColaborador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public float getMonto() {
        return monto;
    }

    public TipoRetorno getTipoRetorno() {
        return tipoRetorno;
    }
    
    
}


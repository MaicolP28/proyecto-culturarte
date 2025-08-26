package com.culturarte.logica.manejadores;

import com.culturarte.logica.clases.Propuesta;
import java.util.ArrayList;

import java.util.HashMap;

public class ManejadorPropuesta {
    private HashMap<String, Propuesta> propuestasTitulo;
    private  static ManejadorPropuesta instancia = null;

    private ManejadorPropuesta(){
        propuestasTitulo = new HashMap<>();
    }

    public static ManejadorPropuesta getInstancia(){
        if(instancia == null){
            instancia = new ManejadorPropuesta();
        }
        return instancia;
    }

    public void agregarPropuesta(Propuesta propuesta){
        propuestasTitulo.put(propuesta.getTitulo(), propuesta);
    }

    public Propuesta getPropuesta(String titulo){
        return propuestasTitulo.get(titulo);
    }
    
    public Propuesta buscarPropuesta(String titulo) {
        return ((Propuesta) propuestasTitulo.get(titulo));
    }
    
    public ArrayList<String> getTitulos(){
        //Retorna el titulo de todas las propuestas
        return new ArrayList<>(propuestasTitulo.keySet());
    }
}

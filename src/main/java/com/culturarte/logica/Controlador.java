/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.clases.*;
import com.culturarte.logica.datatypes.DTProponente;
import com.culturarte.logica.datatypes.DTPropuesta;
import com.culturarte.logica.enums.TipoEstado;
import com.culturarte.logica.manejadores.ManejadorUsuario;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author maicol
 */
public class Controlador implements IControlador{
    
    private Map<String, Proponente> proponentes;
    private Map<String, Propuesta> propuestas;
    
    public Controlador() {
        
    }
    
    @Override
    public void altaColaborador(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen)
            throws UsuarioYaExiste {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.buscarUsuario(nickname);
        if (u != null) {
            throw new UsuarioYaExiste("El usuario con nickname " + nickname + " ya está registrado");
        }
        mu.agregarUsuario(new Colaborador(nickname, nombre, apellido, email, fechaNacimiento, imagen));
    }
    
    @Override
    public void altaProponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String bibliografia)
            throws UsuarioYaExiste {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.buscarUsuario(nickname);
        if (u != null) {
            throw new UsuarioYaExiste("El usuario con nickname " + nickname + " ya está registrado");
        }
        mu.agregarUsuario(new Proponente(nickname, nombre, apellido, email, fechaNacimiento, imagen, direccion, linkWeb, bibliografia));
    }
     
    @Override
    public Map<String, DTProponente> getDTProponentes(){
        Map<String, DTProponente> lista = new HashMap<>();
        for(Map.Entry<String, Proponente> entry : proponentes.entrySet())
        {
            Proponente p = entry.getValue();
            DTProponente dtp = new DTProponente(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(), p.getFechaNacimiento(), p.getImagen(), p.getDireccion(), p.getLinkWeb(), p.getBiografia());
            lista.put(p.getNickname(), dtp);
        }
        return lista;        
    }
    
    @Override
    public Map<TipoEstado, ArrayList<DTPropuesta>> getDTPropuestasProponentes(String nickname){
        Map<TipoEstado, ArrayList<DTPropuesta>> lista = new HashMap<>();
        for(Map.Entry<String, Propuesta> entry : propuestas.entrySet()){
            Propuesta p = entry.getValue();
            DTPropuesta dtp = new DTPropuesta(p.getTitulo(), p.getDescripcion(), p.getLugar(), p.getFechaPrevista(), p.getPrecioEntrada(), p.getMontoNecesario(),
            p.getImagen(), p.getColaboraciones(), p.getNicknameColaboradores(), p.getEstadoActual(), p.getCategoria());
            TipoEstado est = p.getEstadoActual().getEstado();
            lista.computeIfAbsent(est, k -> new ArrayList<>()).add(dtp);
        }
        return lista;
    }
}



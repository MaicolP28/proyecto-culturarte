/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.clases.*;
import com.culturarte.logica.manejadores.ManejadorUsuario;
import java.io.File;
import java.time.LocalDate;

/**
 *
 * @author maicol
 */
public class Controlador implements IControlador{
    
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
    
}

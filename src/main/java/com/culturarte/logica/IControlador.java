/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.datatypes.DTProponente;
import com.culturarte.logica.datatypes.DTPropuesta;
import com.culturarte.logica.enums.TipoEstado;
import java.time.LocalDate;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author maicol
 */
public interface IControlador {
    
    public abstract void altaColaborador(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen) throws UsuarioYaExiste;
    public abstract void altaProponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String bibliografia) throws UsuarioYaExiste;
    public abstract Map<String, DTProponente> listarProponentes();
    public abstract Map<TipoEstado, ArrayList<DTPropuesta>> listarPropuestasProponentes(String nickname);
}

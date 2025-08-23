/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.datatypes.DTProponente;
import java.util.ArrayList;
import com.culturarte.exepciones.PropuestaYaExiste;
import com.culturarte.logica.datatypes.DTColaborador;
import com.culturarte.logica.enums.TipoRetorno;
import java.time.LocalDate;
import java.io.File;
import java.util.EnumSet;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author maicol
 */
public interface IControlador {
    
    public abstract void altaColaborador(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen) throws UsuarioYaExiste;
    public abstract void altaProponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String bibliografia) throws UsuarioYaExiste;
    public abstract ArrayList<String> getNomProponentes();
    public abstract DTProponente getDTProponente(String nickname);
    public abstract void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste;
    public abstract DefaultTreeModel listarCategorias();
    public abstract void altaPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrevista, Float precioEntrada, Float montoNecesario, EnumSet<TipoRetorno> tipoRetornos, File imagen, String proponente, String categoria) throws PropuestaYaExiste;
    public abstract ArrayList<String> getNickColaboradores();
    public abstract DTColaborador getDTColaborador(String nickname);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.datatypes.DTProponente;
import com.culturarte.logica.datatypes.DTPropuesta;
import com.culturarte.logica.enums.TipoEstado;
import java.util.ArrayList;
import java.util.Map;
import com.culturarte.exepciones.PropuestaYaExiste;
import com.culturarte.logica.clases.Categoria;
import com.culturarte.logica.clases.Proponente;
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
    public abstract Map<String, DTProponente> getDTProponentes();
    public abstract Map<TipoEstado, ArrayList<DTPropuesta>> getDTPropuestasProponentes(String nickname);
    public abstract void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste;
    public abstract DefaultTreeModel listarCategorias();
    public void altaPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrevista, float precioEntrada, float montoNecesario, EnumSet<TipoRetorno> tipoRetornos, File imagen, Proponente proponente, Categoria categoria) throws PropuestaYaExiste;
}

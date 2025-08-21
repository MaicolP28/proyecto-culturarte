/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import java.time.LocalDate;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author maicol
 */
public interface IControlador {
    
    public abstract void altaColaborador(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen) throws UsuarioYaExiste;
    public abstract void altaProponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String bibliografia) throws UsuarioYaExiste;
    public abstract void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste;
    public abstract DefaultMutableTreeNode listarCategorias();
}

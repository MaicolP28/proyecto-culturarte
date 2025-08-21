/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.logica.clases.*;
import com.culturarte.logica.manejadores.ManejadorCategoria;
import com.culturarte.logica.manejadores.ManejadorUsuario;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
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
    @Override
    public void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste{
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        
        if (mc.buscarCategoria(nombre) == null) {
            throw new CategoriaYaExiste("Ya existe esta categoria");
        }
        
        if (catPadre == null) {
            mc.agregarCategoriaRaiz(new Categoria(nombre, null));
        } else {
            Categoria padre = mc.buscarCategoria(catPadre);
            if (padre != null) {
                padre.addSubCategoria(new Categoria(nombre, padre));
            }
        }
        
        
        
        
    }
    
    @Override
    public DefaultMutableTreeNode listarCategorias() {
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Categorías");
        ArrayList<Categoria> categorias = mc.getCategoriasRaiz();
        for (Categoria categoria : categorias) {
            raiz.add(crearNodoCategoria(categoria));
        }
        return raiz;
    }
    
    private DefaultMutableTreeNode crearNodoCategoria(Categoria cat) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(cat);
        for (Categoria sub : cat.getSubCategorias()) {
            nodo.add(crearNodoCategoria(sub));
        }
        return nodo;
    }
    
}

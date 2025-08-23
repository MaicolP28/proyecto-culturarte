/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.manejadores;

import com.culturarte.logica.clases.Categoria;
import java.util.ArrayList;

/**
 *
 * @author maicol
 */
public class ManejadorCategoria {
    private ArrayList<Categoria> categoriasRaiz;
    
    private static ManejadorCategoria instancia = null;
    
    private ManejadorCategoria(){
        categoriasRaiz = new ArrayList<Categoria>();
    }
    
    public static ManejadorCategoria getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorCategoria();
        }
        return instancia;
    }
    
    public void agregarCategoriaRaiz(Categoria categoria) {
        categoriasRaiz.add(categoria);
    }
    
    public ArrayList<Categoria> getCategoriasRaiz() {
        return categoriasRaiz;
    }
    
    public Categoria buscarCategoria(String nombre) {
        for (Categoria cat : categoriasRaiz) {
            Categoria encontrada = buscarRecursivo(cat, nombre);
            if (encontrada != null) {
                return encontrada;
            }
        }
        return null;
    }
    
    private Categoria buscarRecursivo(Categoria cat, String nombre) {
        if (cat.getNombre().equalsIgnoreCase(nombre)) {
            return cat;
        }
        for (Categoria sub : cat.getSubCategorias()) {
            Categoria encontrada = buscarRecursivo(sub, nombre);
            if (encontrada != null) return encontrada;
        }
        return null;
    }
    
}

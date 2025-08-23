package com.culturarte.logica.clases;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private Categoria padre;
    private ArrayList<Categoria> subCategorias;
    

    public Categoria() {}

    public Categoria(String nombre, Categoria padre) {

        this.nombre = nombre;
        this.padre = padre;
        this.subCategorias = new ArrayList<Categoria>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Categoria getPadre() {
        return this.padre;
    }
    
    public void setPadre(Categoria padre) {
        this.padre = padre;
    }

    public ArrayList<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public void addSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }
}

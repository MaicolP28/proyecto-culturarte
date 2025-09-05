package com.culturarte.logica.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.ArrayList;

@Entity
public class Categoria {
    @Id
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
        return this.nombre;
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
    
    public String getNombreCompleto() {
        if (this.padre != null ) {
            return this.padre.getNombre() + ", " + this.nombre;
        } else {
            return nombre;
        }
    }
}

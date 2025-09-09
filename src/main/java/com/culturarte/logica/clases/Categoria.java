package com.culturarte.logica.clases;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria implements Serializable {
    @Id
    private String nombre;
    @ManyToOne
    @JoinColumn(name="padre_nombre")
    private Categoria padre;
    @OneToMany(mappedBy = "padre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> subCategorias;
    

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

    public List<Categoria> getSubCategorias() {
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

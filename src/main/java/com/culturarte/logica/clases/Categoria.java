package logica.clases;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private ArrayList<Categoria> subCategorias;

    public Categoria() {}

    public Categoria(String nombre) {

        this.nombre = nombre;
        this.subCategorias = new ArrayList<Categoria>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Categoria> getSubCategoria() {
        return subCategorias;
    }

    public void addSubCategoria(Categoria subCategoria) {
        this.subCategorias.add(subCategoria);
    }
}

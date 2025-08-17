package logica;

import java.util.List;

public class Categoria {
    private TipoCategoria nombre;
    private List<Categoria> subCategoria;

    public Categoria() {}
    public Categoria(TipoCategoria nombre) {

        this.nombre = nombre;

    }
    public TipoCategoria getNombre() {

        return nombre;
    }
    public void setNombre(TipoCategoria nombre) {

        this.nombre = nombre;
    }

    public List<Categoria> getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(List<Categoria> subCategoria) {
        this.subCategoria = subCategoria;
    }
}

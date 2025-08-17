package logica;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Colaborador extends Usuario {
    private ArrayList<Colaboracion> colaboraciones;

    public Colaborador() {}

    public Colaborador(String nickname, String nombre, String apellido, String email, Date fechaNacimiento, File imagen) {
        super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
        this.colaboraciones = new ArrayList<>();
    }

    public ArrayList<Colaboracion> getColaboraciones() {
        return colaboraciones;
    }

    public void setColaboraciones(Colaboracion colaboracion) {
        this.colaboraciones.add(colaboracion);
    }

}

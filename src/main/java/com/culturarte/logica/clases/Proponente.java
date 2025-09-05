package com.culturarte.logica.clases;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.Entity;

@Entity
public class Proponente extends Usuario {
    private String direccion;
    private String biografia;
    private String linkWeb;
    private ArrayList<Propuesta> propuestas;
    
    public Proponente() {}

    public Proponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String biografia) {
        super(nickname, nombre, apellido, email, fechaNacimiento, imagen);
        this.direccion = direccion;
        this.linkWeb = linkWeb;
        this.biografia = biografia;
        this.propuestas = new ArrayList<>();
    }

    public String getDireccion() {

        return direccion;
    }

    public String getBiografia() {

        return biografia;
    }

    public String getLinkWeb() {

        return linkWeb;
    }

    public ArrayList<Propuesta> getPropuestas() {
        return propuestas;
    }

    public void setDireccion(String direccion) {

        this.direccion = direccion;
    }

    public void setBiografia(String biografia) {

        this.biografia = biografia;
    }

    public void setLinkWeb(String linkWeb) {

        this.linkWeb = linkWeb;
    }

    public void addPropuestas(Propuesta propuesta) {
        this.propuestas.add(propuesta);
    }

}

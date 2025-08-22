/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.clases;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author fabriciorivero
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true)
    private String nickname;   

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String imagen;

    @ManyToMany
    @JoinTable(
        name = "propuestasSeguidas",
        joinColumns = @JoinColumn(name = "usuario_nickname"),
        inverseJoinColumns = @JoinColumn(name = "propuesta_id")
    )
    private List<Propuesta> propuestasSeguidas = new ArrayList<>();

    // Relación de seguir otros usuarios
    @ManyToMany
    @JoinTable(
        name = "usuariosSeguidos",
        joinColumns = @JoinColumn(name = "usuario_nickname"),
        inverseJoinColumns = @JoinColumn(name = "seguido_nickname")
    )
    private List<Usuario> usuariosSeguidos = new ArrayList<>();

    public Usuario() { }

    public Usuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    public List<Usuario> getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void addUsuariosSeguidos(Usuario usuario) {
        this.usuariosSeguidos.add(usuario);
    }

    public List<Propuesta> getPropuestas() {
        return propuestasSeguidas;
    }

    public void addPropuestas(Propuesta propuesta) {
        this.propuestasSeguidas.add(propuesta);
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

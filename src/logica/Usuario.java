package logica;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;
    private ArrayList<Propuesta> propuestasSeguidas;
    private ArrayList<Usuario> usuariosSeguidos;
    private File imagen;

    public Usuario() { }

    public Usuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
        this.propuestasSeguidas = new ArrayList<>();
        this.usuariosSeguidos = new ArrayList<>();
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Usuario> getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void addUsuariosSeguidos(Usuario usuario) {
        this.usuariosSeguidos.add(usuario);
    }

    public ArrayList<Propuesta> getPropuestas() {
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

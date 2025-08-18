package logica.manejadores;

import logica.clases.Usuario;

import java.util.HashMap;

public class ManejadorUsuario {
    private HashMap<String, Usuario> usuariosNick;
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuariosNick = new HashMap<>();
    }

    public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        usuariosNick.put(usuario.getNombre(), usuario);
    }

    public Usuario buscarUsuario(String nick) {
        return ((Usuario) usuariosNick.get(nick));
    }
}

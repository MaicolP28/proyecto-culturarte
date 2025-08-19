package logica;

import logica.controladores.ControladorPropuestas;
import logica.controladores.ControladorUsuario;
import logica.controladores.IControladorPropuestas;
import logica.controladores.IControladorUsuario;

public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    }

    public static Fabrica getInstancia() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getIControladorUsuario() { return new ControladorUsuario(); }
    public IControladorPropuestas  getIControladorPropuestas() { return new ControladorPropuestas(); }

}

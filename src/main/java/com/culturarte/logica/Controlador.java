package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.exepciones.PropuestaYaExiste;
import com.culturarte.logica.clases.*;
import com.culturarte.logica.datatypes.DTColaborador;
import com.culturarte.logica.datatypes.DTProponente;
import com.culturarte.logica.datatypes.DTPropuesta;
import com.culturarte.logica.enums.TipoEstado;
import com.culturarte.logica.enums.TipoRetorno;
import com.culturarte.logica.manejadores.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.EnumSet;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author maicol
 */
public class Controlador implements IControlador{
    
    public Controlador() {
        
    }
    
    @Override
    public void altaColaborador(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen)
            throws UsuarioYaExiste {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.buscarUsuario(nickname);
        if (u != null) {
            throw new UsuarioYaExiste("El usuario con nickname " + nickname + " ya está registrado");
        }
        mu.agregarUsuario(new Colaborador(nickname, nombre, apellido, email, fechaNacimiento, imagen));
    }
    
    @Override
    public void altaProponente(String nickname, String nombre, String apellido, String email, LocalDate fechaNacimiento, File imagen, String direccion, String linkWeb, String bibliografia)
            throws UsuarioYaExiste {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario u = mu.buscarUsuario(nickname);
        if (u != null) {
            throw new UsuarioYaExiste("El usuario con nickname " + nickname + " ya está registrado");
        }
        mu.agregarUsuario(new Proponente(nickname, nombre, apellido, email, fechaNacimiento, imagen, direccion, linkWeb, bibliografia));
    }
    
        
    @Override
    public ArrayList<String> getNickColaboradores(){
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ArrayList<String> retorno = new ArrayList<>();
        
        for (Usuario usu : mu.getUsuariosNick().values()) {
            if (usu instanceof Colaborador) {
                retorno.add(usu.getNickname());
            }
        }
        
        retorno.sort(String.CASE_INSENSITIVE_ORDER); // Ordena la lista
        return retorno;   
    }
     
    @Override
    public ArrayList<String> getNomProponentes(){
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ArrayList<String> retorno = new ArrayList<>();
        
        for (Usuario usu : mu.getUsuariosNick().values()) {
            if (usu instanceof Proponente) {
                retorno.add(usu.getNickname());
            }
        }
        
        retorno.sort(String.CASE_INSENSITIVE_ORDER); // Ordena la lista
        return retorno;        
    }
    
    @Override
    public DTColaborador getDTColaborador(String nickname) {
        
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
       
        Colaborador c =(Colaborador) mu.buscarUsuario(nickname);
       
        DTColaborador dtc = new DTColaborador(c.getNickname(), c.getNombre(), c.getApellido(), c.getEmail(), c.getFechaNacimiento(), c.getImagen());
        
        for (Propuesta prop : c.getPropuestas()) {
            dtc.addPropuesta(new DTPropuesta(prop.getTitulo(), prop.getEstadoActual().getEstado() , prop.getProponenteNick(), prop.getMontoRecaudado(), prop.getMontoNecesario()));
        }
       
        return dtc;
    }
    
    
    @Override
    public DTProponente getDTProponente(String nickname){
        // Datos usuario, datos proponente, Propuestas (nombre, estado, lista colaboradores, monto recaudado, monto necesario)
        
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
       
        Proponente p =(Proponente) mu.buscarUsuario(nickname);
       
        DTProponente dtp = new DTProponente(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(), p.getFechaNacimiento(), p.getImagen(), p.getDireccion(), p.getLinkWeb(), p.getBiografia());
        
        for (Propuesta prop : p.getPropuestas()) {
            dtp.addPropuesta(new DTPropuesta(prop.getTitulo(), prop.getEstadoActual().getEstado() , prop.getNicknameColaboradores(), prop.getMontoRecaudado(), prop.getMontoNecesario()));
        }
       
        return dtp;
    }
    

    public void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste{
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        
        if (mc.buscarCategoria(nombre) != null) {
            throw new CategoriaYaExiste("Ya existe esta categoria");
        }
        
        if (catPadre == null) {
            mc.agregarCategoriaRaiz(new Categoria(nombre, null));
        } else {
            Categoria padre = mc.buscarCategoria(catPadre);
            if (padre != null) {
                padre.addSubCategoria(new Categoria(nombre, padre));
            }
        }
        
    }
    
    @Override
    public DefaultTreeModel listarCategorias() {
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Categorías");
        DefaultTreeModel model = new DefaultTreeModel (raiz);
        
        ArrayList<Categoria> categorias = mc.getCategoriasRaiz();
        for (Categoria categoria : categorias) {
            raiz.add(crearNodoCategoria(categoria));
        }
        return model;
    }
    
    private DefaultMutableTreeNode crearNodoCategoria(Categoria cat) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(cat.getNombre());
        for (Categoria sub : cat.getSubCategorias()) {
            nodo.add(crearNodoCategoria(sub));
        }
        return nodo;
    }
    

    @Override
    public void altaPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrevista, Float precioEntrada, Float montoNecesario, EnumSet<TipoRetorno> tipoRetornos, File imagen, String proponente, String categoria)
    throws PropuestaYaExiste {
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        
        if (mp.buscarPropuesta(titulo) != null) {
            throw new PropuestaYaExiste("Ya existe esta Propuesta");
        }
        
        Proponente u = (Proponente) mu.buscarUsuario(proponente);
        
        Categoria c = mc.buscarCategoria(categoria);
        
        Propuesta p = new Propuesta(titulo,descripcion,lugar,fechaPrevista, precioEntrada, montoNecesario, tipoRetornos, imagen, u,c);
        mp.agregarPropuesta(p);
        u.addPropuestas(p);
    }

    @Override
    public ArrayList<String> getTituloPropuestas() {
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ArrayList<String> retorno = new ArrayList<>();
        
        for (Propuesta p : mp.getPropuestas().values()) {
            retorno.add(p.getTitulo());
        }
        return retorno;
    }
    
    @Override
    public DTPropuesta getDTPropuesta(String titulo){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.buscarPropuesta(titulo);
        DTPropuesta dtp = new DTPropuesta(p.getTitulo(), p.getDescripcion(), p.getLugar(), p.getFechaPrevista(), p.getPrecioEntrada(), p.getMontoNecesario(), p.getImagen(), p.getNicknameColaboradores(), p.getProponenteNick(), p.getEstadoActual().getEstado(), p.getCategoria().getNombreCompleto());
        return dtp;
    }
    
    @Override
    public ArrayList<String> getTituloPropuestasPorEstado(TipoEstado estado){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ArrayList<String> retorno = new ArrayList<>();
        
        for (Propuesta p : mp.getPropuestas().values()) {
            if (p.getEstadoActual().getEstado() == estado)
                retorno.add(p.getTitulo());
        }
        
        return retorno;
    }
    
    @Override 
    public void altaColaboracion(String tituloPropuesta, String nickColaborador, TipoRetorno tipoRetorno, float monto){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.buscarPropuesta(tituloPropuesta);
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Colaborador c = (Colaborador) mu.buscarUsuario(nickColaborador);
        Colaboracion colab = new Colaboracion(monto, LocalDate.now(), tipoRetorno, p, c);
        c.addColaboracion(colab);
        p.addColaboracion(colab);
    }
     
}
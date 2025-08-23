package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.exepciones.PropuestaYaExiste;
import com.culturarte.logica.clases.*;
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
    
    private Map<String, Proponente> proponentes = new HashMap<>();
    private Map<String, Propuesta> propuestas = new HashMap<>();
    
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
    public Map<String, DTProponente> getDTProponentes(){
        Map<String, DTProponente> lista = new HashMap<>();
        for(Map.Entry<String, Proponente> entry : proponentes.entrySet())
        {
            Proponente p = entry.getValue();
            DTProponente dtp = new DTProponente(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(), p.getFechaNacimiento(), p.getImagen(), p.getDireccion(), p.getLinkWeb(), p.getBiografia());
            lista.put(p.getNickname(), dtp);
        }
        return lista;        
    }
    
    @Override
    public Map<TipoEstado, ArrayList<DTPropuesta>> getDTPropuestasProponentes(String nickname){
        Map<TipoEstado, ArrayList<DTPropuesta>> lista = new HashMap<>();
      for(Propuesta p : propuestas.values()){
        if(p.getProponente().getNickname().equals(nickname)){
            DTPropuesta dtp = new DTPropuesta(p.getTitulo(), p.getDescripcion(), p.getLugar(), p.getFechaPrevista(), p.getPrecioEntrada(), p.getMontoNecesario(), p.getImagen(),
            p.getColaboraciones(), p.getNicknameColaboradores(), p.getEstadoActual(), p.getCategoria());
            TipoEstado est = p.getEstadoActual().getEstado();
            lista.computeIfAbsent(est, k -> new ArrayList<>()).add(dtp);
        }
    }
        return lista;
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
     
}
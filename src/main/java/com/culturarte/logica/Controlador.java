package com.culturarte.logica;

import com.culturarte.exepciones.CategoriaYaExiste;
import com.culturarte.exepciones.UsuarioYaExiste;
import com.culturarte.exepciones.PropuestaYaExiste;
import com.culturarte.exepciones.UsuarioNoSeguido;
import com.culturarte.exepciones.UsuarioYaSeguido;
import com.culturarte.logica.clases.*;
import com.culturarte.logica.datatypes.*;
import com.culturarte.logica.enums.*;
import com.culturarte.logica.manejadores.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import jakarta.persistence.*;
import java.util.List;
/**
 *
 * @author maicol
 */
public class Controlador implements IControlador{
    
    public Controlador() {
//        ManejadorUsuario mu = ManejadorUsuario.getInstance();
//        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
//        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
//        
//        
//        
//        // Datos de prueba
//        
//        Colaborador c1 = new Colaborador("maicol123", "Maicol", "Pastor", "maicol@coso", LocalDate.now(), null);
//        Colaborador c2 = new Colaborador("roberto", "Robert", "Gomez", "robert@coso", LocalDate.now(), null);
//        Colaborador c3 = new Colaborador("ñery", "Kevin", "Kante", "kevinvpi@coso", LocalDate.now(), null);
//        mu.agregarUsuario(c1);
//        mu.agregarUsuario(c2);
//        mu.agregarUsuario(c3);
//        
//        Proponente p1 = new Proponente("alonso", "Alonso", "Tornado", "tornadito@vpi", LocalDate.now(), null, "Casa", "www.coso.com", "el propio");
//        Proponente p2 = new Proponente("simio", "Simio", "Mono", "coso@vpi", LocalDate.now(), null, "Casa", "www.coso.com", "el propio");
//        Proponente p3 = new Proponente("juan", "Juan", "Caballo", "caballito@vpi", LocalDate.now(), null, "Casa", "www.coso.com", "el propio");
//        mu.agregarUsuario(p1);
//        mu.agregarUsuario(p2);
//        mu.agregarUsuario(p3);
//        
//        Categoria cat1 = new Categoria("Rock", null);
//        Categoria cat2 = new Categoria("Cumbia", null);
//        mc.agregarCategoriaRaiz(cat2);
//        mc.agregarCategoriaRaiz(cat1);
//        
//        cat1.addSubCategoria(new Categoria("Nacional", cat1));
//        cat1.addSubCategoria(new Categoria("Metal", cat1));
//        cat2.addSubCategoria(new Categoria("Argentina", cat2));
//        cat2.addSubCategoria(new Categoria("Uruguaya", cat2));
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
        
        for (Usuario usu : mu.listarUsuarios()) {
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
        
        for (Usuario usu : mu.listarUsuarios()) {
            if (usu instanceof Proponente) {
                retorno.add(usu.getNickname());
            }
        }
        
        retorno.sort(String.CASE_INSENSITIVE_ORDER); // Ordena la lista
        return retorno;        
    }
    


    @Override
    public ArrayList<String> getNomColaboradores(){
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ArrayList<String> retorno = new ArrayList<>();
        for (Usuario usu : mu.listarUsuarios()) {
            if(usu instanceof Colaborador){
                retorno.add(usu.getNombre());
            }
        }
        retorno.sort(String.CASE_INSENSITIVE_ORDER);
        return retorno;
    }

    @Override
    public DTColaborador getDTColaborador(String nickname) {
        
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
       
        Colaborador c =(Colaborador) mu.buscarUsuario(nickname);
       
        DTColaborador dtc = new DTColaborador(c.getNickname(), c.getNombre(), c.getApellido(), c.getEmail(), c.getFechaNacimiento(), c.getImagen());
        
        // TODO : ARREGLAR ESTO
        
//        for (Propuesta prop : c.getPropuestas()) {
//            dtc.addPropuesta(new DTPropuesta(prop.getTitulo(), prop.getEstadoActual().getEstado() , prop.getProponenteNick(), prop.getMontoRecaudado(), prop.getMontoNecesario()));
//        }
       
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

    @Override
    public void altaCategoria(String nombre, String catPadre) throws CategoriaYaExiste {
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();

        if (mc.buscar(nombre) != null) {
            throw new CategoriaYaExiste("La categoría ya existe");
        }
        if (catPadre == null) {
            mc.alta(new Categoria(nombre, null));
        } else {
            mc.agregarSubcategoria(nombre, catPadre);
        }
    }
    
    @Override
    public DefaultTreeModel listarCategorias() {
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Categorías");
        DefaultTreeModel model = new DefaultTreeModel(raiz);

        List<Categoria> categoriasRaiz = mc.getCategoriasRaizConSubcategorias();
        for (Categoria cat : categoriasRaiz) {
            raiz.add(crearNodo(cat));
        }   
        return model;
    }
    
    private DefaultMutableTreeNode crearNodo(Categoria cat) {
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(cat.getNombre());

        // Inicializamos subcategorías mientras el EntityManager está abierto
        if (cat.getSubCategorias() != null) {
            cat.getSubCategorias().size(); // fuerza carga
            for (Categoria sub : cat.getSubCategorias()) {
                nodo.add(crearNodo(sub));
            }
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
        
        Categoria c = mc.buscar(categoria);
        
        Propuesta p = new Propuesta(titulo,descripcion,lugar,fechaPrevista, precioEntrada, montoNecesario, tipoRetornos, imagen, u,c);
        mp.agregarPropuesta(p);
        u.addPropuestas(p);
    }

    @Override
    public ArrayList<String> getTituloPropuestas(){
        //Retorna todos los titulos de todas las propuestas
       ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
       ArrayList<String> retorno = new ArrayList<>();
       for (Propuesta p : mp.getPropuestas()){
        retorno.add(p.getTitulo());
    }
       retorno.sort(String.CASE_INSENSITIVE_ORDER);
       return retorno;
    }
     
    @Override
    public ArrayList<DTPropuesta> getDTPropuestas(){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ArrayList<DTPropuesta> retorno = new ArrayList<>();
        for(Propuesta p : mp.getPropuestas()){
            DTPropuesta dtp = new DTPropuesta( p.getTitulo(),
                    p.getDescripcion(),
                    p.getLugar(),
                    p.getFechaPrevista(),
                    p.getPrecioEntrada(),
                    p.getMontoNecesario()
            );
            retorno.add(dtp);
        }
        return retorno;
    }

    
    @Override
    public DTPropuesta getDTPropuesta(String titulo){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.buscarPropuesta(titulo);
        String nombreCategoria = "Sin categoría"; 
        DTPropuesta dtp = new DTPropuesta();
        if(p != null){
    if (p.getCategoria() != null) {
        nombreCategoria = p.getCategoria().getNombre();
    }
        dtp = new DTPropuesta(p.getTitulo(), p.getDescripcion(), p.getLugar(), p.getFechaPrevista(), p.getPrecioEntrada(), p.getMontoNecesario(), p.getImagen(), p.getNicknameColaboradores(), p.getProponenteNick(), p.getEstadoActual().getEstado(), nombreCategoria);
        }
        return dtp;
    }
        
    
    @Override
    public ArrayList<String> getTituloPropuestasPorEstado(TipoEstado estado){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ArrayList<String> retorno = new ArrayList<>();
        
        for (Propuesta p : mp.getPropuestas()) {
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
    
    @Override 
    public  ArrayList<String> getNickUsuarios() {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        ArrayList<String> retorno = new ArrayList<>();
        for(Usuario u : mu.listarUsuarios()){
            retorno.add(u.getNickname());
        }
        retorno.sort(String.CASE_INSENSITIVE_ORDER);//Ordena la lista
        return retorno;
    }
    
    @Override 
    public  void seguirUsuario(String nickSeguidor, String nickSeguido) throws UsuarioYaSeguido {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        
        Usuario seguidor = mu.buscarUsuarioConUsuariosSeguidos(nickSeguidor);
        Usuario seguido = mu.buscarUsuario(nickSeguido);
        
        if (seguidor == null || seguido == null) {
            throw new UsuarioYaSeguido("Uno de los usuarios no existe.");
        }
        
        if(seguidor.getUsuariosSeguidos().contains(seguido)){
            throw new UsuarioYaSeguido("El usuario con nickname: " + nickSeguidor + "\nYa está siguiendo a usuario con nickname: " + nickSeguido);
        }
        
        seguidor.addUsuariosSeguidos(seguido);
        mu.actualizarUsuario(seguidor);
    }
    
    @Override 
    public  void dejarDeSeguirUsuario(String nickSeguidor, String nickSeguido) throws UsuarioNoSeguido {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario seguidor = mu.buscarUsuarioConUsuariosSeguidos(nickSeguidor);
        Usuario seguido = mu.buscarUsuario(nickSeguido);
        if(seguidor.getUsuariosSeguidos().contains(seguido)){
            seguidor.getUsuariosSeguidos().remove(seguido);
            mu.actualizarUsuario(seguidor);
        }else{
            throw new UsuarioNoSeguido("El usuario con nickname: " + nickSeguidor + ", no sigue al usuario con nickname: " + nickSeguido);
        }
    }

    @Override
    public DTUsuario getDTUsuario(String nickname) {
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Usuario usu = mu.buscarUsuario(nickname);
        
        ArrayList<String> nickSeguidos = new ArrayList<>();
        for(Usuario u : usu.getUsuariosSeguidos()){
            nickSeguidos.add(u.getNickname());
        }
        
        DTUsuario dtu = new DTUsuario(usu.getNickname(), usu.getNombre(), nickSeguidos);
        
        return dtu;
    }
     
    
        
    
    
    @Override
    public void cancelarColaboracionPropuesta(String tituloPropuesta, String nickColaborador){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.buscarPropuesta(tituloPropuesta);
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Colaborador c = (Colaborador) mu.buscarUsuario(nickColaborador);
        
        for (Colaboracion colab : p.getColaboraciones()) {
            if (colab.getColaborador().equals(c)) {
                p.getColaboraciones().remove(colab);
                c.getColaboraciones().remove(colab);
                break;
            }
        }
    }
    
    
    @Override
    public ArrayList<DTColaboracion> getDTColaboracionesPropuestas(String nickColab){
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Colaborador c = (Colaborador) mu.buscarUsuario(nickColab);
        
        ArrayList<DTColaboracion> ret = new ArrayList<>();
        
        for (Colaboracion colab : c.getColaboraciones()) {
            ret.add(new DTColaboracion(colab.getColaborador().getNickname(),colab.getPropuesta().getTitulo(),colab.getFechaAporte(),colab.getMonto(),colab.getTipoRetorno()));
        }
        
        return ret;
    }
    
    @Override
    public DTColaboracion getDTColaboracionPropuesta(String nickColab, String tituloProp){
        
        for (DTColaboracion colab : this.getDTColaboracionesPropuestas(nickColab)) {
            if (colab.getPropuestaTitulo().equals(tituloProp)) {
                return colab;
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<DTColaboracion> getDTColaboraciones(){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        
        ArrayList<DTColaboracion> ret = new ArrayList<>();
        
        for (Propuesta p : mp.getPropuestas()) {
            if (p.getColaboraciones() != null)
                for(Colaboracion c : p.getColaboraciones()){
                    ret.add(new DTColaboracion(c.getColaborador().getNickname(),c.getPropuesta().getTitulo(),c.getFechaAporte(),c.getMonto(),c.getTipoRetorno()));
                }
        }
        return ret;
    }
    @Override
    public void actualizarTituloProp(String tituloViejo, String tituloNuevo){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.getPropuesta(tituloViejo);
        if (p != null) {
        mp.sacarPropuesta(p);
        p.setTitulo(tituloNuevo);
        mp.agregarPropuesta(p);
        }
    }
    
    @Override 
    public String getNickProponente(String tituloPropuesta){
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        Propuesta p = mp.buscarPropuesta(tituloPropuesta);
        
        return p.getProponente().getNickname();
    }
}

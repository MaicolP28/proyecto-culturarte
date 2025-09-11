package com.culturarte.logica;

import com.culturarte.exepciones.CargaFallida;
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
import java.util.List;
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
       
        Proponente p = mu.getProponenteConPropuestas(nickname);
        
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
        
        if (mp.getPropuesta(titulo) != null) {
            throw new PropuestaYaExiste("Ya existe esta Propuesta");
        }
        
        Proponente u = (Proponente) mu.buscarUsuario(proponente);
        
        Categoria c = mc.buscar(categoria);
        
        Propuesta p = new Propuesta(titulo,descripcion,lugar,fechaPrevista, precioEntrada, montoNecesario, tipoRetornos, imagen, u,c);
        
        mp.agregarPropuesta(p);
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
        Propuesta p = mp.getPropuesta(titulo);
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
        Propuesta p = mp.getPropuesta(tituloPropuesta);
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
        
        Usuario seguidor = mu.buscarUsuario(nickSeguidor);
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
        Usuario seguidor = mu.buscarUsuario(nickSeguidor);
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
//        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
//        Propuesta p = mp.getPropuesta(tituloPropuesta);
//        ManejadorUsuario mu = ManejadorUsuario.getInstance();
//        Colaborador c = (Colaborador) mu.buscarUsuario(nickColaborador);
//        
//        for (Colaboracion colab : p.getColaboraciones()) {
//            if (colab.getColaborador().equals(c)) {
//                p.getColaboraciones().remove(colab);
//                c.getColaboraciones().remove(colab);
//                break;
//            }
//        }
    }
    
    
    @Override
    public ArrayList<DTColaboracion> getDTColaboracionesPropuestas(String nickColab){
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        Colaborador c = (Colaborador) mu.buscarUsuario(nickColab);
        
        ArrayList<DTColaboracion> ret = new ArrayList<>();
        
//        for (Colaboracion colab : c.getColaboraciones()) {
//            ret.add(new DTColaboracion(colab.getColaborador().getNickname(),colab.getPropuesta().getTitulo(),colab.getFechaAporte(),colab.getMonto(),colab.getTipoRetorno()));
//        }
        
        return ret;
    }
    
    @Override
    public DTColaboracion getDTColaboracionPropuesta(String nickColab, String tituloProp){
        
//        for (DTColaboracion colab : this.getDTColaboracionesPropuestas(nickColab)) {
//            if (colab.getPropuestaTitulo().equals(tituloProp)) {
//                return colab;
//            }
//        }
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
        Propuesta p = mp.getPropuesta(tituloPropuesta);
        
        return p.getProponente().getNickname();
    }
    
    public void cargarDatosPrueba() throws CargaFallida{
        ManejadorPropuesta mp = ManejadorPropuesta.getInstancia();
        ManejadorCategoria mc = ManejadorCategoria.getInstancia();
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        System.out.println("Agregando datos de prueba: ...");
        try {
            // Proponentes
            this.altaProponente(
                    "hrubino", "Horacio", "Rubino",
                    "horacio.rubino@guambia.com.uy", LocalDate.of(1962, 2, 25),
                    new File("imagenes/hr.png"),
                    "18 de Julio 1234",
                    "https://twitter.com/horaciorubino",
                    "Actor y conductor"
            );
            this.altaProponente(
                    "mbusca", "Martín", "Buscaglia",
                    "martin.bus@agadu.org.uy", LocalDate.of(1972, 6, 14),
                    new File("imagenes/mb.png"),
                    "Colonia 4321",
                    "http://www.martinbuscaglia.com/",
                    "Músico uruguayo"
            );
            this.altaProponente(
                    "hectorg", "Héctor", "Guido",
                    "hector.gui@elgalpon.org.uy", LocalDate.of(1954, 1, 7),
                    new File("imagenes/hg.png"),
                    "Gral. Flores 5645",
                    "",
                    "Actor de teatro"
            );
            this.altaProponente(
                    "tabarec", "Tabaré", "Cardozo",
                    "tabare.car@agadu.org.uy", LocalDate.of(1971, 7, 24),
                    new File("imagenes/tc.png"),
                    "Santiago Rivas 1212",
                    "https://www.facebook.com/Tabaré-Cardozo-55179094281/?ref=br_rs",
                    "Cantante murguista"
            );
            this.altaProponente(
                    "cachilas", "Waldemar \"Cachila\"", "Silva",
                    "cachila.sil@c1080.org.uy", LocalDate.of(1947, 1, 1),
                    new File("imagenes/cs.png"),
                    "Br. Artigas 4567",
                    "https://www.facebook.com/C1080?ref=br_rs",
                    "Director comparsa"
            );
            this.altaProponente(
                    "juliob", "Julio", "Bocca",
                    "juliobocca@sodre.com.uy", LocalDate.of(1967, 3, 16),
                    new File("imagenes/jb.png"),
                    "Benito Blanco 4321",
                    "",
                    "Bailarín"
            );
            this.altaProponente(
                    "diegop", "Diego", "Parodi",
                    "diego@efectocine.com", LocalDate.of(1975, 1, 1),
                    new File("imagenes/dp.png"),
                    "Emilio Frugoni 1138 Ap. 02",
                    "http://www.efectocine.com",
                    "Cineasta"
            );
            this.altaProponente(
                    "kairoh", "Kairo", "Herrera",
                    "kairoher@pilsenrock.com.uy", LocalDate.of(1840, 4, 25),
                    new File("imagenes/kh.png"),
                    "Paraguay 1423",
                    "",
                    "Organizador eventos"
            );
            this.altaProponente(
                    "losBardo", "Los", "Bardo",
                    "losbardo@bardocientifico.com", LocalDate.of(1980, 10, 31),
                    new File("imagenes/lb.png"),
                    "8 de Octubre 1429",
                    "https://bardocientifico.com/",
                    "Divulgación científica"
            );
                // Colaboradores
            this.altaColaborador(
                    "robinh", "Robin", "Henderson",
                    "robin.h@tinglesa.com.uy", LocalDate.of(1940, 8, 3),
                    new File("imagenes/rh.png")
            );
            this.altaColaborador(
                    "marcelot", "Marcelo", "Tinelli",
                    "marcelot@ideasdelsur.com.ar", LocalDate.of(1960, 4, 1),
                    new File("imagenes/mt.png")
            );
            this.altaColaborador(
                    "novick", "Edgardo", "Novick",
                    "edgardo@novick.com.uy", LocalDate.of(1952, 7, 17),
                    new File("imagenes/en.png")
            );
            this.altaColaborador(
                    "sergiop", "Sergio", "Puglia",
                    "puglia@alpanpan.com.uy", LocalDate.of(1950, 1, 28),
                    new File("imagenes/sp.png")
            );
            this.altaColaborador(
                    "chino", "Alvaro", "Recoba",
                    "chino@trico.org.uy", LocalDate.of(1976, 3, 17),
                    null
            );
            this.altaColaborador(
                    "tonyp", "Antonio", "Pacheco",
                    "tonyp@manya.org.uy", LocalDate.of(1955, 2, 14),
                    new File("imagenes/ap.png")
            );
            this.altaColaborador(
                    "nicoJ", "Nicolás", "Jodal",
                    "jodal@artech.com.uy", LocalDate.of(1960, 8, 9),
                    new File("imagenes/nj.png")
            );
            this.altaColaborador(
                    "juanP", "Juan", "Perez",
                    "juanp@elpueblo.com", LocalDate.of(1970, 1, 1),
                    null
            );
            this.altaColaborador(
                    "Mengano", "Mengano", "Gómez",
                    "menganog@elpueblo.com", LocalDate.of(1982, 2, 2),
                    null
            );
            this.altaColaborador(
                    "Perengano", "Perengano", "López",
                    "pere@elpueblo.com", LocalDate.of(1985, 3, 3),
                    null
            );
            this.altaColaborador(
                    "Tiajaci", "Tía", "Jacinta",
                    "jacinta@elpueblo.com", LocalDate.of(1990, 4, 4),
                    null
            );
            
            // HR sigue a: MB, HG, TC
            this.seguirUsuario("hrubino", "mbusca");
            this.seguirUsuario("hrubino", "hectorg");
            this.seguirUsuario("hrubino", "tabarec");

            // MB sigue a: HR, HG, TC
            
            this.seguirUsuario("mbusca", "hrubino");
            this.seguirUsuario("mbusca", "hectorg");
            this.seguirUsuario("mbusca", "tabarec");

            // HG sigue a: HR, TC
            this.seguirUsuario("hectorg", "hrubino");
            this.seguirUsuario("hectorg", "tabarec");

            // TC sigue a: HR, HG
            this.seguirUsuario("tabarec", "hrubino");
            this.seguirUsuario("tabarec", "hectorg");

            // CS sigue a: HR
            this.seguirUsuario("cachilas", "hrubino");

            // JB sigue a: HR, TC
            this.seguirUsuario("juliob", "hrubino");
            this.seguirUsuario("juliob", "tabarec");

            // DP sigue a: HR, TC
            this.seguirUsuario("diegop", "hrubino");
            this.seguirUsuario("diegop", "tabarec");

            // KH sigue a: HR
            this.seguirUsuario("kairoh", "hrubino");

            // LB sigue a: HR, TC
            this.seguirUsuario("losBardo", "hrubino");
            this.seguirUsuario("losBardo", "tabarec");

            // RH sigue a: HR, MB, HG
            this.seguirUsuario("robinh", "hrubino");
            this.seguirUsuario("robinh", "mbusca");
            this.seguirUsuario("robinh", "hectorg");

            // MT sigue a: HR, MB, HG
            this.seguirUsuario("marcelot", "hrubino");
            this.seguirUsuario("marcelot", "mbusca");
            this.seguirUsuario("marcelot", "hectorg");

            // EN sigue a: HR, MB, HG
            this.seguirUsuario("novick", "hrubino");
            this.seguirUsuario("novick", "mbusca");
            this.seguirUsuario("novick", "hectorg");

            // SP sigue a: HR, MB, HG
            this.seguirUsuario("sergiop", "hrubino");
            this.seguirUsuario("sergiop", "mbusca");
            this.seguirUsuario("sergiop", "hectorg");

            // AR sigue a: HR
            this.seguirUsuario("chino", "hrubino");

            // AP sigue a: HR
            this.seguirUsuario("tonyp", "hrubino");

            // NJ sigue a: HR, MB
            this.seguirUsuario("nicoJ", "hrubino");
            this.seguirUsuario("nicoJ", "mbusca");

            // JP sigue a: HR, MB, HG
            this.seguirUsuario("juanP", "hrubino");
            this.seguirUsuario("juanP", "mbusca");
            this.seguirUsuario("juanP", "hectorg");

            // MG sigue a: HR, MB, HG
            this.seguirUsuario("Mengano", "hrubino");
            this.seguirUsuario("Mengano", "mbusca");
            this.seguirUsuario("Mengano", "hectorg");

            // PL sigue a: HR, MB
            this.seguirUsuario("Perengano", "hrubino");
            this.seguirUsuario("Perengano", "mbusca");

            // TJ sigue a: HR, MB, HG
            this.seguirUsuario("Tiajaci", "hrubino");
            this.seguirUsuario("Tiajaci", "mbusca");
            this.seguirUsuario("Tiajaci", "hectorg");

            
            // Categorias
            this.altaCategoria("Teatro", null);
            this.altaCategoria("Literatura", null);
            this.altaCategoria("Música", null);
            this.altaCategoria("Cine", null);
            this.altaCategoria("Danza", null);
            this.altaCategoria("Carnaval", null);
            
            // Subcategorías de Teatro
            this.altaCategoria("Teatro Dramático", "Teatro");
            this.altaCategoria("Teatro Musical", "Teatro");
            this.altaCategoria("Comedia", "Teatro");
            this.altaCategoria("Stand-up", "Comedia");
            
            // Subcategorías de Música
            this.altaCategoria("Festival", "Música");
            this.altaCategoria("Concierto", "Música");
            
            // Subcategorías de Cine
            this.altaCategoria("Cine al Aire Libre", "Cine");
            this.altaCategoria("Cine a Pedal", "Cine");
            
            // Subcategorías de Danza
            this.altaCategoria("Ballet", "Danza");
            this.altaCategoria("Flamenco", "Danza");
            
            // Subcategorías de Carnaval
            this.altaCategoria("Murga", "Carnaval");
            this.altaCategoria("Humoristas", "Carnaval");
            this.altaCategoria("Parodistas", "Carnaval");
            this.altaCategoria("Lubolos", "Carnaval");
            this.altaCategoria("Revista", "Carnaval");
            
            // Propuestas
            this.altaPropuesta(
                    "Cine en el Botánico",
                    "El 16 de Diciembre a la hora 20 se proyectará la película \"Clever\", en el Jardín Botánico (Av. 19 de Abril 1181) en el marco de las actividades realizadas por el ciclo Cultura al Aire Libre. El largometraje uruguayo de ficción Clever es dirigido por Federico Borgia y Guillermo Madeiro. Es apto para mayores de 15 años.",
                    "Jardín Botánico",
                    LocalDate.of(2017, 9, 16),
                    (float)200, (float)150000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    null,
                    "diegop", "Cine"
            );
            
            this.altaPropuesta(
                    "Religiosamente",
                    "MOMOSAPIENS presenta \"Religiosamente\". Mediante dos parodias y un hilo conductor que aborda la temática de la religión Momosapiens, mediante el humor y la reflexión, hilvana una historia que muestra al hombre inmerso en el tema religioso. El libreto está escrito utilizando diferentes lenguajes de humor, dando una visión satírica y reflexiva desde distintos puntos de vista, logrando mediante situaciones paródicas armar una propuesta plena de arte carnavalero.",
                    "Teatro de Verano",
                    LocalDate.of(2017, 10, 7),
                    (float)300, (float)300000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/mom.png"),
                    "hrubino", "Parodistas"
            );
            
            this.altaPropuesta(
                    "El Pimiento Indomable",
                    "El Pimiento Indomable, formación compuesta por Kiko Veneno y el uruguayo Martín Buscaglia, presentará este 19 de Octubre, su primer trabajo. Bajo un título homónimo al del grupo, es un disco que según los propios protagonistas \"no se parece al de ninguno de los dos por separado. Entre los títulos que se podrán escuchar se encuentran \"Nadador salvador\", \"América es más grande\", \"Pescaito Enroscado\" o \"La reina del placer\".",
                    "Teatro Solís",
                    LocalDate.of(2017, 10, 19),
                    (float)400, (float)400000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/pim.png"),
                    "mbusca", "Concierto"
            );
            
            this.altaPropuesta(
                    "Pilsen Rock",
                    "La edición 2017 del Pilsen Rock se celebrará el 21 de Octubre en la Rural del Prado y contará con la participación de más de 15 bandas nacionales. Quienes no puedan trasladarse al lugar, tendrán la posibilidad de disfrutar los shows a través de Internet, así como entrevistas en vivo a los músicos una vez finalizados los conciertos.",
                    "Rural de Prado",
                    LocalDate.of(2017, 10, 21),
                    (float)1000, (float)900000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/pil.png"),
                    "kairoh", "Festival"
            );
            
            this.altaPropuesta(
                    "Romeo y Julieta",
                    "Romeo y Julieta de Kenneth MacMillan, uno de los ballets favoritos del director artístico Julio Bocca, se presentará nuevamente el 5 de Noviembre en el Auditorio Nacional del Sodre. Basada en la obra homónima de William Shakespeare, Romeo y Julieta es considerada la coreografía maestra del MacMillan. La producción de vestuario y escenografía se realizó en los Talleres del Auditorio Adela Reta, sobre los diseños originales.",
                    "Auditorio Nacional del Sodre",
                    LocalDate.of(2017, 11, 5),
                    (float)800, (float)750000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/ryj.png"),
                    "juliob", "Ballet"
            );
            
            this.altaPropuesta(
                    "Un día de Julio",
                    "La Catalina presenta el espectáculo \"Un Día de Julio\" en Landia. Un hombre misterioso y solitario vive encerrado entre las cuatro paredes de su casa. Intenta, con sus teorías extravagantes, cambiar el mundo exterior que le resulta inhabitable. Un día de Julio sucederá algo que cambiará su vida y la de su entorno para siempre.",
                    "Landia",
                    LocalDate.of(2017, 11, 16),
                    (float)650, (float)300000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/udj.png"),
                    "tabarec", "Murga"
            );
            
            this.altaPropuesta(
                    "El Lazarillo de Tormes",
                    "Vuelve unas de las producciones de El Galpón más aclamadas de los últimos tiempos. Esta obra se ha presentado en Miami, Nueva York, Washington, México, Guadalajara, Río de Janeiro y La Habana. En nuestro país, El Lazarillo de Tormes fue nominado en los rubros mejor espectáculo y mejor dirección a los Premios Florencio 1995, obteniendo su protagonista Héctor Guido el Florencio a Mejor actor de ese año.",
                    "Teatro el Galpón",
                    LocalDate.of(2017, 12, 3),
                    (float)350, (float)175000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS),
                    null,
                    "hectorg", "Teatro Dramático"
            );
            
            this.altaPropuesta(
                    "Bardo en la FING",
                    "El 10 de Diciembre se presentará Bardo Científico en la FING. El humor puede ser usado como una herramienta importante para el aprendizaje y la democratización de la ciencia, los monólogos científicos son una forma didáctica de apropiación del conocimiento científico y contribuyen a que el público aprenda ciencia de forma amena. Los invitamos a pasar un rato divertido, en un espacio en el cual aprenderán cosas de la ciencia que los sorprenderán. ¡Los esperamos!",
                    "Anfiteatro Edificio \"José Luis Massera\"",
                    LocalDate.of(2017, 12, 10),
                    (float)200, (float)100000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS),
                    null,
                    "losBardo", "Stand-up"
            );
            
            
//            Colaboracion col01 = new Colaboracion(50000, LocalDate.of(2017, 5, 20), TipoRetorno.PORCENTAJEGANANCIA, ceb, en);
//            Colaboracion col02 = new Colaboracion(50000, LocalDate.of(2017, 5, 24), TipoRetorno.PORCENTAJEGANANCIA, ceb, rh);
//            Colaboracion col03 = new Colaboracion(50000, LocalDate.of(2017, 5, 30), TipoRetorno.PORCENTAJEGANANCIA, ceb, nj);
//            Colaboracion col04 = new Colaboracion(200000, LocalDate.of(2017, 6, 30), TipoRetorno.PORCENTAJEGANANCIA, mom, mt);
//            Colaboracion col05 = new Colaboracion(500, LocalDate.of(2017, 7, 1), TipoRetorno.ENTRADAGRATIS, mom, tj);
//            Colaboracion col06 = new Colaboracion(600, LocalDate.of(2017, 7, 7), TipoRetorno.ENTRADAGRATIS, mom, mg);
//            Colaboracion col07 = new Colaboracion(50000, LocalDate.of(2017, 7, 10), TipoRetorno.PORCENTAJEGANANCIA, mom, en);
//            Colaboracion col08 = new Colaboracion(50000, LocalDate.of(2017, 7, 15), TipoRetorno.PORCENTAJEGANANCIA, mom, sp);
//            Colaboracion col09 = new Colaboracion(200000, LocalDate.of(2017, 8, 1), TipoRetorno.PORCENTAJEGANANCIA, pim, mt);
//            Colaboracion col10 = new Colaboracion(80000, LocalDate.of(2017, 8, 3), TipoRetorno.PORCENTAJEGANANCIA, pim, sp);
//            Colaboracion col11 = new Colaboracion(50000, LocalDate.of(2017, 8, 5), TipoRetorno.ENTRADAGRATIS, pil, ar);
//            Colaboracion col12 = new Colaboracion(120000, LocalDate.of(2017, 8, 10), TipoRetorno.PORCENTAJEGANANCIA, pil, en);
//            Colaboracion col13 = new Colaboracion(120000, LocalDate.of(2017, 8, 15), TipoRetorno.ENTRADAGRATIS, pil, ap);
//            Colaboracion col14 = new Colaboracion(100000, LocalDate.of(2017, 8, 13), TipoRetorno.PORCENTAJEGANANCIA, ryj, sp);
//            Colaboracion col15 = new Colaboracion(200000, LocalDate.of(2017, 8, 14), TipoRetorno.PORCENTAJEGANANCIA, ryj, mt);
//            Colaboracion col16 = new Colaboracion(30000, LocalDate.of(2017, 8, 15), TipoRetorno.ENTRADAGRATIS, udj, ap);
//            Colaboracion col17 = new Colaboracion(150000, LocalDate.of(2017, 8, 17), TipoRetorno.PORCENTAJEGANANCIA, udj, mt);
//
            
            System.out.println("Datos de prueba cargados exitosamente.");
        } catch (Exception e) {
            throw new CargaFallida("Error al cargar datos de prueba: " + e);
        }
    }
}

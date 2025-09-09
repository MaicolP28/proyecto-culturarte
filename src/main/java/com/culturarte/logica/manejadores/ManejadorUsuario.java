package com.culturarte.logica.manejadores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.culturarte.logica.clases.Usuario;
import java.util.List;
import jakarta.persistence.TypedQuery;


public class ManejadorUsuario {
    private static ManejadorUsuario instancia = null;
    private EntityManagerFactory emf;
    
    private ManejadorUsuario() {
        emf = Persistence.createEntityManagerFactory("DBculturarte"); ;
    }

    public static ManejadorUsuario getInstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Usuario buscarUsuario(String nick) {
        EntityManager em = emf.createEntityManager();
        try {
            Usuario u = em.find(Usuario.class, nick);
            if (u == null) 
                return null;
            u.getUsuariosSeguidos().size();
            return u;
        } finally {
            em.close();
        }
    }
    
    public Usuario buscarUsuarioConUsuariosSeguidos(String nick) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT u FROM Usuario u LEFT JOIN FETCH u.usuariosSeguidos WHERE u.nickname = :nick", 
                Usuario.class
            )
            .setParameter("nick", nick)
            .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Usuario> listarUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}



   

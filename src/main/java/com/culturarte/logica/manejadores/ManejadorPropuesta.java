package com.culturarte.logica.manejadores;

import com.culturarte.logica.clases.Propuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ManejadorPropuesta {

    private static ManejadorPropuesta instancia = null;
    private EntityManagerFactory emf;

    private ManejadorPropuesta() {
        emf = Persistence.createEntityManagerFactory("DBculturarte");
    }

    // Singleton thread-safe
    public static synchronized ManejadorPropuesta getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPropuesta();
        }
        return instancia;
    }

    // Agregar propuesta
    public void agregarPropuesta(Propuesta propuesta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(propuesta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Buscar propuesta por título
    public Propuesta getPropuesta(String titulo) {
        if (titulo == null) return null;

        EntityManager em = emf.createEntityManager();
        try {
            Propuesta p = em.find(Propuesta.class, titulo);
            if (p != null) {

                p.getColaboraciones().size();  

            }
            return p;
        } finally {
            em.close();
        }
    }

    // Listar todas las propuestas
    public List<Propuesta> getPropuestas() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Propuesta> query = em.createQuery("SELECT p FROM Propuesta p", Propuesta.class);
            List<Propuesta> propuestas = query.getResultList();
            propuestas.forEach(this::forzarCargaLazy);
            return propuestas;
        } finally {
            em.close();
        }
    }

    // Eliminar propuesta
    public void sacarPropuesta(Propuesta propuesta) {
        if (propuesta == null || propuesta.getTitulo() == null) return;

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Propuesta p = em.find(Propuesta.class, propuesta.getTitulo());
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    private void forzarCargaLazy(Propuesta propuesta) {
        if (propuesta == null) return;
        
        if (propuesta.getColaboraciones() != null)
            propuesta.getColaboraciones().size();

        if (propuesta.getHistorialEstados() != null)
            propuesta.getHistorialEstados().size();

        if (propuesta.getEstadoActual() != null)
            propuesta.getEstadoActual().getId();

        if (propuesta.getProponente() != null)
            propuesta.getProponente().getNickname();

        if (propuesta.getCategoria() != null)
            propuesta.getCategoria().getNombre();
    }
    
}


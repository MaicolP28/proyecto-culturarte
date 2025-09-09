package com.culturarte.logica.manejadores;

import com.culturarte.logica.clases.Propuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ManejadorPropuesta {
    private  static ManejadorPropuesta instancia = null;
    private EntityManagerFactory emf;

    private ManejadorPropuesta(){
        emf = Persistence.createEntityManagerFactory("BaseDeDatos");
    }

    public static ManejadorPropuesta getInstancia(){
        if(instancia == null){
            instancia = new ManejadorPropuesta();
        }
        return instancia;
    }

    public void agregarPropuesta(Propuesta propuesta){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(propuesta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Propuesta getPropuesta(String titulo){
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Propuesta.class, titulo);
        } finally {
            em.close();
        }
    }
    
    public Propuesta buscarPropuesta(String titulo) {
        return getPropuesta(titulo);
    }
    
    public List<Propuesta> getPropuestas() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Propuesta> query = em.createQuery("SELECT p FROM Propuesta p", Propuesta.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
    public void sacarPropuesta(Propuesta propuesta){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Propuesta p = em.find(Propuesta.class, propuesta.getTitulo());
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}


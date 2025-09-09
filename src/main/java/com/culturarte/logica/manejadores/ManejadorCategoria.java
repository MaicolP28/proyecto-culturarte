package com.culturarte.logica.manejadores;

import com.culturarte.logica.clases.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ManejadorCategoria {

    private static ManejadorCategoria instancia = null;
    private EntityManagerFactory emf;
    
    private ManejadorCategoria(){
        emf = Persistence.createEntityManagerFactory("BaseDeDatos");
    }
    
    public static ManejadorCategoria getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorCategoria();
        }
        return instancia;
    }
    
    public void agregarCategoriaRaiz(Categoria categoria) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<Categoria> getCategoriasRaiz() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Categoria> query = em.createQuery(
                "SELECT c FROM Categoria c WHERE c.padre IS NULL", Categoria.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Categoria buscarCategoria(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Categoria.class, nombre);
        } finally {
            em.close();
        }
    }
    
    private Categoria buscarRecursivo(Categoria cat, String nombre) {
        if (cat.getNombre().equalsIgnoreCase(nombre)) {
            return cat;
        }
        for (Categoria sub : cat.getSubCategorias()) {
            Categoria encontrada = buscarRecursivo(sub, nombre);
            if (encontrada != null) return encontrada;
        }
        return null;
    }
    
}



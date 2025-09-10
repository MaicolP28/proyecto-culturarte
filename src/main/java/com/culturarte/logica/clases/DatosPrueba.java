/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.clases;
import com.culturarte.logica.enums.TipoRetorno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.File;
import java.util.EnumSet;
/**
 *
 * @author fabriciorivero
 */

public class DatosPrueba {

    public static void cargar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CulturartePU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            Proponente hr = new Proponente(
                    "hrubino", "Horacio", "Rubino",
                    "horacio.rubino@guambia.com.uy", LocalDate.of(1962, 2, 25),new File("imagenes/hr.png"),
                    "18 de Julio 1234",
                    "https://twitter.com/horaciorubino",
                    "Horacio Rubino Torres nace el 25 de febrero de 1962..."
            );
            Proponente mb = new Proponente(
                    "mbusca", "Martín", "Buscaglia",
                    "martin.bus@agadu.org.uy", LocalDate.of(1972, 6, 14),
                    new File("imagenes/mb.png"),
                    "Colonia 4321",
                    "http://www.martinbuscaglia.com/",
                    "Martín Buscaglia (Montevideo, 1972) es un artista, músico..."
            );
            Colaborador rh = new Colaborador(
                    "robinh", "Robin", "Henderson",
                    "robin.h@tinglesa.com.uy", LocalDate.of(1940, 8, 3),
                    new File("imagenes/rh.png")
            );

            em.persist(hr);
            em.persist(mb);
            em.persist(rh);


            Categoria teatro = new Categoria("Teatro", null);
            Categoria parodistas = new Categoria("Parodistas", teatro);
            Categoria cine = new Categoria("Cine", null);
            Categoria cineAL = new Categoria("Cine al Aire Libre", cine);

            em.persist(teatro);
            em.persist(parodistas);
            em.persist(cine);
            em.persist(cineAL);


            Propuesta ceb = new Propuesta(
                    "Cine en el Botánico",
                    "El 16 de diciembre se proyectará la película Clever en el Jardín Botánico...",
                    "Jardín Botánico",
                    LocalDate.of(2017, 9, 16),
                    200, 150000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/ceb.png"),
                    hr, cineAL
            );


            em.persist(ceb);


            
            Colaboracion col1 = new Colaboracion(
                   //ceb, 50000,LocalDate.now(),TipoRetorno.PORCENTAJEGANANCIA,rh        
            );

            em.persist(col1);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culturarte.logica.clases;
import com.culturarte.logica.enums.TipoRetorno;
import com.culturarte.logica.enums.TipoEstado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
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
                    "horacio.rubino@guambia.com.uy", LocalDate.of(1962, 2, 25),
                    new File("imagenes/hr.png"),
                    "18 de Julio 1234",
                    "https://twitter.com/horaciorubino",
                    "Horacio Rubino Torres nace el 25 de febrero de 1962, es conductor, actor y libretista. Debuta en 1982 en carnaval en Los \"Klaper's\", donde estuvo cuatro años, actuando y libretando. Luego para \"Gaby's\" (6 años), escribió en categoría revistas y humoristas y desde el comienzo y hasta el presente en su propio conjunto Momosapiens."
            );
            
            Proponente mb = new Proponente(
                    "mbusca", "Martín", "Buscaglia",
                    "martin.bus@agadu.org.uy", LocalDate.of(1972, 6, 14),
                    new File("imagenes/mb.png"),
                    "Colonia 4321",
                    "http://www.martinbuscaglia.com/",
                    "Martín Buscaglia (Montevideo, 1972) es un artista, músico, compositor y productor uruguayo. Tanto con su banda (\"Los Bochamakers\") como en su formato \"Hombre orquesta\", o solo con su guitarra, ha recorrido el mundo tocando entre otros países en España, Estados Unidos, Inglaterra, Francia, Australia, Brasil, Colombia, Argentina, Chile, Paraguay, México y Uruguay."
            );
            
            Proponente hg = new Proponente(
                    "hectorg", "Héctor", "Guido",
                    "hector.gui@elgalpon.org.uy", LocalDate.of(1954, 1, 7),
                    new File("imagenes/hg.png"),
                    "Gral. Flores 5645",
                    "",
                    "En 1972 ingresó a la Escuela de Arte Dramático del teatro El Galpón. Participó en más de treinta obras teatrales y varios largometrajes. Integró el elenco estable de Radioteatro del Sodre, y en 2006 fue asesor de su Consejo Directivo. Como actor recibió múltiples reconocimientos: cuatro premios Florencio, premio al mejor actor extranjero del Festival de Miami y premio Mejor Actor de Cine 2008."
            );
            
            Proponente tc = new Proponente(
                    "tabarec", "Tabaré", "Cardozo",
                    "tabare.car@agadu.org.uy", LocalDate.of(1971, 7, 24),
                    new File("imagenes/tc.png"),
                    "Santiago Rivas 1212",
                    "https://www.facebook.com/Tabaré-Cardozo-55179094281/?ref=br_rs",
                    "Tabaré Cardozo (Montevideo, 24 de julio de 1971) es un cantante, compositor y murguista uruguayo; conocido por su participación en la murga Agarrate Catalina, conjunto que fundó junto a su hermano Yamandú y Carlos Tanco en el año 2001."
            );
            
            Proponente cs = new Proponente(
                    "cachilas", "Waldemar \"Cachila\"", "Silva",
                    "cachila.sil@c1080.org.uy", LocalDate.of(1947, 1, 1),
                    new File("imagenes/cs.png"),
                    "Br. Artigas 4567",
                    "https://www.facebook.com/C1080?ref=br_rs",
                    "Nace en el año 1947 en el conventillo \"Medio Mundo\" ubicado en pleno Barrio Sur. Es heredero parcialmente-junto al resto de sus hermanos- de la Comparsa \"Morenada\" (inactiva desde el fallecimiento de Juan Ángel Silva), en 1999 forma su propia Comparsa de negros y lubolos \"Cuareim 1080\". Director responsable, compositor y cantante de la misma."
            );
            
            Proponente jb = new Proponente(
                    "juliob", "Julio", "Bocca",
                    "juliobocca@sodre.com.uy", LocalDate.of(1967, 3, 16),
                    new File("imagenes/jb.png"),
                    "Benito Blanco 4321",
                    "",
                    ""
            );
            
            Proponente dp = new Proponente(
                    "diegop", "Diego", "Parodi",
                    "diego@efectocine.com", LocalDate.of(1975, 1, 1),
                    new File("imagenes/dp.png"),
                    "Emilio Frugoni 1138 Ap. 02",
                    "http://www.efectocine.com",
                    ""
            );
            
            Proponente kh = new Proponente(
                    "kairoh", "Kairo", "Herrera",
                    "kairoher@pilsenrock.com.uy", LocalDate.of(1840, 4, 25),
                    new File("imagenes/kh.png"),
                    "Paraguay 1423",
                    "",
                    ""
            );
            
            Proponente lb = new Proponente(
                    "losBardo", "Los", "Bardo",
                    "losbardo@bardocientifico.com", LocalDate.of(1980, 10, 31),
                    new File("imagenes/lb.png"),
                    "8 de Octubre 1429",
                    "https://bardocientifico.com/",
                    "Queremos ser vistos y reconocidos como una organización: referente en divulgación científica con un fuerte espíritu didáctico y divertido, a través de acciones coordinadas con otros divulgadores científicos, que permitan establecer puentes de comunicación. Impulsora en la generación de espacios de democratización y apropiación social del conocimiento científico."
            );

            Colaborador rh = new Colaborador(
                    "robinh", "Robin", "Henderson",
                    "robin.h@tinglesa.com.uy", LocalDate.of(1940, 8, 3),
                    new File("imagenes/rh.png")
            );
            
            Colaborador mt = new Colaborador(
                    "marcelot", "Marcelo", "Tinelli",
                    "marcelot@ideasdelsur.com.ar", LocalDate.of(1960, 4, 1),
                    new File("imagenes/mt.png")
            );
            
            Colaborador en = new Colaborador(
                    "novick", "Edgardo", "Novick",
                    "edgardo@novick.com.uy", LocalDate.of(1952, 7, 17),
                    new File("imagenes/en.png")
            );
            
            Colaborador sp = new Colaborador(
                    "sergiop", "Sergio", "Puglia",
                    "puglia@alpanpan.com.uy", LocalDate.of(1950, 1, 28),
                    new File("imagenes/sp.png")
            );
            
            Colaborador ar = new Colaborador(
                    "chino", "Alvaro", "Recoba",
                    "chino@trico.org.uy", LocalDate.of(1976, 3, 17),
                    null
            );
            
            Colaborador ap = new Colaborador(
                    "tonyp", "Antonio", "Pacheco",
                    "tonyp@manya.org.uy", LocalDate.of(1955, 2, 14),
                    new File("imagenes/ap.png")
            );
            
            Colaborador nj = new Colaborador(
                    "nicoJ", "Nicolás", "Jodal",
                    "jodal@artech.com.uy", LocalDate.of(1960, 8, 9),
                    new File("imagenes/nj.png")
            );
            
            Colaborador jp = new Colaborador(
                    "juanP", "Juan", "Perez",
                    "juanp@elpueblo.com", LocalDate.of(1970, 1, 1),
                    null
            );
            
            Colaborador mg = new Colaborador(
                    "Mengano", "Mengano", "Gómez",
                    "menganog@elpueblo.com", LocalDate.of(1982, 2, 2),
                    null
            );
            
            Colaborador pl = new Colaborador(
                    "Perengano", "Perengano", "López",
                    "pere@elpueblo.com", LocalDate.of(1985, 3, 3),
                    null
            );
            
            Colaborador tj = new Colaborador(
                    "Tiajaci", "Tía", "Jacinta",
                    "jacinta@elpueblo.com", LocalDate.of(1990, 4, 4),
                    null
            );

            // Persistir usuarios
            em.persist(hr); em.persist(mb); em.persist(hg); em.persist(tc);
            em.persist(cs); em.persist(jb); em.persist(dp); em.persist(kh); em.persist(lb);
            em.persist(rh); em.persist(mt); em.persist(en); em.persist(sp);
            em.persist(ar); em.persist(ap); em.persist(nj); em.persist(jp);
            em.persist(mg); em.persist(pl); em.persist(tj);

            // Categorías raíz
            Categoria teatro = new Categoria("Teatro", null);
            Categoria literatura = new Categoria("Literatura", null);
            Categoria musica = new Categoria("Música", null);
            Categoria cine = new Categoria("Cine", null);
            Categoria danza = new Categoria("Danza", null);
            Categoria carnaval = new Categoria("Carnaval", null);
            
            // Subcategorías de Teatro
            Categoria teatroDramatico = new Categoria("Teatro Dramático", teatro);
            Categoria teatroMusical = new Categoria("Teatro Musical", teatro);
            Categoria comedia = new Categoria("Comedia", teatro);
            Categoria standup = new Categoria("Stand-up", comedia);
            
            // Subcategorías de Música
            Categoria festival = new Categoria("Festival", musica);
            Categoria concierto = new Categoria("Concierto", musica);
            
            // Subcategorías de Cine
            Categoria cineAL = new Categoria("Cine al Aire Libre", cine);
            Categoria cinePedal = new Categoria("Cine a Pedal", cine);
            
            // Subcategorías de Danza
            Categoria ballet = new Categoria("Ballet", danza);
            Categoria flamenco = new Categoria("Flamenco", danza);
            
            // Subcategorías de Carnaval
            Categoria murga = new Categoria("Murga", carnaval);
            Categoria humoristas = new Categoria("Humoristas", carnaval);
            Categoria parodistas = new Categoria("Parodistas", carnaval);
            Categoria lubolos = new Categoria("Lubolos", carnaval);
            Categoria revista = new Categoria("Revista", carnaval);

            // Persistir categorías
            em.persist(teatro); em.persist(literatura); em.persist(musica);
            em.persist(cine); em.persist(danza); em.persist(carnaval);
            em.persist(teatroDramatico); em.persist(teatroMusical); em.persist(comedia); em.persist(standup);
            em.persist(festival); em.persist(concierto);
            em.persist(cineAL); em.persist(cinePedal);
            em.persist(ballet); em.persist(flamenco);
            em.persist(murga); em.persist(humoristas); em.persist(parodistas);
            em.persist(lubolos); em.persist(revista);

            Propuesta ceb = new Propuesta(
                    "Cine en el Botánico",
                    "El 16 de Diciembre a la hora 20 se proyectará la película \"Clever\", en el Jardín Botánico (Av. 19 de Abril 1181) en el marco de las actividades realizadas por el ciclo Cultura al Aire Libre. El largometraje uruguayo de ficción Clever es dirigido por Federico Borgia y Guillermo Madeiro. Es apto para mayores de 15 años.",
                    "Jardín Botánico",
                    LocalDate.of(2017, 9, 16),
                    200, 150000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    null,
                    dp, cineAL
            );
            
            Propuesta mom = new Propuesta(
                    "Religiosamente",
                    "MOMOSAPIENS presenta \"Religiosamente\". Mediante dos parodias y un hilo conductor que aborda la temática de la religión Momosapiens, mediante el humor y la reflexión, hilvana una historia que muestra al hombre inmerso en el tema religioso. El libreto está escrito utilizando diferentes lenguajes de humor, dando una visión satírica y reflexiva desde distintos puntos de vista, logrando mediante situaciones paródicas armar una propuesta plena de arte carnavalero.",
                    "Teatro de Verano",
                    LocalDate.of(2017, 10, 7),
                    300, 300000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/mom.png"),
                    hr, parodistas
            );
            
            Propuesta pim = new Propuesta(
                    "El Pimiento Indomable",
                    "El Pimiento Indomable, formación compuesta por Kiko Veneno y el uruguayo Martín Buscaglia, presentará este 19 de Octubre, su primer trabajo. Bajo un título homónimo al del grupo, es un disco que según los propios protagonistas \"no se parece al de ninguno de los dos por separado. Entre los títulos que se podrán escuchar se encuentran \"Nadador salvador\", \"América es más grande\", \"Pescaito Enroscado\" o \"La reina del placer\".",
                    "Teatro Solís",
                    LocalDate.of(2017, 10, 19),
                    400, 400000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/pim.png"),
                    mb, concierto
            );
            
            Propuesta pil = new Propuesta(
                    "Pilsen Rock",
                    "La edición 2017 del Pilsen Rock se celebrará el 21 de Octubre en la Rural del Prado y contará con la participación de más de 15 bandas nacionales. Quienes no puedan trasladarse al lugar, tendrán la posibilidad de disfrutar los shows a través de Internet, así como entrevistas en vivo a los músicos una vez finalizados los conciertos.",
                    "Rural de Prado",
                    LocalDate.of(2017, 10, 21),
                    1000, 900000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/pil.png"),
                    kh, festival
            );
            
            Propuesta ryj = new Propuesta(
                    "Romeo y Julieta",
                    "Romeo y Julieta de Kenneth MacMillan, uno de los ballets favoritos del director artístico Julio Bocca, se presentará nuevamente el 5 de Noviembre en el Auditorio Nacional del Sodre. Basada en la obra homónima de William Shakespeare, Romeo y Julieta es considerada la coreografía maestra del MacMillan. La producción de vestuario y escenografía se realizó en los Talleres del Auditorio Adela Reta, sobre los diseños originales.",
                    "Auditorio Nacional del Sodre",
                    LocalDate.of(2017, 11, 5),
                    800, 750000,
                    EnumSet.of(TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/ryj.png"),
                    jb, ballet
            );
            
            Propuesta udj = new Propuesta(
                    "Un día de Julio",
                    "La Catalina presenta el espectáculo \"Un Día de Julio\" en Landia. Un hombre misterioso y solitario vive encerrado entre las cuatro paredes de su casa. Intenta, con sus teorías extravagantes, cambiar el mundo exterior que le resulta inhabitable. Un día de Julio sucederá algo que cambiará su vida y la de su entorno para siempre.",
                    "Landia",
                    LocalDate.of(2017, 11, 16),
                    650, 300000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS, TipoRetorno.PORCENTAJEGANANCIA),
                    new File("imagenes/udj.png"),
                    tc, murga
            );
            
            Propuesta ldt = new Propuesta(
                    "El Lazarillo de Tormes",
                    "Vuelve unas de las producciones de El Galpón más aclamadas de los últimos tiempos. Esta obra se ha presentado en Miami, Nueva York, Washington, México, Guadalajara, Río de Janeiro y La Habana. En nuestro país, El Lazarillo de Tormes fue nominado en los rubros mejor espectáculo y mejor dirección a los Premios Florencio 1995, obteniendo su protagonista Héctor Guido el Florencio a Mejor actor de ese año.",
                    "Teatro el Galpón",
                    LocalDate.of(2017, 12, 3),
                    350, 175000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS),
                    null,
                    hg, teatroDramatico
            );
            
            Propuesta bef = new Propuesta(
                    "Bardo en la FING",
                    "El 10 de Diciembre se presentará Bardo Científico en la FING. El humor puede ser usado como una herramienta importante para el aprendizaje y la democratización de la ciencia, los monólogos científicos son una forma didáctica de apropiación del conocimiento científico y contribuyen a que el público aprenda ciencia de forma amena. Los invitamos a pasar un rato divertido, en un espacio en el cual aprenderán cosas de la ciencia que los sorprenderán. ¡Los esperamos!",
                    "Anfiteatro Edificio \"José Luis Massera\"",
                    LocalDate.of(2017, 12, 10),
                    200, 100000,
                    EnumSet.of(TipoRetorno.ENTRADAGRATIS),
                    null,
                    lb, standup
            );

            // Persistir propuestas
            em.persist(ceb); em.persist(mom); em.persist(pim); em.persist(pil);
            em.persist(ryj); em.persist(udj); em.persist(ldt); em.persist(bef);

            // Estados para CEB
            ceb.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 5, 15));
            ceb.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 5, 17));
            ceb.agregarEstado(TipoEstado.ENFINANCIACION, LocalDate.of(2017, 5, 20));
            ceb.agregarEstado(TipoEstado.FINANCIADA, LocalDate.of(2017, 5, 30));
            ceb.agregarEstado(TipoEstado.CANCELADA, LocalDate.of(2017, 6, 15));
            
            // Estados para MOM
            mom.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 6, 18));
            mom.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 6, 20));
            mom.agregarEstado(TipoEstado.ENFINANCIACION, LocalDate.of(2017, 6, 30));
            mom.agregarEstado(TipoEstado.FINANCIADA, LocalDate.of(2017, 7, 15));
            
            // Estados para PIM
            pim.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 7, 26));
            pim.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 7, 31));
            pim.agregarEstado(TipoEstado.ENFINANCIACION, LocalDate.of(2017, 8, 1));
            
            // Estados para PIL
            pil.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 7, 30));
            pil.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 8, 1));
            pil.agregarEstado(TipoEstado.ENFINANCIACION, LocalDate.of(2017, 8, 5));
            
            // Estados para RYJ
            ryj.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 8, 4));
            ryj.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 8, 10));
            
            // Estados para UDJ
            udj.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 8, 6));
            udj.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 8, 12));
            
            // Estados para LDT
            ldt.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 8, 18));
            ldt.agregarEstado(TipoEstado.PUBLICADA, LocalDate.of(2017, 8, 20));
            
            // Estados para BEF
            bef.agregarEstado(TipoEstado.INGRESADA, LocalDate.of(2017, 8, 23));

            Colaboracion col01 = new Colaboracion(50000, LocalDate.of(2017, 5, 20), TipoRetorno.PORCENTAJEGANANCIA, ceb, en);
            Colaboracion col02 = new Colaboracion(50000, LocalDate.of(2017, 5, 24), TipoRetorno.PORCENTAJEGANANCIA, ceb, rh);
            Colaboracion col03 = new Colaboracion(50000, LocalDate.of(2017, 5, 30), TipoRetorno.PORCENTAJEGANANCIA, ceb, nj);
            Colaboracion col04 = new Colaboracion(200000, LocalDate.of(2017, 6, 30), TipoRetorno.PORCENTAJEGANANCIA, mom, mt);
            Colaboracion col05 = new Colaboracion(500, LocalDate.of(2017, 7, 1), TipoRetorno.ENTRADAGRATIS, mom, tj);
            Colaboracion col06 = new Colaboracion(600, LocalDate.of(2017, 7, 7), TipoRetorno.ENTRADAGRATIS, mom, mg);
            Colaboracion col07 = new Colaboracion(50000, LocalDate.of(2017, 7, 10), TipoRetorno.PORCENTAJEGANANCIA, mom, en);
            Colaboracion col08 = new Colaboracion(50000, LocalDate.of(2017, 7, 15), TipoRetorno.PORCENTAJEGANANCIA, mom, sp);
            Colaboracion col09 = new Colaboracion(200000, LocalDate.of(2017, 8, 1), TipoRetorno.PORCENTAJEGANANCIA, pim, mt);
            Colaboracion col10 = new Colaboracion(80000, LocalDate.of(2017, 8, 3), TipoRetorno.PORCENTAJEGANANCIA, pim, sp);
            Colaboracion col11 = new Colaboracion(50000, LocalDate.of(2017, 8, 5), TipoRetorno.ENTRADAGRATIS, pil, ar);
            Colaboracion col12 = new Colaboracion(120000, LocalDate.of(2017, 8, 10), TipoRetorno.PORCENTAJEGANANCIA, pil, en);
            Colaboracion col13 = new Colaboracion(120000, LocalDate.of(2017, 8, 15), TipoRetorno.ENTRADAGRATIS, pil, ap);
            Colaboracion col14 = new Colaboracion(100000, LocalDate.of(2017, 8, 13), TipoRetorno.PORCENTAJEGANANCIA, ryj, sp);
            Colaboracion col15 = new Colaboracion(200000, LocalDate.of(2017, 8, 14), TipoRetorno.PORCENTAJEGANANCIA, ryj, mt);
            Colaboracion col16 = new Colaboracion(30000, LocalDate.of(2017, 8, 15), TipoRetorno.ENTRADAGRATIS, udj, ap);
            Colaboracion col17 = new Colaboracion(150000, LocalDate.of(2017, 8, 17), TipoRetorno.PORCENTAJEGANANCIA, udj, mt);

            // Persistir colaboraciones
            em.persist(col01); em.persist(col02); em.persist(col03); em.persist(col04);
            em.persist(col05); em.persist(col06); em.persist(col07); em.persist(col08);
            em.persist(col09); em.persist(col10); em.persist(col11); em.persist(col12);
            em.persist(col13); em.persist(col14); em.persist(col15); em.persist(col16);
            em.persist(col17);

            // HR sigue a: MB, HG, TC
            hr.addUsuariosSeguidos(mb); hr.addUsuariosSeguidos(hg); hr.addUsuariosSeguidos(tc);
            
            // MB sigue a: HR, HG, TC
            mb.addUsuariosSeguidos(hr); mb.addUsuariosSeguidos(hg); mb.addUsuariosSeguidos(tc);
            
            // HG sigue a: HR, TC
            hg.addUsuariosSeguidos(hr); hg.addUsuariosSeguidos(tc);
            
            // TC sigue a: HR, TC
            tc.addUsuariosSeguidos(hr); tc.addUsuariosSeguidos(tc);
            
            // CS sigue a: HR
            cs.addUsuariosSeguidos(hr);
            
            // JB sigue a: HR, TC
            jb.addUsuariosSeguidos(hr); jb.addUsuariosSeguidos(tc);
            
            // DP sigue a: HR, TC
            dp.addUsuariosSeguidos(hr); dp.addUsuariosSeguidos(tc);
            
            // KH sigue a: HR
            kh.addUsuariosSeguidos(hr);
            
            // LB sigue a: HR, TC
            lb.addUsuariosSeguidos(hr); lb.addUsuariosSeguidos(tc);
            
            // RH sigue a: HR, MB, HG
            rh.addUsuariosSeguidos(hr); rh.addUsuariosSeguidos(mb); rh.addUsuariosSeguidos(hg);
            
            // MT sigue a: HR, MB, HG
            mt.addUsuariosSeguidos(hr); mt.addUsuariosSeguidos(mb); mt.addUsuariosSeguidos(hg);
            
            // EN sigue a: HR, MB, HG
            en.addUsuariosSeguidos(hr); en.addUsuariosSeguidos(mb); en.addUsuariosSeguidos(hg);
            
            // SP sigue a: HR, MB, HG
            sp.addUsuariosSeguidos(hr); sp.addUsuariosSeguidos(mb); sp.addUsuariosSeguidos(hg);
            
            // AR sigue a: HR
            ar.addUsuariosSeguidos(hr);
            
            // AP sigue a: HR
            ap.addUsuariosSeguidos(hr);
            
            // NJ sigue a: HR, MB
            nj.addUsuariosSeguidos(hr); nj.addUsuariosSeguidos(mb);
            
            // JP sigue a: HR, MB, HG
            jp.addUsuariosSeguidos(hr); jp.addUsuariosSeguidos(mb); jp.addUsuariosSeguidos(hg);
            
            // MG sigue a: HR, MB, HG
            mg.addUsuariosSeguidos(hr); mg.addUsuariosSeguidos(mb); mg.addUsuariosSeguidos(hg);
            
            // PL sigue a: HR, MB
            pl.addUsuariosSeguidos(hr); pl.addUsuariosSeguidos(mb);
            
            // TJ sigue a: HR, MB, HG
            tj.addUsuariosSeguidos(hr); tj.addUsuariosSeguidos(mb); tj.addUsuariosSeguidos(hg);

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

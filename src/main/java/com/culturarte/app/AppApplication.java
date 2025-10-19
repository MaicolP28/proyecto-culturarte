package com.culturarte.app;

import com.culturarte.presentacion.MenuPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.swing.SwingUtilities;
import java.awt.*;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        System.out.println("🚀 Aplicación Culturarte iniciada correctamente.");

        // Solo iniciar la interfaz Swing si no estamos en modo headless
        if (!GraphicsEnvironment.isHeadless()) {
            iniciarSwing();
        } else {
            System.out.println("🖥️ Entorno sin interfaz gráfica: no se inicia Swing.");
        }
    }

    private static void iniciarSwing() {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}


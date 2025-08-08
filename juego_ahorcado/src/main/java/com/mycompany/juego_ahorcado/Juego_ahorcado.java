package com.mycompany.juego_ahorcado;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import controlador.ControladorPrincipal;

public class Juego_ahorcado {

    public static void main(String[] args) {

        FlatSolarizedLightIJTheme.setup();

        ControladorPrincipal controlador = new ControladorPrincipal();
        controlador.inicializar();
        
        controlador.iniciarAplicacion();
    }
}

package com.mycompany.juego_ahorcado;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import logueo_registro.JFLogueo;

public class Juego_ahorcado {

    public static void main(String[] args) {
        FlatSolarizedLightIJTheme.setup();
        JFLogueo frame = new JFLogueo();
        frame.setVisible(true);
    }
}

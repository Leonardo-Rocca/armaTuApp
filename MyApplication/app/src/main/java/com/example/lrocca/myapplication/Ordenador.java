package com.example.lrocca.myapplication;

import java.util.ArrayList;

/**
 * Created by lrocca on 21/04/2017.
 */
public class Ordenador {
    public Match max(ArrayList<Match> matches) {
        int h = 10000;
        Integer hActual;
        int selectedIndex=-1;

        for (int i =0;i<matches.size();i++){
            hActual = matches.get(i).habilidadAcumulada();
           if (hActual<h) {
               h=hActual;
               selectedIndex=i;
           }

        }

        return matches.get(selectedIndex);
    }

}

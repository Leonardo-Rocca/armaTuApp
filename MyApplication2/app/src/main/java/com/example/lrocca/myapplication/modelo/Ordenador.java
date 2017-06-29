package com.example.lrocca.myapplication.modelo;

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
    public int maxCode(ArrayList<Jugador> players) {

        if(players.isEmpty())return  -1;
        int h = -10000;
        Integer hActual;
        int selectedIndex=-1;

        for (int i =0;i<players.size();i++){
            hActual = players.get(i).getId();
            if (hActual>h) {
                h=hActual;
                selectedIndex=i;
            }
        }

        return players.get(selectedIndex).getId();
    }

    public Jugador find(ArrayList<Jugador> lJugadores, Jugador jugador) {
        int id = jugador.getId();
        Integer hActual;
        int selectedIndex=-1;

        for (int i =0;i<lJugadores.size();i++){
            hActual = lJugadores.get(i).getId();
            if (hActual==id) {
                selectedIndex=i;
               i=lJugadores.size();
            }

        }
        return lJugadores.get(selectedIndex);
    }


}

package com.example.lrocca.myapplication.modelo;

import java.util.ArrayList;

/**
 * Created by lrocca on 07/06/2017.
 */
public class Grupo {

    private  ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private String name;

    public Grupo(String name, ArrayList<Jugador> players) {
        this.setName(name);
        this.setJugadores(players);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public ArrayList<Jugador> getPlayers() {
        return jugadores;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}

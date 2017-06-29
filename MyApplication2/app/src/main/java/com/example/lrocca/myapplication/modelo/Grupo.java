package com.example.lrocca.myapplication.modelo;

import java.util.ArrayList;

/**
 * Created by lrocca on 07/06/2017.
 */
public class Grupo implements Persistible{

    private  ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private String name;
    private int id;

    public Grupo(String name, ArrayList<Jugador> players, int id) {
        initialice(name, id);
        this.setJugadores(players);
    }

    private void initialice(String name, int id) {
        this.setName(name);
        this.setId(id);
    }

    public Grupo(String name, int id) {
        initialice(name,id);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

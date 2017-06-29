package com.example.lrocca.myapplication.Fragments;

import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * Created by lrocca on 12/05/2017.
 */
public class BtnAction {
    private ABMPlayerFragment frag;
    private Jugador player;

    public void setPlayer(Jugador j){
        player = j;
    }

    public ABMPlayerFragment getFrag() {
        return frag;
    }

    public void setFrag(ABMPlayerFragment frag) {
        this.frag = frag;
    }

    public Jugador getPlayer() {
        return player;
    }

    public void execute(String n, String h) {

    }
}

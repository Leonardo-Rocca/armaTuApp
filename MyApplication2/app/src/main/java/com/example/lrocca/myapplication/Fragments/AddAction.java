package com.example.lrocca.myapplication.Fragments;

import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * Created by lrocca on 12/05/2017.
 */
public class AddAction extends BtnAction {

    public AddAction(ABMPlayerFragment abmPlayerFragment) {
        setFrag(abmPlayerFragment);
   //     getFrag().getBtnExec().setText("Agregar");
    }

    public void execute(String n, String h) {
        getFrag().addPlayer(n,h);
    }
}

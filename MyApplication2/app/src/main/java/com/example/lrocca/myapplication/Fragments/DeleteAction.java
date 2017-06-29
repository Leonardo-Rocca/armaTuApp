package com.example.lrocca.myapplication.Fragments;

import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * Created by lrocca on 12/05/2017.
 */
public class DeleteAction extends BtnAction {

    public DeleteAction(ABMPlayerFragment abmPlayerFragment) {
        setFrag(abmPlayerFragment);
    }


    public void execute(String n, String h) {
        getFrag().delete(getPlayer());
    }
}

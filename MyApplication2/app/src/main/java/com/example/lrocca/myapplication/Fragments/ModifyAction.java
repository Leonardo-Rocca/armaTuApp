package com.example.lrocca.myapplication.Fragments;

import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * Created by lrocca on 12/05/2017.
 */
public class ModifyAction extends BtnAction {


    public ModifyAction(ABMPlayerFragment abmPlayerFragment) {
        setFrag(abmPlayerFragment);
        getFrag().getBtnExec().setText("Modificar");
    }

    public void execute(String n, String h) {
        getFrag().getBtnExec().setText("Agregar");
        getFrag().setbtnAction(new AddAction(getFrag()));
        getFrag().modifyPlayer(n,h,getPlayer().getId());
    }
}

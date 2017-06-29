package com.example.lrocca.myapplication.Slide;

import com.example.lrocca.myapplication.Row;
import com.example.lrocca.myapplication.modelo.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lrocca on 27/06/2017.
 */
public class RowsController {

    public ArrayList<Row> getRows(ArrayList<Jugador> jugadores) {
        ArrayList<Row> rows = new ArrayList<Row>();

        Row row = null;
        for (int i = 0; i < jugadores.size(); i++) {
            row = new Row();
            row.setPlayer(jugadores.get(i));
            rows.add(row);
        }
        return rows;
    }

    public void chechAllId(ArrayList<Jugador> g, ArrayList<Row> lrows) {
        for (int i = 0; i < g.size(); i++) {
            checkWhithId(g.get(i).getId(),lrows);
        }
    }

    private void checkWhithId(final long id,ArrayList<Row> rows) {
      /*  Iterator ite = new Iterator(this,rows,adapter) {
            public boolean execute(Row r) {
                return r.getPlayer().getId()==id;
            }
        };*/
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getPlayer().getId() == id) {
                rows.get(i).setChecked(true);
            }
        }
    }

    public ArrayList<Jugador> rowsChecked(ArrayList<Row> rows) {
        ArrayList<Jugador> j = new ArrayList<Jugador>();
      //  j.add(new Jugador("pepe","3",100));
        for (int i = 0; i < rows.size(); i++) {
            if(rows.get(i).isChecked()) j.add(rows.get(i).getPlayer());
        }
        return j;
    }
}

package com.example.lrocca.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lrocca on 24/04/2017.
 */

public class FileSystem extends SQLiteOpenHelper {

    public FileSystem(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ArrayList<Jugador> getPlayers() {
        ArrayList<Jugador> lJugadores = new ArrayList<>();
        SQLiteDatabase bd = this.getWritableDatabase();
        int i= this.maxCode();

       for (int j=0;j<i;j++){
        Cursor fila = bd.rawQuery(
                "select name,hability from players where codigo=" +j, null);

        if (fila.moveToFirst()) {
            lJugadores.add( new Jugador(fila.getString(1),Integer.valueOf(fila.getString(2))));
        }
        }

            bd.close();


        lJugadores.add(new Jugador("Leo",7));lJugadores.add(new Jugador("Eric",6));
            lJugadores.add(new Jugador("Facu",7));lJugadores.add(new Jugador("Nico",7));lJugadores.add(new Jugador("Cris",4));lJugadores.add(new Jugador("Fede",3));
            lJugadores.add(new Jugador("Juanjo",9));lJugadores.add(new Jugador("Edu",8));lJugadores.add(new Jugador("Titos",6));lJugadores.add(new Jugador("Nico A",9));

        return lJugadores;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table players(codigo int primary key,name text,hability int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int maxCode() {
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select Max(codigo) from players", null);

        if (fila.moveToFirst()) {
            bd.close();
            return Integer.valueOf(fila.getString(0));
        }
        bd.close();
        return -1;
    }
}

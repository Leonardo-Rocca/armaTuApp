package com.example.lrocca.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lrocca.myapplication.modelo.Grupo;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.modelo.Ordenador;

import java.util.ArrayList;

/**
 * Created by lrocca on 24/04/2017.
 */

public class FileSystem extends SQLiteOpenHelper {

    private int currentId=0;

    public FileSystem(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
      //  this.currentId=this.maxCode();
    }

    public ArrayList<Jugador> getPlayers() {
        ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
     //   persistir(new Jugador("Eric",6));
      SQLiteDatabase bd = this.getWritableDatabase();
        Jugador player;
        int i=1 ;//this.maxCode();

       String[] campos = new String[] {"codigo", "name","hability"};

        Cursor c = bd.query("players", campos, null, null, null, null, null);

//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
             do {
                player = new Jugador(c.getString(1), c.getInt(2));
                player.setId(c.getInt(0));
                lJugadores.add(player);
            } while(c.moveToNext());
        }

    /*    if (fila.moveToFirst()) {
             player = new Jugador(fila.getString(0), fila.getInt(1));
            player.setId(fila.getInt(2));
            lJugadores.add(player);
        }*/
     //   }

            bd.close();
        return lJugadores;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table players(codigo integer primary key autoincrement,name text,hability integer)");//  db.execSQL("create table players(codigo integer primary key,name text,hability integer)");
        db.execSQL("create table players(codigo int primary key,name text,hability int)");//  db.execSQL("create table players(codigo integer primary key,name text,hability integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int maxCode2() {
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select max(codigo) from players", null);

        if (fila.moveToFirst()) {
            bd.close();
            return Integer.valueOf(fila.getInt(0));
        }
        bd.close();
        return -1;
    }

    public void persistir(Jugador j) {
        SQLiteDatabase bd = this.getWritableDatabase();
        String cod = String.valueOf(j.getId());
        String name = j.getName();
        String hability = String.valueOf(j.getHability());
        ContentValues registro = new ContentValues();
        registro.put("codigo", j.getId());
        registro.put("name", name);
        registro.put("hability", hability);
        bd.insert("players", null, registro);
        bd.close();
    }

    public int getCurrentId() {
        this.currentId = currentId+1;
        return currentId;
    }

    public ArrayList<Jugador> getPlayersDammy() {
        ArrayList<Jugador> lJugadores=new ArrayList<Jugador>();
        lJugadores.add(new Jugador("Leo",7));lJugadores.add(new Jugador("Eric",6));
        lJugadores.add(new Jugador("Facu",7));lJugadores.add(new Jugador("Nico",7));lJugadores.add(new Jugador("Cris",4));lJugadores.add(new Jugador("Fede",3));
        lJugadores.add(new Jugador("Juanjo",9));lJugadores.add(new Jugador("Edu",8));lJugadores.add(new Jugador("Titos",6));lJugadores.add(new Jugador("Nico A",9));

        return lJugadores;
    }

    public int maxCode() {
       return new Ordenador().maxCode(this.getPlayers());
    }

    public void modify(Jugador j) {
        SQLiteDatabase bd = this.getWritableDatabase();
        String cod = String.valueOf(j.getId());
        String name = j.getName();
        String hability = String.valueOf(j.getHability());
        ContentValues registro = new ContentValues();
       // registro.put("codigo", j.getId());
        registro.put("name", name);
        registro.put("hability", hability);
        bd.update("players",registro,"codigo ="+cod,null);//(layers", null, registro);
        bd.close();
    }

    public void deletePlayer(Jugador j) {
        SQLiteDatabase bd = this.getWritableDatabase();
        String cod = String.valueOf(j.getId());
        bd.delete("players","codigo ="+cod,null);
        bd.close();
    }

    public ArrayList<Grupo> getGroups() {
        ArrayList<Grupo> g = new ArrayList<Grupo>();
        ArrayList<Jugador> js = this.getPlayers();
        g.add(new Grupo("Ninguno", new ArrayList<Jugador>()));
        g.add(new Grupo("Todos",js));
        ArrayList<Jugador> js2 = new ArrayList<Jugador>();js2.add(js.get(0));js2.add(js.get(3));
        g.add(new Grupo("Alguno",js2));
        return g;
    }
}

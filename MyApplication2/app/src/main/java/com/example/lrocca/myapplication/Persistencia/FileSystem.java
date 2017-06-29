package com.example.lrocca.myapplication.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.lrocca.myapplication.modelo.Grupo;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.modelo.Ordenador;

import java.util.ArrayList;

/**
 * Created by lrocca on 24/04/2017.
 */

public class FileSystem extends SQLiteOpenHelper {

    private static final String GROUPPLAYERS = "groups_players";
    private static final String PLAYERS = "players";
    private static final String GROUPS = "groups";
    private int currentId=0;
    // Database Version
    private static final int DATABASE_VERSION = 4;

    public FileSystem(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION);
      //  this.currentId=this.maxCode();
    }

    public ArrayList<Jugador> getPlayers(String where) {
        ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
     //   persistir(new Jugador("Eric",6));
      SQLiteDatabase bd = this.getWritableDatabase();
        Jugador player;
        int i=1 ;//this.maxCode();

       String[] campos = new String[] {"codigo", "name","hability"};

        Cursor c = bd.query("players", campos, where, null, null, null, null);

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
        //  db.execSQL("create table players(codigo integer primary key,name text,hability integer)");

        db.execSQL("DROP TABLE IF EXISTS "+ "players");
        db.execSQL("create table players(codigo int primary key,name text,hability int)");   //<<<-- la inicial

        db.execSQL("create table groups(group_id integer primary key ,group_name text)");
        String midleTable = "create table groups_players (" +
                "player_id integer," +
                "group_id integer," +
                "order_num integer," +
                "status integer," + //agregado
                "primary key (player_id, group_id))";
        db.execSQL(midleTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ "groups");
        db.execSQL("DROP TABLE IF EXISTS "+ "groups_players");

        onCreate(db);
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

        this.addPlayerToGroup(bd,"1",100,j.getId());
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
        ArrayList<Jugador> players = this.getPlayers();
        return new Ordenador().maxCode(players);
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
        bd.delete(GROUPPLAYERS,"player_id ="+cod,null);
        bd.close();
    }

    public ArrayList<Grupo> getGroups() {
        ArrayList<Grupo> g = getEmptyGroups();

        ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
        String codigosQuery;
        for (int i =0;i<g.size();i++){
            codigosQuery = " (select player_id FROM "+GROUPPLAYERS+" gp, "+PLAYERS +" p" +
                    " WHERE codigo = gp.player_id " +//" )"; +
                    " AND group_id ="+String.valueOf(i)+" ) ";
            lJugadores = getPlayers("codigo in"+codigosQuery);
            g.get(i).setJugadores(lJugadores);
        }
        return g;
    }

    @NonNull
    public ArrayList<Grupo> getEmptyGroups() {
        ArrayList<Grupo> g = new ArrayList<Grupo>();

        SQLiteDatabase bd = this.getWritableDatabase();
        Grupo grupo =new Grupo("all",new ArrayList<Jugador>(),0);
        String[] campos = new String[] {"group_name","group_id"};

        Cursor c = bd.query(GROUPS,campos, null , null, null, null, null);/* "group_id ="+String.valueOf(index)*/

        if (c.moveToFirst()) {
                   do {
                       grupo = new Grupo(c.getString(0), c.getInt(1));
                       g.add(grupo);
                   } while(c.moveToNext());
        }
        bd.close();
        return g;
    }


    public Grupo getGroup(int index) {
        return this.getGroups().get(index);
    }

    public ArrayList<Jugador> getPlayers() {
       return this.getPlayers(null);
    }

    String midleTable = "create table groups_players (" +
            "player_id integer," +
            "group_id integer," +
            "order_num integer," +
            "primary key (player_id, group_id))";

    public void createtGroup(Grupo group) {
        SQLiteDatabase bd = this.getWritableDatabase();

        String group_name = group.getName();
        ContentValues registro = new ContentValues();
        registro.put("group_name", group_name);
        bd.insert("groups", null, registro);

        String group_id  = String.valueOf(group.getId());

        ArrayList<Jugador> players = group.getPlayers();
        addPlayersToGroup(bd, group_id,players);

        bd.close();
    }

    private void addPlayersToGroup(SQLiteDatabase bd, String group_id, ArrayList<Jugador> players) {
        for (int i = 0; i< players.size(); i++){
            addPlayerToGroup(bd, group_id, i, players.get(i).getId());
        }
    }

    private void addPlayerToGroup(SQLiteDatabase bd, String group_id, int order, int player_id) {
        ContentValues registro = new ContentValues();
        registro.put("player_id",String.valueOf(player_id) );
        registro.put("group_id",group_id);
        registro.put("order_num",String.valueOf(order) );
        registro.put("status","1");
        bd.insert("groups_players", null, registro);
    }

    public String getDummy() {
        Grupo g = this.getGroup(0);
        SQLiteDatabase bd = this.getWritableDatabase();
        String[] campos = new String[] {"group_name","group_id"};

        Cursor c = bd.query(GROUPS, campos, null, null, null, null, null);
        String s="sarasa";
//Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            s = c.getString(0);
            s= s + String.valueOf(c.getInt(1));
        }
        bd.close();this.getDummyGroup();

        return g.getName()+String.valueOf(g.getId());
    }

    public Grupo getDummyGroup() {
       ArrayList<Jugador> js = this.getPlayers();
        ArrayList<Jugador> js2 = new ArrayList<Jugador>();js2.add(js.get(0));js2.add(js.get(3));js2.add(js.get(5));
    return new Grupo("Algunos",js2,2);
    }

    public void onlyPersist(ArrayList<Jugador> j, int groupId) {
        SQLiteDatabase bd = this.getWritableDatabase();
        String gid = String.valueOf(groupId);

        deletePlayersFromGroup(bd,gid);
        addPlayersToGroup(bd,gid,j);
    }

    private void deletePlayersFromGroup(SQLiteDatabase bd, String gid) {
        bd.delete(GROUPPLAYERS,"group_id ="+gid,null);
    }


}

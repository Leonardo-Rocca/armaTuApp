package com.example.lrocca.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ABMPlayerActivity extends AppCompatActivity {
    private ArrayAdapter<Jugador> adapter;
    private ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmplayer);
        new FileSystem(this, "administracion", null, 1);

         lv =(ListView)findViewById(R.id.lvPlayers);
        adapter = new ArrayAdapter<Jugador>(this,android.R.layout.simple_list_item_1, lJugadores);
        lv.setAdapter(adapter);//JugadoresTotales

        loadPlayers();
    }

    private void loadPlayers() {
        FileSystem admin = new FileSystem(this, "administracion", null, 1);
        admin.getPlayers();
}}

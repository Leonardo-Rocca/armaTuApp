package com.example.lrocca.myapplication.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.lrocca.myapplication.Adapters.RecicleAdapter;
import com.example.lrocca.myapplication.Persistencia.FileSystem;
import com.example.lrocca.myapplication.Persistencia.FileSystemAdapter;
import com.example.lrocca.myapplication.Slide.RowsController;
import com.example.lrocca.myapplication.modelo.Grupo;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Row;

public class SelectionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<Row> rows;

   
    private RecicleAdapter adapter;
    private RecyclerView listView;
    private TextView tvCant;
    //   private CustomArrayAdapter adapter;
  //  private CustomCheckRowAdapter adapter;
    private Spinner spGroups;
    private ArrayList<Grupo> grupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    //    listView = (ListView) findViewById(android.R.id.list);
        listView = (RecyclerView) findViewById(R.id.my_recycler_view);

        FileSystem fs = FileSystemAdapter.getFS();
        ArrayList<Jugador> j = fs.getPlayers();// new  ArrayList<Jugador>() ;//new FileSystem(new ABMPlayerActivity(), "administracion", null, 1).getPlayers2();
      // j.add(new Jugador("Dammy",1)); j.addAll(new FileSystem().getPlayers2());
        rows = new RowsController().getRows(j);

    //    listView.setHasFixedSize(true);
        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llmanager);
        adapter = new RecicleAdapter(this,j);
      //  adapter = new CustomCheckRowAdapter(this, rows);

        listView.setAdapter(adapter);
        tvCant = (TextView) findViewById(R.id.tvCant);

        spGroups = (Spinner) findViewById(R.id.spinnerGroups);
         grupos = fs.getGroups();
        ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this,android.R.layout.simple_spinner_item, grupos){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                // do whatever you want with this text view
                textView.setTextSize(24);
                textView.setTextColor(Color.WHITE);
                return view;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setBackgroundColor(Color.LTGRAY);
               view.setBackgroundResource(R.drawable.item_white);
                //view.setActivated(position == selectedScrollAnimator);
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spGroups.setAdapter(adapter);
        spGroups.setOnItemSelectedListener(this);
        spGroups.setSelection(-1);
    }


    public void generate (View view) {
        Intent i = new Intent(this, MainActivity.class );
        i.putExtra("players",this.selectedPlayers());
        startActivity(i);
    }

    private ArrayList<Jugador> selectedPlayers() {
        return this.rowsChecked();
    }

    public ArrayList<Jugador> rowsChecked() {
        ArrayList<Jugador> j = new ArrayList<Jugador>();
        for (int i = 0; i < rows.size(); i++) {
            if(rows.get(i).isChecked()) j.add(rows.get(i).getPlayer());
        }
        return j;
    }

    public void selectAll (View view) {
        select(rows);
    }

    private void select(ArrayList<Row> list) {
        //  adapter.selectAll();
        for (int i = 0; i < list.size(); i++) {
           list.get(i).changeCheck();
        }
        refreshSelection();
    }

    private void refreshSelection() {
        adapter.notifyDataSetChanged();
        updateSelected();
    }

    public void check(int position) {
        rows.get(position).changeCheck();
        updateSelected();
    }

    public void updateSelected() {
        tvCant.setText("Seleccionados:"+ String.valueOf(this.selectedPlayers().size()));
    }

    public boolean amIChecked(int position) {
        return  rows.get(position).isChecked();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectCheck(rows,false);
        ArrayList<Jugador> g = grupos.get(position).getPlayers();
        new RowsController().chechAllId(g,rows);
        refreshSelection();
    }


    private void selectCheck(ArrayList<Row> list, boolean b) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(b);
            }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.example.lrocca.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelectionActivity extends AppCompatActivity {
    List<Row> rows;

    private ListView listView;
    private CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        listView = (ListView) findViewById(android.R.id.list);

        ArrayList<Jugador> j = new FileSystem().getPlayers();
        rows = new ArrayList<Row>();

        Row row = null;
        for (int i = 0; i < j.size(); i++) {
            row = new Row();
            row.setPlayer(j.get(i));
            rows.add(row);
        }

      //  rows.get(3).setChecked(true);

        adapter = new CustomArrayAdapter(this, rows);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

      /*  listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(SelectionActivity.this,
                        rows.get(position).getTitle(), Toast.LENGTH_SHORT)
                        .show();

                rows.get(position).setChecked(true);
                adapter.notifyDataSetChanged();
            }
        });*/
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                listView.setItemChecked(position, true);
                rows.get(position).changeCheck();
                adapter.notifyDataSetChanged();
            }
        });

    }


    public void generate (View view) {
        Intent i = new Intent(this, MainActivity.class );
        i.putExtra("players",this.selectedPlayers());
        startActivity(i);
    }

    private ArrayList<Jugador> selectedPlayers() {
        //return new FileSystem().getPlayers();
        return this.rowsChecked();
    }

    private ArrayList<Jugador> rowsChecked() {
        ArrayList<Jugador> j = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            if(rows.get(i).isChecked()) j.add(rows.get(i).getPlayer());
        }

        return j;
    }

    public void selectAll (View view) {
      adapter.selectAll();
    }
}

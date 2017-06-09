package com.example.lrocca.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lrocca.myapplication.modelo.ArmadorEquipos;
import com.example.lrocca.myapplication.modelo.Equipo;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.modelo.Match;
import com.example.lrocca.myapplication.modelo.Ordenador;
import com.example.lrocca.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private String[] JugadoresTotales = { "Leo", "Cris", "Facu", "Bolivia",
            "Peru", "Ecuador", "Brasil", "Colombia", "Venezuela", "Urugay" };
    private ArrayList<Jugador> jugadoresE1 = new ArrayList<Jugador>();
    private ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
    private ArrayList<Jugador> jugadoresE2 = new ArrayList<Jugador>();

    private ListView lv;
    private ListView lvE1;
    private ListView lvE2;

    private ArrayAdapter<Jugador> adapterLv1;
    private ArrayAdapter<Jugador> adapterLv2;
    private ArrayAdapter<Jugador> adapter;

    private TextView tv1;
    private TextView tv2;
    private TextView tvPlayer;
    private int selectedIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.iniciarLista();
        lv =(ListView)findViewById(R.id.lvJugadores);
        adapter = new ArrayAdapter<Jugador>(this,android.R.layout.simple_list_item_1, lJugadores);
        lv.setAdapter(adapter);//JugadoresTotales

        lvE1 =(ListView)findViewById(R.id.lvJugadores1);
        adapterLv1 = new ArrayAdapter<Jugador>(this,android.R.layout.simple_list_item_1, jugadoresE1);
        lvE1.setAdapter(adapterLv1);

        lvE2 =(ListView)findViewById(R.id.lvJugadores2);
        adapterLv2 = new ArrayAdapter<Jugador>(this,android.R.layout.simple_list_item_1, jugadoresE2);
        lvE2.setAdapter(adapterLv2);

        tv1=(TextView)findViewById(R.id.txtHabilityE1);
        tv2=(TextView)findViewById(R.id.txtHabilityE2);
        tvPlayer=(TextView)findViewById(R.id.txtSelectedPlayer);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex=position;
                tvPlayer.setText(lJugadores.get(position).getName());
            }
        });

        lvE1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returnToMainList(position,jugadoresE1);
            }
        });
        lvE2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returnToMainList(position,jugadoresE2);
            }
        });


        CheckBox chk = (CheckBox) findViewById(R.id.chkPoints);
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePoints();
            }
        });
        this.changePoints();
    }

    private void returnToMainList(int position, ArrayList<Jugador> jugadoresFrom) {
        selectedIndex=position;
        add(lJugadores, jugadoresFrom,adapter,adapterLv1,tvPlayer);
        selectedIndex=lJugadores.size()-1;
        tvPlayer.setText(lJugadores.get(selectedIndex).getName());
        refreshHabilities();
    }

    private void iniciarLista() {
       lJugadores.addAll((Collection<? extends Jugador>) getIntent().getSerializableExtra("players"));
    }


    public void addE2(View view){
        this.add(jugadoresE2,lJugadores,adapterLv2,adapter,tv2);
    }
    public void addE1(View view){
        this.add(jugadoresE1,lJugadores,adapterLv1,adapter,tv1);
    }

    private void add(ArrayList<Jugador> jugadoresToAdd, ArrayList<Jugador> lJugadoresFrom, ArrayAdapter<Jugador> adapter1, ArrayAdapter<Jugador> adapterFrom, TextView tv) {
        if (selectedIndex==-1)return;
        jugadoresToAdd.add(lJugadoresFrom.get(selectedIndex));
        lJugadoresFrom.remove(selectedIndex);
        adapterFrom.notifyDataSetChanged(); adapter1.notifyDataSetChanged();
        String hability = String.valueOf(new Equipo(jugadoresToAdd).habilidad());
        tv.setText(hability);
        tvPlayer.setText("");
        selectedIndex=-1;
    }

    public void generar(View view){
        if (lJugadores.isEmpty())return;
        ArrayList<Jugador> total= new ArrayList<Jugador>();
        total.addAll(jugadoresE1);total.addAll(jugadoresE2); total.addAll(lJugadores);

          Match m = new Ordenador().max(new ArmadorEquipos().armarEquipoConJugadores(total,jugadoresE1,jugadoresE2));
               jugadoresE1.removeAll(jugadoresE1); jugadoresE2.removeAll(jugadoresE2);
            jugadoresE1.addAll(m.getJugadoresEquipo1());     adapterLv1.notifyDataSetChanged();
             jugadoresE2.addAll(m.getJugadoresEquipo2()); adapterLv2.notifyDataSetChanged();
           lJugadores.removeAll(lJugadores); adapter.notifyDataSetChanged();

        refreshHabilities();
        tvPlayer.setText("A jugar!");
    }

    private void refreshHabilities() {
        String hability = String.valueOf(new Equipo(jugadoresE1).habilidad());
        tv1.setText(hability);
        hability = String.valueOf(new Equipo(jugadoresE2).habilidad());
        tv2.setText(hability);
    }

    private void changePoints() {
        changeList(lJugadores,adapter);
        changeList(jugadoresE1, adapterLv1);
        changeList(jugadoresE2, adapterLv2);
    }

    private void changeList(ArrayList<Jugador> listToChange, ArrayAdapter<Jugador> adapter) {
        int size = listToChange.size();

        for(int i = 0;i<size;i++){
            Jugador j = listToChange.get(i);
            j.changeDescription();
        }
        adapter.notifyDataSetChanged();
    }
}

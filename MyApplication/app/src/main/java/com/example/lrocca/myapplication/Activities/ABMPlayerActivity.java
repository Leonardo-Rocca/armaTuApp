package com.example.lrocca.myapplication.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lrocca.myapplication.Adapters.CustomABMAdapter;
import com.example.lrocca.myapplication.FileSystem;
import com.example.lrocca.myapplication.FileSystemAdapter;
import com.example.lrocca.myapplication.Fragments.ABMPlayerFragment;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.modelo.Ordenador;

import java.util.ArrayList;

public class ABMPlayerActivity extends AppCompatActivity implements ABMPlayerFragment.OnFragmentInteractionListener {
    private ArrayAdapter<Jugador> adapter;
    private Button btn;
    private ImageButton btAddModify;

    public ArrayList<Jugador> getlJugadores() {
        return lJugadores;
    }

    private ArrayList<Jugador> lJugadores = new ArrayList<Jugador>();
    ListView lv;
    private FileSystem admin;

    private EditText edtName;
    private EditText edtHability;
    private TextView tvTitle;

    public ABMPlayerFragment getFrag() {
        return frag;
    }

    public void setFrag(ABMPlayerFragment frag) {
        this.frag = frag;
    }

    private ABMPlayerFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abmplayer);

        lv =(ListView)findViewById(R.id.lvPlayers);
       // adapter = new ArrayAdapter<Jugador>(this,android.R.layout.simple_list_item_1, lJugadores);
        adapter = new CustomABMAdapter(this, lJugadores);

        lv.setAdapter(adapter);//JugadoresTotales

        loadPlayers();
     //   tvTitle= (TextView) findViewById(R.id.tvTitle);
        //tvTitle.setText(String.valueOf(admin.maxCode()));
        btn = (Button) findViewById(R.id.btnNew);
        btn.setVisibility(btn.INVISIBLE);
     //   addFrag2();
        btAddModify = (ImageButton) findViewById(R.id.imageButton);
    }

    private void addFrag2() {
        // Begin the transaction
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        frag = new ABMPlayerFragment();
        ft.add(R.id.activity_abmplayer, frag);
        ft.commit();
    }

    private void addFrag(Jugador j) {
        //frag = new ABMPlayerFragment();
        frag= ABMPlayerFragment.newInstance(j);
        frag.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_abmplayer, frag).commit();
    }

    private void loadPlayers() {
         admin = FileSystemAdapter.getFS();// new FileSystem(this, "administracion", null, 1);
        lJugadores.addAll(admin.getPlayers());
        adapter.notifyDataSetChanged();
    }

    public void modify(int position) {
        btn.setVisibility(btn.VISIBLE);
        Jugador j = lJugadores.get(position);
        addFrag2();
        getFrag().modify(j);

      //  addFrag( j);
       /* edtName.setText(j.getName());
        Jugador j2 = new Jugador(j.getName(), Integer.valueOf(edtHability.getText().toString()));
        j2.setId(j.getId());
        admin.modify(j2);*/
    }
    public void addPlayer (View v){
        addFrag2();
     //   getFrag().newPlayer();
        btn.setVisibility(btn.INVISIBLE);
    }


    @Override
    public void insert(String n, String s) {
       // tvTitle.setText(n + s);
        Jugador j = new Jugador(n, s);
        j.setId(admin.maxCode()+1);
        admin.persistir(j);
        lJugadores.add(j);
        adapter.notifyDataSetChanged();
        removeFrag();
    }

    private void removeFrag() {
     /*   FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment f = getFragmentManager().findFragmentById(R.id.activity_abmplayer);
        transaction.remove(f);
   //     transaction.addToBackStack(null);
// Commit the transaction
        transaction.commit();
        setFrag(null);

*/      android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(frag);
        ft.commit();
    }

    @Override
    public void update(Jugador jugador) {
        admin.modify(jugador);
        Jugador j = new Ordenador().find(lJugadores,jugador);
        j.setName(jugador.getName());
        j.setHability(jugador.getHability());
        adapter.notifyDataSetChanged();
        //   removeFrag();
        btn.setVisibility(btn.INVISIBLE);
    }

    public void delete(final int position) {
        //    final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("Desea eliminar al jugador?")
                //   .setView(taskEditText)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //      String task = String.valueOf(taskEditText.getText());
                        admin.deletePlayer(getlJugadores().remove(position));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();


    }
 }

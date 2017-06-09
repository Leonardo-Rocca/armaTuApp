package com.example.lrocca.myapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.modelo.Jugador;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 //* {@link ABMPlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ABMPlayerFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText edtHability;
    private EditText edtName;
    private Button btnExec;
    private BtnAction btnAction = new AddAction(this);

    public ABMPlayerFragment() {
        // Required empty public constructor
    }

    public void execute (View v){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView =  inflater.inflate(R.layout.fragment_abmplayer, container, false);
        //Nuevos parametros para el view del fragmento
        RelativeLayout.LayoutParams params =    new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //Nueva Regla: EL fragmento estara debajo del boton add_fragment
    //    params.addRule(RelativeLayout.ALIGN_LEFT, true);
  //     params.addRule(RelativeLayout.ABOVE, R.id.lvPlayers);   //<----este va
        //Margenes: top:41dp
    //    params.setMargins(0,780,0,1);
    //    params.setMargins(0,0,0,600);  //545 es la posta
        params.setMargins(0,0,0,540);                       //<----este va
        //Setear los parametros al view
        rootView.setLayoutParams(params);

        return rootView;
    }
    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        edtName = (EditText) rootView.findViewById(R.id.etName);
        edtHability = (EditText) rootView.findViewById(R.id.etHability);
        btnExec = (Button)rootView.findViewById(R.id.btnAdd);

        btnExec.setOnClickListener( new View.OnClickListener() {

            public void onClick(View view){
                if(edtHability.getText().toString().equals("")||edtName.getText().toString().equals(""))return;
                String n = edtName.getText().toString();
                String h = edtHability.getText().toString();
                edtName.setText("");
                edtHability.setText("");
                btnAction.execute(n,h);
            }
        });
    }

    public void addPlayer(String n, String h) {
        mListener.insert(n,h);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void modify(Jugador j) {
        updateText(j);
         btnAction = new ModifyAction(this);
         btnAction.setPlayer(j);
        btnExec.setText("Modificar");
        edtHability.selectAll();
    }

    private void updateText(Jugador j) {
        edtName.setText(j.getName());
        edtHability.setText(j.getStringHability());
    }

    public void delete(Jugador j) {
        updateText(j);
        btnAction = new DeleteAction(this);
        btnAction.setPlayer(j);
    }


    public void modifyPlayer(String n, String h, int id) {
        mListener.update(new Jugador(n,h,id));
    }

    public void newPlayer() {
        String h = edtHability.getText().toString();
        String n = edtName.getText().toString();
        if (h.equals("")|| n.equals(""))return;
        btnAction = new AddAction(this);
        getBtnExec().setText("Agregar");
        edtName.setText("");
        edtHability.setText("");
    }

    public Button getBtnExec() {
        return btnExec;
    }

    public void setbtnAction(AddAction btnAction) {
        this.btnAction = btnAction;
    }
/*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    //     void onFragmentInteraction(Uri uri);
        void insert(String n, String s);
        void update(Jugador jugador);
    }
    public static ABMPlayerFragment newInstance(Jugador j){
        ABMPlayerFragment f = new ABMPlayerFragment();
        if(j != null){
            f.modify(j);
        }
        return f;
    }
}

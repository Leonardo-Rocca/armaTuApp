package com.example.lrocca.myapplication.Slide;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lrocca.myapplication.Activities.MainActivity;
import com.example.lrocca.myapplication.Activities.SwipeActivity;
import com.example.lrocca.myapplication.Persistencia.FileSystem;
import com.example.lrocca.myapplication.Persistencia.FileSystemAdapter;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Row;
import com.example.lrocca.myapplication.modelo.Grupo;
import com.example.lrocca.myapplication.modelo.Jugador;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 */
public class ScreenSlidePageFragment extends Fragment {

    private static final String INDEX = "index";
    private static SwipeActivity activ;
    //   private OnFragmentInteractionListener mListener;
    private int index;
    private RecyclerView listView;
    private RecicleGroupAdapter adapter;
    private Grupo group;
    private ArrayList<Row> rows;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    public static ScreenSlidePageFragment newInstance(int color, int index, SwipeActivity swipeActivity) {
       activ = swipeActivity;
        // Instantiate a new fragment
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //  super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        // Load parameters when the initial creation of the fragment is done
        this.index = (getArguments() != null) ? getArguments().getInt(INDEX)
                : -1;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        loadGroup(rootView);

        Button btnExec = (Button) rootView.findViewById(R.id.btnSave);
        btnExec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGroup(v);
            }
        });
        // Show the current page index in the view
        TextView tvIndex = (TextView) rootView.findViewById(R.id.tvIndex);
        ImageView img = (ImageView) rootView.findViewById(R.id.imageView);
        tvIndex.setText(String.valueOf(group.getId())+" : "+group.getName());

       // int intDrawable=getResId("back0", Drawable.class);
        int intDrawable= getResourceId("back"+String.valueOf(index), "drawable", activ.getPackageName());
        if(intDrawable==-1)intDrawable= R.drawable.back0;

        Resources res = rootView.getResources();
        Drawable f = null;
       // f = ResourcesCompat.getDrawable(rootView.getResources(),R.drawable.back0, null);
        f = ResourcesCompat.getDrawable(rootView.getResources(),intDrawable, null);
        //     tvIndex.setBackgroundDrawable(f);
        img.setImageDrawable(f);
        return rootView;
        //return inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    private void loadGroup(ViewGroup rootView) {
        FileSystem admin = FileSystemAdapter.getFS();
        ArrayList<Jugador> j = admin.getPlayers();

        listView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view_group);
        LinearLayoutManager llmanager = new LinearLayoutManager(getActivity());
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llmanager);
        RowsController rowsController = new RowsController();
        rows = rowsController.getRows(j);
        adapter = new RecicleGroupAdapter(rows);
        listView.setAdapter(adapter);

        group = admin.getGroup(index);
        rowsController.chechAllId(group.getJugadores(),rows);

    }

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
         //   e.printStackTrace();
            return -1;
        }
    }

    public void saveGroup (View view) {
        FileSystem admin = FileSystemAdapter.getFS();
        RowsController rowsController = new RowsController();
        ArrayList<Jugador> j = rowsController.rowsChecked(rows);
        admin.onlyPersist(j,group.getId());
    }


}
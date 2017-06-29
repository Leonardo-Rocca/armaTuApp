package com.example.lrocca.myapplication.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.example.lrocca.myapplication.Adapters.MyFragmentPagerAdapter;
import com.example.lrocca.myapplication.Persistencia.FileSystem;
import com.example.lrocca.myapplication.Persistencia.FileSystemAdapter;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Slide.ScreenSlidePageFragment;
import com.example.lrocca.myapplication.Slide.ZoomOutPageTransformer;
import com.example.lrocca.myapplication.modelo.Grupo;
import com.example.lrocca.myapplication.modelo.Jugador;

import java.util.ArrayList;

public class SwipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MyFragmentPagerAdapter pagerAdapter;
    ViewPager pager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addFragments();
            }
        });


        // Instantiate a ViewPager
        this.pager = (ViewPager) this.findViewById(R.id.pager);

        // Create an adapter with the fragments we show on the ViewPager
        //addFragments();
        //this.removeFragment();
    }

    private void addFragments() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager());

        ArrayList<Grupo> groupsNames = FileSystemAdapter.getFS().getEmptyGroups();

        for (int i =0;i<groupsNames.size();i++){
            adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, i,this));
        }
    //    adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 0,this));
   //     adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 1, this));
    /*    adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 2, this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 3, this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 4, this));*/
        this.pager.setAdapter(adapter);

        this.pager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void removeFragment() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initGroups() {
        FileSystem fs = FileSystemAdapter.getFS();
        fs.createtGroup(new Grupo("Ninguno", new ArrayList<Jugador>(),0));
        fs.createtGroup(new Grupo("Todos", FileSystemAdapter.getFS().getPlayers(),1));
        fs.createtGroup(fs.getDummyGroup());
    }
}

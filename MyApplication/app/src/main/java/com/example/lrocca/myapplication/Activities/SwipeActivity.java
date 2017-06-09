package com.example.lrocca.myapplication.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lrocca.myapplication.Adapters.MyFragmentPagerAdapter;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Slide.ScreenSlidePageFragment;
import com.example.lrocca.myapplication.Slide.ZoomOutPageTransformer;

public class SwipeActivity extends AppCompatActivity {

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
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 0,this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 1, this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 2, this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 3, this));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(-1, 4, this));
        this.pager.setAdapter(adapter);

        this.pager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void removeFragment() {

    }

}

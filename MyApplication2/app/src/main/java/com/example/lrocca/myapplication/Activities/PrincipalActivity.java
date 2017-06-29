package com.example.lrocca.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lrocca.myapplication.Adapters.MyFragmentPagerAdapter;
import com.example.lrocca.myapplication.ConfigurationFragment;
import com.example.lrocca.myapplication.R;

public class PrincipalActivity extends AppCompatActivity implements ConfigurationFragment.OnFragmentConfigListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void loadPlayers (View view) {
        Intent i = new Intent(this, ABMPlayerActivity.class );
        startActivity(i);
    }

    public void selection (View view) {
        Intent i = new Intent(this, SelectionActivity.class );
        startActivity(i);
    }
    public void configuration (View view) {
        Intent i = new Intent(this, SwipeActivity.class );
        startActivity(i);return;

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
    //    if (findViewById(R.id.frag_configuration) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
          /*  if (savedInstanceState != null) {
                return;
            }*/
/*       MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

            // Create a new Fragment to be placed in the activity layout
            ConfigurationFragment firstFragment = new ConfigurationFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_principal, firstFragment).commit();
    //   }*/
    }
}

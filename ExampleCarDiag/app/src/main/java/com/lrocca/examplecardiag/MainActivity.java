package com.lrocca.examplecardiag;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lrocca.examplecardiag.persistence.DataBaseService;


public class MainActivity extends AppCompatActivity {

    private DataBaseService db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseAdapter.setDatabase(new DataBaseService(this));
        db = DataBaseAdapter.getDatabase();
  //      carStateBtn = (Button) findViewById(R.id.car_state);
  //      errorCodesBtn = (Button) findViewById(R.id.error_codes);
 //       serviceFinderBtn = (Button) findViewById(R.id.service_finder);
    }

    public void showSolutions(View v) {
  //      db.insertDummySteps();
        startActivity(new Intent(this, StepsActivity.class));  // leo
    //    startActivity(new Intent(this, FragmentActivity.class));

    }


    private BluetoothAdapter getBluetoothAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

}

package com.example.lrocca.myapplication.Activities;

import android.app.Activity;
import android.widget.Adapter;

import com.example.lrocca.myapplication.Adapters.RecicleAdapter;
import com.example.lrocca.myapplication.Row;

import java.util.ArrayList;

/**
 * Created by lrocca on 07/06/2017.
 */
public class Iterator {
    private final ArrayList<Row> rows;
    private Activity activity;
    private RecicleAdapter adapter;

    public Iterator(SelectionActivity act, ArrayList<Row> list, RecicleAdapter adapter) {
        setActivity(act);
        rows = list;
        setAdapter(adapter);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setAdapter(RecicleAdapter adapter) {
        this.adapter = adapter;
    }

    public RecicleAdapter getAdapter() {
        return adapter;
    }

    public ArrayList<Row> iterate() {
        ArrayList<Row> auxList = new ArrayList<>();
        for (int i = 0; i < auxList.size(); i++) {
            Row element= rows.get(i);
            if (this.execute(element)) auxList.add(element);
        }
        return auxList;
    }

    private boolean execute(Row row) {
        return true;
    }
}

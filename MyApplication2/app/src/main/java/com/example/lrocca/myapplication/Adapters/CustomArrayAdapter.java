package com.example.lrocca.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lrocca on 24/04/2017.
 */
public class CustomArrayAdapter extends ArrayAdapter<Row>
{
    private LayoutInflater layoutInflater;
    ArrayList<Row> internalRows = new ArrayList<Row>();
    private ArrayList<Holder> holders = new ArrayList<>();

    public CustomArrayAdapter(Context context, ArrayList<Row> objects)
    {
        super(context, 0, objects);
        internalRows.addAll(objects);
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            holder.setAdapter(this);
            convertView = layoutInflater.inflate(R.layout.listview_row, null);
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));
            holders.add(holder);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
        holder.setPosition(position);
        return convertView;
    }

    public void selectAll() {
        for (int i =0;i<holders.size();i++){
            holders.get(i).doCheck();
        }

    }

    static class Holder
    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;
        CustomArrayAdapter adapter;
        private int position;

        public TextView getTextViewTitle()
        {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle)
        {
            this.textViewTitle = textViewTitle;
            getTextViewTitle().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCheck();
                }
            });
            getCheckBox().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCheck();
                }});
        }

        private void doCheck() {
            this.getCheckBox().setChecked(!getCheckBox().isChecked());
            this.getSelected(getTextViewTitle().getText()).changeCheck();
        }

        private Row getSelected(CharSequence text) {
            List<Row> rows = adapter.geRows(); return rows.get(position);
        }

        public TextView getTextViewSubtitle()
        {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle)
        {
            this.textViewSubtitle = textViewSubtitle;
        }

        public CheckBox getCheckBox()
        {
            return checkBox;
        }
        public void setCheckBox(CheckBox checkBox)
        {
            this.checkBox = checkBox;
        }

        public void setAdapter(CustomArrayAdapter adapter) {
            this.adapter = adapter;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    private List<Row> geRows() {
        return internalRows;
    }

}

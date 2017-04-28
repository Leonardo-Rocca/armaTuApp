package com.example.lrocca.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lrocca on 24/04/2017.
 */
public class CustomArrayAdapter extends ArrayAdapter<Row>
{
    private LayoutInflater layoutInflater;
    List<Row> internalRows;
    private ArrayList<Holder> holders;

    public CustomArrayAdapter(Context context, List<Row> objects)
    {
        super(context, 0, objects);
        internalRows = objects;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();
            holder.setRows(internalRows);
            convertView = layoutInflater.inflate(R.layout.listview_row, null);
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        holders.add(holder);
        Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
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
        private List<Row> rows;

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
            getCheckBox().setChecked(!getCheckBox().isChecked());
            getSelected(getTextViewTitle().getText()).changeCheck();
        }

        private Row getSelected(CharSequence text) {
            for (int i =0;i<rows.size();i++){
                if (rows.get(i).getTitle().equals(text))return rows.get(i);
            }
            return null;
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

        public void setRows(List<Row> internalRows) {
            rows = internalRows;
        }
    }
}

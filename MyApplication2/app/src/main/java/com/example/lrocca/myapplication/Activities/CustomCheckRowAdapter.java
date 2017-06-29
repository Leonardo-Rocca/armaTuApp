package com.example.lrocca.myapplication.Activities;

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
 * Created by lrocca on 15/05/2017.
 */

public class CustomCheckRowAdapter extends ArrayAdapter<Row> {

    private final SelectionActivity activ;
    private LayoutInflater layoutInflater;

    public CustomCheckRowAdapter(Context context, ArrayList<Row> rows) {
        super(context, 0, rows);
        layoutInflater = LayoutInflater.from(context);
        activ= (SelectionActivity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.listview_row, null);
            holder.setCheckBox((CheckBox) convertView.findViewById(R.id.checkBox));
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.textViewTitle));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.textViewSubtitle));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Row row = getItem(position);
        holder.getTextViewTitle().setText(row.getTitle());
        holder.getTextViewSubtitle().setText(row.getSubtitle());
        holder.setContext(activ);
        holder.setPosition(position);
    //    row.setHolder(holder);
        return convertView;
    }

    }

    class Holder
    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;

        private int position;
        private SelectionActivity context;

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
            context.check(position);
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

        public void setContext(SelectionActivity context) {
            this.context = context;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }




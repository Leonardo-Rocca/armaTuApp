package com.example.lrocca.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lrocca.myapplication.Activities.ABMPlayerActivity;
import com.example.lrocca.myapplication.modelo.Jugador;
import com.example.lrocca.myapplication.R;

import java.util.ArrayList;

/**
 * Created by lrocca on 24/04/2017.
 */

public class CustomABMAdapter extends ArrayAdapter<Jugador>
{
    private LayoutInflater layoutInflater;

    private ArrayList<Holder> holders = new ArrayList<>();
    private ABMPlayerActivity activ;

    public CustomABMAdapter(Context context, ArrayList<Jugador> objects)
    {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        activ= (ABMPlayerActivity) context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.listview_playerabm, null);
            holder.setBtnModify((Button) convertView.findViewById(R.id.btModify));
            holder.setBtnDelete((Button) convertView.findViewById(R.id.btnDelete));
            holder.setTextView((TextView) convertView.findViewById(R.id.tvPlayer));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        //     ABMRow row = getItem(position);
        Jugador row = getItem(position);
        holder.getTextView().setText(row.toString() );
        holder.setContext(activ);
        holder.setPosition(position);
        holders.add(holder);
        return convertView;
    }




    static class Holder
    {
        private Button btnDelete;
        private TextView textView;
        Button btnModify;
        private int position;
        private ABMPlayerActivity context;


        public Button getBtnDelete() {
            return btnDelete;
        }

        public Button getBtnModify() {
            return btnModify;
        }

        public void setBtnModify(Button btnModify) {
            this.btnModify = btnModify;
            btnModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.modify(position);
                }
            });
        }

        public void setBtnDelete(Button btnDelete) {
            this.btnDelete = btnDelete;
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.delete(position);
                }
            });
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setContext(ABMPlayerActivity context) {
            this.context = context;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

}




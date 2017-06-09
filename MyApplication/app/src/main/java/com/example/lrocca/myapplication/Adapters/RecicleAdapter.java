package com.example.lrocca.myapplication.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lrocca.myapplication.Activities.SelectionActivity;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.modelo.Jugador;

import java.util.ArrayList;

/**
 * Created by lrocca on 16/05/2017.
 */
public class RecicleAdapter extends RecyclerView.Adapter<RecicleAdapter.ViewHolder> {
    private final Context activ;
    private ArrayList<Jugador> mDataset;
    private ArrayList<ViewHolder> holders=new ArrayList<>();


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View myView;
        private TextView mTextView;
        private SelectionActivity context;
        private int position;
        private boolean isChecked = false;

        public ViewHolder(final View convertView) {
            super(convertView);
            myView = convertView;
            initialice();
            convertView.setOnClickListener(this);
        }

        private void initialice() {
            mTextView = (TextView) myView.findViewById(R.id.textViewTitle);

          /*  mTextView = new TextView(context.getApplicationContext());
            mTextView.setBackgroundColor(Color.WHITE);
            LinearLayout contenedor = new LinearLayout(context.getApplicationContext());
            contenedor.addView(mTextView);*/

         /*   mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCheck();
                }
            });*/
        }

        private void doCheck() {
           // isChecked = !isChecked;
            //  Drawable f2 = ResourcesCompat.getDrawable(convertView.getResources(), R.drawable.cesped4, null);
            //  mTextView.setBackground(f2);
            context.check(getAdapterPosition());
            checkIfCorresponds();
        }

        public void setContext(Context context) {
            this.context = (SelectionActivity) context;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            doCheck();
        }

        public void checkIfCorresponds() {
            isChecked = context.amIChecked(getAdapterPosition());

            Resources res =myView.getResources();
            Drawable f = null;
            if(isChecked) f = ResourcesCompat.getDrawable(myView.getResources(), R.drawable.xtick, null);
            if(!isChecked)    f = null;
            mTextView.setCompoundDrawablesWithIntrinsicBounds(f,null,null,null);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecicleAdapter(Context c, ArrayList<Jugador> myDataset) {
        mDataset = myDataset;
        activ = c;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecicleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycle, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // params.setMargins(0,4,0,0);
       final ViewHolder vh = new ViewHolder(v);

        v.setLayoutParams(params);
        vh.setContext(activ);

        holders.add(vh);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());
        holder.checkIfCorresponds();
   //     holder.setPosition(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void selectAll() {
        for (int i = 0; i < holders.size(); i++) {
            holders.get(i).doCheck();
        }
    }
}



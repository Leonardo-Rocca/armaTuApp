package com.example.lrocca.myapplication.Slide;

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
import com.example.lrocca.myapplication.Adapters.RecicleAdapter;
import com.example.lrocca.myapplication.R;
import com.example.lrocca.myapplication.Row;
import com.example.lrocca.myapplication.modelo.Jugador;

import java.util.ArrayList;

/**
 * Created by lrocca on 27/06/2017.
 */
public class RecicleGroupAdapter extends RecyclerView.Adapter<RecicleGroupAdapter.ViewHolder> {
    private ArrayList<Row> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View myView;
        private TextView mTextView;
        private RecicleGroupAdapter context;
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
        }

        private void doCheck() {
            context.check(getAdapterPosition());
            checkIfCorresponds();
        }

        public void setContext(RecicleGroupAdapter context) {
            this.context = context;
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

    private boolean amIChecked(int adapterPosition) {
            return  mDataset.get(adapterPosition).isChecked();
    }

    private void check(int adapterPosition) {
        mDataset.get(adapterPosition).changeCheck();
    }

    public RecicleGroupAdapter(ArrayList<Row> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecicleGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycle, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // params.setMargins(0,4,0,0);
        final RecicleGroupAdapter.ViewHolder vh = new RecicleGroupAdapter.ViewHolder(v);

        v.setLayoutParams(params);
        vh.setContext(this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecicleGroupAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText((mDataset.get(position)).getName());
        holder.checkIfCorresponds();
        //     holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

package com.example.lrocca.myapplication.Slide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lrocca.myapplication.Activities.SwipeActivity;
import com.example.lrocca.myapplication.R;

import java.lang.reflect.Field;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 */
public class ScreenSlidePageFragment extends Fragment {

    private static final String INDEX = "index";
    private static SwipeActivity activ;
    //   private OnFragmentInteractionListener mListener;
    private int index;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    public static ScreenSlidePageFragment newInstance(int color, int index, SwipeActivity swipeActivity) {
       activ = swipeActivity;
        // Instantiate a new fragment
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //  super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        // Load parameters when the initial creation of the fragment is done
        this.index = (getArguments() != null) ? getArguments().getInt(INDEX)
                : -1;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        // Show the current page index in the view
        TextView tvIndex = (TextView) rootView.findViewById(R.id.tvIndex);
        ImageView img = (ImageView) rootView.findViewById(R.id.imageView);
        tvIndex.setText(String.valueOf(this.index));

       // int intDrawable=getResId("back0", Drawable.class);
        int intDrawable= getResourceId("back"+String.valueOf(index), "drawable", activ.getPackageName());
        if(intDrawable==-1)intDrawable= R.drawable.back0;

        Resources res = rootView.getResources();
        Drawable f = null;
       // f = ResourcesCompat.getDrawable(rootView.getResources(),R.drawable.back0, null);
        f = ResourcesCompat.getDrawable(rootView.getResources(),intDrawable, null);
        //     tvIndex.setBackgroundDrawable(f);
        img.setImageDrawable(f);
        return rootView;
        //return inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
    }

    public int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
         //   e.printStackTrace();
            return -1;
        }
    }
}
package com.example.lrocca.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.lrocca.myapplication.Adapters.MyFragmentPagerAdapter;
import com.example.lrocca.myapplication.Slide.ScreenSlidePageFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConfigurationFragment.OnFragmentConfigListener} interface
 * to handle interaction events.
 */
public class ConfigurationFragment extends Fragment {
    ViewPager pager = null;
    MyFragmentPagerAdapter pagerAdapter;


    private OnFragmentConfigListener mListener;

    public ConfigurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView =  inflater.inflate(R.layout.fragment_configuration, container, false);
        //Nuevos parametros para el view del fragmento
        RelativeLayout.LayoutParams params =    new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //Nueva Regla: EL fragmento estara debajo del boton add_fragment
        //    params.addRule(RelativeLayout.ALIGN_LEFT, true);
        //   params.addRule(RelativeLayout.ABOVE, R.id.lvPlayers);
        //Margenes: top:41dp
        params.setMargins(0,780,0,1);
        //Setear los parametros al view
        rootView.setLayoutParams(params);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentConfigListener) {
            mListener = (OnFragmentConfigListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentConfigListener {
    }
/*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}

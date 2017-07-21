package com.lrocca.examplecardiag;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lrocca.examplecardiag.fragments.MyFragmentPagerAdapter;
import com.lrocca.examplecardiag.fragments.ScreenSlidePageFragment;
import com.lrocca.examplecardiag.fragments.StepFragment;
import com.lrocca.examplecardiag.persistence.DataBaseService;

import java.util.ArrayList;


public class StepsActivity extends AppCompatActivity {
    MyFragmentPagerAdapter pagerAdapter;
    ViewPager pager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        // Instantiate a ViewPager
        this.pager = (ViewPager) this.findViewById(R.id.pager);

        // Create an adapter with the fragments we show on the ViewPager
        addFragments();
      //  addFrag2();
        //this.removeFragment();
    }

   private void addFragments() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
               getSupportFragmentManager());
       int id = 0;
//       ArrayList<Step> steps = new DataBaseService(this).getSteps(id);
       ArrayList<Step> steps = DataBaseAdapter.getDatabase().getSteps(id);
        for(Step s :steps){
            adapter.addFragment(StepFragment.newInstance(s, 0,this));
        }
//        adapter.addFragment(StepFragment.newInstance(new Step("s0","Revisar cuidadosamente el circuito del banco 1 / circuito de VCT, sistema de cableado y conectores, como lo indica el manual de reparación"), 0,this));
//       adapter.addFragment(StepFragment.newInstance(new Step("s1","Con el motor caliente, cheque la operación de la OCV"), 0,this));
//       adapter.addFragment(StepFragment.newInstance(new Step("s0","sustitución / repare o reemplace según sea necesario"), 0,this));
       this.pager.setAdapter(adapter);

        this.pager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void removeFragment() {

    }

}

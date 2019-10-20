package com.lc.testdemo.aidlTest.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lc.testdemo.R;
import com.lc.testdemo.aidlTest.fragment.BlankFragmentA;
import com.lc.testdemo.aidlTest.fragment.ItemFragment;
import com.lc.testdemo.aidlTest.fragment.dummy.DummyContent;

public class FragmentActivity extends AppCompatActivity implements
        ItemFragment.OnListFragmentInteractionListener,
        BlankFragmentA.OnFragmentInteractionListener {

    private ItemFragment itemFragment;
    private BlankFragmentA blankFragmentA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // person first commit
       FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();

       itemFragment = new ItemFragment();
       blankFragmentA = new BlankFragmentA();

       fragmentTransaction.add(R.id.fl,itemFragment,itemFragment.getClass().getSimpleName());
//       fragmentTransaction.add(R.id.fl,blankFragmentA,blankFragmentA.getClass().getSimpleName());

       fragmentTransaction.show(itemFragment).commit();

    }

    private void switchFragment(boolean isNext){
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        int animIn = !isNext?android.R.anim.slide_in_left:android.R.anim.slide_out_right;
        int animOut = isNext?android.R.anim.slide_in_left:android.R.anim.slide_out_right;
        fragmentTransaction.setCustomAnimations(animIn,animOut);
        fragmentTransaction.replace(R.id.fl,isNext?blankFragmentA:itemFragment).commit();
    }

    public void previous(View view) {
        switchFragment(false);
    }

    public void next(View view) {
        switchFragment(true);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d("4Test","item:"+item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

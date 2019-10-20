package com.lc.testdemo.aidlTest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lc.diyviewanimation.ViewAnimationManager;
import com.lc.testdemo.R;

/**
 * Created by Administrator on 2019/3/3.
 */

public class DIYViewAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyview_animation);

    }


    public void anim(View view){
        ViewAnimationManager viewAnimationManager = new ViewAnimationManager();
        viewAnimationManager.moveTo(0,0);
        viewAnimationManager.cubicTo(100,600,900,1000,200,1200);
//        viewAnimationManager.startAnimation(2000,view);
    }
}

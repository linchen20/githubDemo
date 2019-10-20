package com.lc.diyviewanimation;

import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/3.
 */

public class ViewAnimationManager {

    private List<PathPoint> pathPointList = new ArrayList<>();
    private View view;
    public void lineTo(float x,float y){
        pathPointList.add(new PathPoint(PathPoint.PATH_TYPE_LINE,x,y));
    }
    public void moveTo(float x,float y){
        pathPointList.add(new PathPoint(PathPoint.PATH_TYPE_MOVE,x,y));
    }

    public void cubicTo(float control1X,float control1Y,float control2X,float control2Y,float endX,float endY){
        pathPointList.add(new PathPoint(control1X,control1Y,control2X,control2Y,endX,endY));
    }


    public void startAnimation(int duration,View view){
        this.view  = view;
        ObjectAnimator animator = ObjectAnimator.ofObject(this,"animation",new PathPointTypeEvaluator(),pathPointList.toArray());
        animator.setDuration(duration);
        animator.start();
    }

    public void setAnimation(PathPoint pathPoint){
        view.setTranslationX(pathPoint.x);
        view.setTranslationY(pathPoint.y);
    }
}

package com.lc.diyviewanimation;

/**
 * Created by Administrator on 2019/3/3.
 */

public class PathPoint {

    public static final int PATH_TYPE_MOVE = 1;
    public static final int PATH_TYPE_LINE = 2;
    public static final int PATH_TYPE_CUBIC = 3;

    protected int type;

    protected float x, y;

    protected float control1X;
    protected float control1Y;
    protected float control2X;
    protected float control2Y;

    public PathPoint(int type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public PathPoint( float control1X, float control1Y, float control2X, float control2Y,float x, float y) {
        this.type = PATH_TYPE_CUBIC;
        this.x = x;
        this.y = y;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.control2X = control2X;
        this.control2Y = control2Y;
    }
}

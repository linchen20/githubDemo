package com.lc.diyviewanimation;

import android.animation.TypeEvaluator;
import android.graphics.Path;

/**
 * Created by Administrator on 2019/3/3.
 */

public class PathPointTypeEvaluator implements TypeEvaluator<PathPoint> {

    private float x, y;

    @Override
    public PathPoint evaluate(float fraction, PathPoint startPoint, PathPoint endPoint) {
        switch (endPoint.type) {
            case PathPoint.PATH_TYPE_CUBIC:
                float dis = 1 - fraction;
                x = (float) (startPoint.x * Math.pow(dis, 3) + 3 * endPoint.control1X * fraction * Math.pow(dis, 2)
                        + 3 * endPoint.control2X * Math.pow(fraction, 2) * dis + endPoint.x * Math.pow(fraction, 3));
                y = (float) (startPoint.y * Math.pow(dis, 3) + 3 * endPoint.control1Y * fraction * Math.pow(dis, 2)
                        + 3 * endPoint.control2Y * Math.pow(fraction, 2) * dis + endPoint.y * Math.pow(fraction, 3));
                break;
            case PathPoint.PATH_TYPE_LINE:
                x = startPoint.x + fraction * (endPoint.x - startPoint.x);
                y = startPoint.y + fraction * (endPoint.y - startPoint.y);
                break;
            case PathPoint.PATH_TYPE_MOVE:
                x = endPoint.x;
                y = endPoint.y;
                break;
        }
        return new PathPoint(PathPoint.PATH_TYPE_MOVE, x, y);
    }
}

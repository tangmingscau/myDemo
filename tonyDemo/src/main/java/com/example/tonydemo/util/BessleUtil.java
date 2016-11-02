package com.example.tonydemo.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.List;

/**
 * Created by tony on 16-10-10.
 */

public class BessleUtil {
    private static BessleUtil mInstance = null;
    private List<Point> mPoints = null;
    private List<Point> mMidPoints = null;
    private List<Point> mMidMidPoints = null;
    private List<Point> mControlPoints = null;

    public static BessleUtil getInstance() {
        if (mInstance == null) {
            mInstance = new BessleUtil();
        }
        return mInstance;
    }

    /**
     * @param mPoints        贝塞尔曲线点坐标
     * @param mMidPoints     中点对象
     * @param mMidMidPoints  中中点对象
     * @param mControlPoints 控制点对象
     */
    public void initPoint(List<Point> mPoints, List<Point> mMidPoints, List<Point> mMidMidPoints, List<Point> mControlPoints) {
        this.mPoints = mPoints;
        this.mMidPoints = mMidPoints;
        this.mMidMidPoints = mMidMidPoints;
        this.mControlPoints = mControlPoints;
        initMidPoints(mPoints);
        initMidMidPoints(mMidPoints);
        initControlPoints(mPoints, mMidPoints, mMidMidPoints);
    }

    public void drawBesselLine(Canvas canvas, Paint mPaint, Path mPath) {
        mPath.reset();
        for (int i = 0; i < mPoints.size(); i++) {
            if (i == 0) {// 第一条为二阶贝塞尔
                mPath.moveTo(mPoints.get(i).x, mPoints.get(i).y);// 起点
                mPath.quadTo(mControlPoints.get(i).x, mControlPoints.get(i).y,// 控制点
                    mPoints.get(i + 1).x, mPoints.get(i + 1).y);
            } else if (i < mPoints.size() - 2) {// 三阶贝塞尔
                mPath.cubicTo(mControlPoints.get(2 * i - 1).x, mControlPoints.get(2 * i - 1).y,// 控制点
                    mControlPoints.get(2 * i).x, mControlPoints.get(2 * i).y,// 控制点
                    mPoints.get(i + 1).x, mPoints.get(i + 1).y);// 终点
            } else if (i == mPoints.size() - 2) {// 最后一条为二阶贝塞尔
                mPath.quadTo(mControlPoints.get(mControlPoints.size() - 1).x, mControlPoints.get(mControlPoints.size() - 1).y,
                    mPoints.get(i + 1).x, mPoints.get(i + 1).y);// 终点
            }
        }
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 初始化中点集合
     */
    private void initMidPoints(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point midPoint = null;
            if (i == points.size() - 1) {
                return;
            } else {
                midPoint = new Point((points.get(i).x + points.get(i + 1).x) / 2, (points.get(i).y + points.get(i + 1).y) / 2);
            }
            mMidPoints.add(midPoint);
        }
    }

    /**
     * 初始化中点的中点集合
     */
    private void initMidMidPoints(List<Point> midPoints) {
        for (int i = 0; i < midPoints.size(); i++) {
            Point midMidPoint = null;
            if (i == midPoints.size() - 1) {
                return;
            } else {
                midMidPoint = new Point((midPoints.get(i).x + midPoints.get(i + 1).x) / 2, (midPoints.get(i).y + midPoints.get(i + 1).y) / 2);
            }
            mMidMidPoints.add(midMidPoint);
        }
    }

    /**
     * 初始化控制点集合
     */
    private void initControlPoints(List<Point> points, List<Point> midPoints, List<Point> midMidPoints) {
        for (int i = 0; i < points.size(); i++) {
            if (i == 0 || i == points.size() - 1) {
                continue;
            } else {
                Point before = new Point();
                Point after = new Point();
                before.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i - 1).x;
                before.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i - 1).y;
                after.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i).x;
                after.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i).y;
                mControlPoints.add(before);
                mControlPoints.add(after);
            }
        }
    }
}

package com.huang.androidarchitect;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Creater: jsm
 * Created Date: 2017/12/19 16:33
 * Description: 切割圆心
 */
public class RoundClipView extends View {
    private static final String TAG = "RoundClipView";
    private Paint mPaint, mClipPaint;
    private Shader mShader;
    private int mMeasureWidth, mMeasureHeight, mClipRadius, mClipCx, mClipCy;
    private RectF mProgressRect;
    private int mLeft, mTop, mRight, mBottom;

    public RoundClipView(Context context) {
        this(context, null);
    }

    public RoundClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(R.color.circle_border));
        mClipPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClipPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 新建一个线性渐变，前两个参数是渐变开始的点坐标，第三四个参数是渐变结束的点的坐标。
        // 连接这2个点就拉出一条渐变线了，玩过PS的都懂。
        // 然后那个数组是渐变的颜色。
        // 下一个参数是渐变颜色的分布，如果为空，每个颜色就是均匀分布的。
        // 最后是模式，这里设置的是循环渐变
        if (null == mShader) {
            int beginAndEnd = Color.rgb(250, 250, 250);
            mShader = new LinearGradient(0, 0, getMeasuredWidth(), getMeasuredHeight(),
                    new int[]{beginAndEnd, Color.rgb(253, 253, 253), beginAndEnd},
                    null, Shader.TileMode.REPEAT);
        }
        mClipPaint.setShader(mShader);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mProgressRect) {
            mMeasureWidth = getMeasuredWidth();
            mMeasureHeight = getMeasuredHeight();

//            int realLeft = (mMeasureWidth * mLeft) / 480;
//            int realRight = (mMeasureWidth * mRight) / 480;
//            int realTop = (mMeasureHeight * mTop) / 640;
//            int realBottom = (mMeasureHeight * mBottom) / 640;

            int realLeft = 75;
            int realRight = mMeasureWidth - 75;
            int realTop = 75;
            int realBottom = mMeasureHeight - 75;

            mClipCx = (realLeft + realRight) / 2;
            mClipCy = (realTop + realBottom) / 2;
//            mClipCy += mClipCy / 2;
            Log.d(TAG, "top:" + mTop + "bottom:" + mBottom + "  mMeasureWidth:" + mMeasureWidth + "  mMeasureHeight:" + mMeasureHeight + "   mClipCx:" + mClipCx + "   mClipCy:" + mClipCy);
            mClipRadius = (realRight - realLeft) / 2;
            mProgressRect = new RectF(0, (mMeasureHeight - mMeasureWidth) / 2, mMeasureWidth, (mMeasureWidth + mMeasureHeight) / 2);
        }
//        canvas.drawRect(0, 0, mMeasureWidth, mMeasureHeight, mClipPaint);
        canvas.drawCircle(mClipCx, mClipCy, mClipRadius + 10, mPaint);
        canvas.drawCircle(mClipCx, mClipCy, mClipRadius, mClipPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        for (int degree = 0; degree < 360; degree += 5) {

            float statX = (float) (mClipCx + (mClipRadius + 5) * Math.sin(Math.toRadians(degree)));
            float endX = (float) (mClipCx + (mClipRadius + 25) * Math.sin(Math.toRadians(degree)));
            float startY = (float) (mClipCx + (mClipRadius + 5) * Math.cos(Math.toRadians(degree)));
            float endY = (float) (mClipCx + (mClipRadius + 25) * Math.cos(Math.toRadians(degree)));
            canvas.drawLine(statX, startY, endX, endY, mPaint);

        }
    }

    public void setRect(int left, int top, int right, int bottom) {
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }

}

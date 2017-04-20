package com.lenny.framedemo.compent.ui.widget;

/**
 * 用途：
 * Created by milk on 16/8/31.
 * 邮箱：649444395@qq.com
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lenny.framedemo.R;


public class ProgressImage extends ImageView {
    private float maxRadius;
    private int pointColor = Color.BLACK;

    Point[] points = null;
    private float minRadius;

    public float mAngle;
    private ValueAnimator animator;


    public ProgressImage(Context context) {
        super(context);
        init(null, 0);
    }

    public ProgressImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ProgressImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Paint p = new Paint();
        p.setColor(pointColor);
        p.setAntiAlias(true);
        for (Point point : points) {
            float angle = point.angle + mAngle;
            float radius = point.radius;
            if (angle >= 0 && angle <= 360) {
                canvas.save();
                p.setAlpha((int) (75 + 180 * (360 - angle) / 360));
                radius = ((0.3f * radius)) + (0.7f * radius * (360 - angle) / 360);
                canvas.rotate(point.angle + mAngle);
                canvas.drawCircle(0, -(getHeight() / 2 - maxRadius), radius, p);
                canvas.restore();
            }
        }
        canvas.restore();
        super.onDraw(canvas);
    }

    private void init(AttributeSet attrs, int defStyle) {

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        float defaultMinRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.5f, metrics);
        float defaultMaxRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3.67f, metrics);
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ProgressImage, defStyle, 0);
        minRadius = a.getDimension(
                R.styleable.ProgressImage_minRadius, defaultMinRadius);
        maxRadius = a.getDimension(
                R.styleable.ProgressImage_maxRadius,
                defaultMaxRadius);

        pointColor = a.getColor(R.styleable.ProgressImage_pointColor, Color.BLACK);


        a.recycle();
        float step = (maxRadius - minRadius) / 7;
        points = new Point[]{
                new Point(minRadius, -0),
                new Point(minRadius + step, -50),
                new Point(minRadius + 2 * step, -100),
                new Point(minRadius + 3 * step, -150),
                new Point(minRadius + 4 * step, -200),
                new Point(minRadius + 5 * step, -250),
                new Point(minRadius + 6 * step, -300),
                new Point(minRadius + 7 * step, -350)
        };
    }

    class Point {
        float radius;
        float angle;

        public Point(float radius, int angle) {
            this.radius = radius;
            this.angle = angle;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator = ValueAnimator.ofFloat(0f, 740f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAngle = (float) valueAnimator.getAnimatedValue();
                postInvalidate();

            }
        });
        animator.setDuration(3500);
        animator.setRepeatCount(-1);
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }
}


package com.jay.semicirclerectview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jay.semicirclerectview.R;

/**
 * Created by Q.Jay on 2016/5/6 22:50
 *
 * @version 1.0.0
 */
public class SemiCircleRectView extends TextView {
    private static final String TAG = "SemiCircleRectView";
    private int backgroundColor;

    public SemiCircleRectView(Context context) {
        this(context, null);
    }

    public SemiCircleRectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SemiCircleRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SemiCircleRectView, defStyleAttr, 0);
        backgroundColor = typedArray.getColor(R.styleable.SemiCircleRectView_backgroundColor, Color.TRANSPARENT);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(new SemiCircleRectDrawable());
        } else {
            setBackgroundDrawable(new SemiCircleRectDrawable());
        }
    }

    class SemiCircleRectDrawable extends Drawable {
        private final Paint mPaint;
        private RectF rectF;

        public SemiCircleRectDrawable() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(backgroundColor);
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            if (rectF == null) {
                rectF = new RectF(left, top, right, bottom);
            } else {
                rectF.set(left, top, right, bottom);
            }
        }

        @Override
        public void draw(Canvas canvas) {
            float R = rectF.bottom / 2;
            if (rectF.right < rectF.bottom) {
                R = rectF.right / 2;
            }
            canvas.drawRoundRect(rectF, R, R, mPaint);
        }

        @Override
        public void setAlpha(int alpha) {
            mPaint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            mPaint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }
}

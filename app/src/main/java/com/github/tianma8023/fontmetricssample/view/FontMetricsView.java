package com.github.tianma8023.fontmetricssample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.github.tianma8023.fontmetricssample.R;

/**
 * Created by Tianma on 2018/1/20.
 */

public class FontMetricsView extends View {

    public static final int DEFAULT_TEXT_SIZE_PX = 150;
    private static final int DEFAULT_STROKE_WIDTH_PX = 4;

    private boolean mTopLineVisible = true;
    private boolean mAscentLineVisible = true;
    private boolean mBaseLineVisible = true;
    private boolean mDescentLineVisible = true;
    private boolean mBottomLineVisile = true;

    private String mText;
    private float mTextSize;
    private float mLineStrokeWidth;

    private TextPaint mTextPaint;
    private Paint.Align mTextAlign = Paint.Align.LEFT;

    private Paint mLinePaint;

    private float mWidth; // View width
    private float mHeight; // View height

    private Paint.FontMetrics mFontMetrics;

    private Context mContext;

    public FontMetricsView(Context context) {
        super(context);
        init(context);
    }

    public FontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mTextSize = DEFAULT_TEXT_SIZE_PX;
        mLineStrokeWidth = DEFAULT_STROKE_WIDTH_PX;

        // init text paint
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        refreshTextPaint();

        // init line paint
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(mLineStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawImpl(canvas);
    }

    // draw text and lines
    private void drawImpl(Canvas canvas) {

        float startX = getPaddingLeft();
        // baseLine在可绘制区域中间
        float startY = getPaddingTop() + (mHeight - getPaddingTop() - getPaddingBottom()) / 2;
        float stopX = mWidth - getPaddingRight();

        float baseLineY = startY; // baseLine的X坐标

        // draw text
        canvas.drawText(mText, startX, baseLineY, mTextPaint);

        // draw lines
        if (mTopLineVisible) { // drop top line
            // top线的y坐标 = baseLineY + fontMetrics.top
            startY = baseLineY + mFontMetrics.top;
            mLinePaint.setColor(ContextCompat.getColor(mContext, R.color.red));
            canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
        }

        if (mAscentLineVisible) { // draw ascent line
            // ascent线的y坐标 = baseLineY + fontMetrics.ascent
            startY = baseLineY + mFontMetrics.ascent;
            mLinePaint.setColor(ContextCompat.getColor(mContext, R.color.teal));
            canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
        }

        if (mBaseLineVisible) { // draw base line
            startY = baseLineY;
            mLinePaint.setColor(ContextCompat.getColor(mContext, R.color.purple));
            canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
        }

        if (mDescentLineVisible) { // draw descent line
            // descent线的y坐标 = baseLineY + fontMetrics.descent
            startY = baseLineY + mFontMetrics.descent;
            mLinePaint.setColor(ContextCompat.getColor(mContext, R.color.indigo));
            canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
        }

        if (mBottomLineVisile) { // draw bottom line
            // bottom线的y坐标 = baseLineY + fontMetrics.bottom
            startY = baseLineY + mFontMetrics.bottom;
            mLinePaint.setColor(ContextCompat.getColor(mContext, R.color.green));
            canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
        }
    }

    private void refreshTextPaint() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(mTextAlign);
        // acquire FontMetrics obj
        mFontMetrics = mTextPaint.getFontMetrics();
    }

    public void setText(String text) {
        mText = text;
        invalidate();
    }

    public void setTopLineVisible(boolean topLineVisible) {
        mTopLineVisible = topLineVisible;
        invalidate();
    }

    public void setAscentLineVisible(boolean ascentLineVisible) {
        mAscentLineVisible = ascentLineVisible;
        invalidate();
    }

    public void setBaseLineVisible(boolean baseLineVisible) {
        mBaseLineVisible = baseLineVisible;
        invalidate();
    }

    public void setDescentLineVisible(boolean descentLineVisible) {
        mDescentLineVisible = descentLineVisible;
        invalidate();
    }

    public void setBottomLineVisible(boolean bottomLineVisile) {
        mBottomLineVisile = bottomLineVisile;
        invalidate();
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
        refreshTextPaint();
        invalidate();
    }

    public void setTextAlign(Paint.Align align) {
        mTextAlign = align;
        refreshTextPaint();
        invalidate();
    }
}

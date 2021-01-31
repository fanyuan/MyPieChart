package com.convert.mypiechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.Paint.Style.FILL;
import static android.graphics.Paint.Style.STROKE;

/**
 * 饼图进度view
 */
public class PieChartView extends View {
    int progressWidth = 0;
    int progressColor = Color.YELLOW;
    /**
     * 扇形的角度
     */
    int sweepAngle = 200;

    private Paint paint;    //画笔
    private int w;          //View宽高
    private int h;
    private RectF rectF;    //矩形

    public PieChartView(Context context) {
        super(context);
        initPaint();
    }

    public void setSweepAngle(int angle){
        sweepAngle = angle;
        invalidate();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieChartView, 0, 0);
        if (typedArray != null) {
            progressWidth = typedArray.getDimensionPixelOffset(R.styleable.PieChartView_progressWidth,30);
            progressColor = typedArray.getColor(R.styleable.PieChartView_progressColor,0xF25B3D);//Color.YELLOW
            sweepAngle = typedArray.getInt(R.styleable.PieChartView_sweepAngle,200);
            typedArray.recycle();
            Log.d("ddebug","---PieChartView---offset=" + progressWidth);
        }
        initPaint();

    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieChartView, defStyleAttr, 0);
        if(a != null){
            progressWidth =a.getDimensionPixelOffset(R.styleable.PieChartView_progressWidth,30);
            progressColor = a.getColor(R.styleable.PieChartView_progressColor,0xF25B3D);//Color.YELLOW
            sweepAngle = a.getInt(R.styleable.PieChartView_sweepAngle,200);
            a.recycle();
            Log.d("ddebug","---PieChartView---f=" + progressWidth);
        }


    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
        Log.d("ddebug","---initPaint---");
        paint = new Paint();
        //设置画笔默认颜色
        paint.setColor(Color.WHITE);
        //设置画笔模式：填充
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        //初始化区域
        rectF = new RectF();
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width;
//        int height;
//        int size = MeasureSpec.getSize(widthMeasureSpec);
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
//        if(mode == MeasureSpec.EXACTLY){
//            width = size;
//        }else {
//            width = (int) (2 *(outsideRadius + progressWidth));
//        }
//
//        size = MeasureSpec.getSize(heightMeasureSpec);
//        mode = MeasureSpec.getMode(heightMeasureSpec);
//        if(mode == MeasureSpec.EXACTLY){
//            height = size;
//        }else {
//            height = (int) (2 * (outsideRadius + progressWidth));
//        }
//
//        setMeasuredDimension(200,280);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        //直径
        int diameter = Math.min(w,h) - progressWidth*2;

        rectF = new RectF( 0, 0, diameter , diameter);

        if(w > h){
            rectF.offset((w-diameter)/2,progressWidth);
        }else {
            rectF.offset(progressWidth,(h-diameter)/2);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int circleX = getWidth() / 2;
        int circleY = getHeight() / 2;
        //第一步:画背景(即内层圆)
        paint.setColor(progressColor); //设置圆的颜色
        paint.setStyle(STROKE); //设置空心
        paint.setStrokeWidth(progressWidth); //设置圆的宽度
        paint.setAntiAlias(true);  //消除锯齿

        //半径
        int radius = Math.min(w,h)/2;
        canvas.drawCircle(circleX, circleY, radius - progressWidth, paint); //画出圆 //canvas.drawCircle(circleX, circleY, getWidth()/2 - progressWidth, paint); //画出圆

        paint.setStyle(FILL);
        paint.setColor(progressColor);
        canvas.drawArc(rectF,-90,sweepAngle,true,paint);//画圆弧，这个时候，绘制没有经过圆心

    }
}

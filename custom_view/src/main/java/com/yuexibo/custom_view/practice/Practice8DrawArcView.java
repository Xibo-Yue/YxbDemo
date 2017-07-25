package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    Paint circlePaint;

    public Practice8DrawArcView(Context context) {
        this(context, null);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //三点钟方向为0度
        RectF rectF = new RectF(20, 20, 200, 200);
        canvas.drawArc(rectF, 270, -90, true, circlePaint); //扇形
        //第二个参数起始角度,三点钟方向为0度
        //第三个参数为转过的角度
        //第四个参数为圆弧起点和终点是否连接圆心

        RectF rectF1 = new RectF(100, 100, 300, 300);
        canvas.drawArc(rectF1, 270, -90, false, circlePaint);// 绘制弧形

        RectF rectF2 = new RectF(300, 300, 600, 600);
        circlePaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF2, 270, -90, false, circlePaint);// 绘制不封口的弧形
//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
    }
}

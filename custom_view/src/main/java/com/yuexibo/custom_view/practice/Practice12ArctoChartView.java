package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lvzishen on 17/7/21.
 */
public class Practice12ArctoChartView extends View {
    Paint circlePaint;

    public Practice12ArctoChartView(Context context) {
        this(context, null);
    }

    public Practice12ArctoChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice12ArctoChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.lineTo(50, 50);
        RectF rectF = new RectF(120, 120, 220, 220);
        path.arcTo(rectF, 270, 90, true);
        canvas.drawPath(path, circlePaint);

        Path path2 = new Path();
        path2.moveTo(250, 250);
        path2.lineTo(280, 280);
        RectF rectF1 = new RectF(300, 300, 400, 400);
        path2.arcTo(rectF1, 270, 90, false);
        canvas.drawPath(path2, circlePaint);
        //addArc	添加一个圆弧到path	直接添加一个圆弧到path中
        //arcTo	添加一个圆弧到path	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
        //arcTo参数  forceMoveTo
        //forceMoveTo	含义	等价方法
        //true	将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点	public void addArc (RectF oval, float startAngle, float sweepAngle)
        //false(默认)	不移动，而是连接最后一个点与圆弧起点	public void arcTo (RectF oval, float startAngle, float sweepAngle)
    }
}

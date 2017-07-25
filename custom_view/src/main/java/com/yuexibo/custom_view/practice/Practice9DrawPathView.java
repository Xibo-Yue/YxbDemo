package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    Paint circlePaint;

    public Practice9DrawPathView(Context context) {
        this(context, null);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        RectF rectF = new RectF(20, 20, 200, 200);
        path.addArc(rectF, 135, 225);
        RectF rectF1 = new RectF(200, 20, 380, 200);
        path.arcTo(rectF1, 180, 225);
        //addArc	添加一个圆弧到path	直接添加一个圆弧到path中
        //arcTo	添加一个圆弧到path	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
        //arcTo参数  forceMoveTo
        //forceMoveTo	含义	等价方法
        //true	将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点	public void addArc (RectF oval, float startAngle, float sweepAngle)
        //false(默认)	不移动，而是连接最后一个点与圆弧起点	public void arcTo (RectF oval, float startAngle, float sweepAngle)

        path.lineTo(200, 300); //连接到X,Y坐标
        path.close();//使path的起点和终点连接在一起构成一个封闭图形   //每一个add都会重置path起点,arcTo则不会重置path起点
        //close() 和 lineTo(起点坐标) 是完全等价的。
        canvas.drawPath(path, circlePaint);

        circlePaint.setStyle(Paint.Style.STROKE);
        path.moveTo(300, 300);//将path起点移动到X,Y
        path.lineTo(500, 500); // 由当前位置 (0, 0) 向 (500, 500) 画一条直线
        path.rLineTo(100, 0); // 由当前位置 (500, 500) 向正右方 100 像素的位置画一条直线
        canvas.drawPath(path, circlePaint);

//        Path path1 = new Path();
//        RectF rectF11 = new RectF(600, 20, 800, 200);
//        path1.setFillType(Path.FillType.EVEN_ODD);
//        path1.addRoundRect(rectF11, 10, 10, Path.Direction.CW);
//
//        Path path2 = new Path();
//        RectF rectF2 = new RectF(50, 50, 300, 300);
//        path2.addRoundRect(rectF2, 10, 10, Path.Direction.CW);
//
//        Path path3 = new Path();
//        RectF rectF3 = new RectF(100, 100, 500, 500);
//        path3.addRoundRect(rectF3, 10, 10, Path.Direction.CW);
//        path1.addPath(path2);
////        path.addPath(path3);
//        canvas.drawPath(path1, circlePaint);
//        canvas.drawPath(path3, circlePaint);
//        练习内容：使用 canvas.drawPath() 方法画心形
    }
}

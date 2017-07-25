package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice7DrawRoundRectView extends View {
    Paint circlePaint;

    public Practice7DrawRoundRectView(Context context) {
        this(context, null);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(20, 20, 200, 200);
        canvas.drawRoundRect(rectF, 30, 30, circlePaint);//第三个参数圆角的横向半径,第四个参数圆角的纵向半径

        RectF rect1 = new RectF(20, 300, 200, 350);
        canvas.drawRoundRect(rect1, 50 / 2, 50 / 2, circlePaint); //圆头矩形
//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
    }
}

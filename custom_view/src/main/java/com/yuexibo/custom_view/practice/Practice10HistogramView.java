package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
    Paint whitePaint;
    Paint greenPaint;
    int padding = 15;//数据之间的间隔

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(26f);
        whitePaint.setStyle(Paint.Style.STROKE);
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        //画坐标系
        canvas.drawLine(width * 0.1f, height * 0.1f, width * 0.1f, height * 0.8f, whitePaint);
        canvas.drawLine(width * 0.1f, height * 0.8f, width * 0.9f, height * 0.8f, whitePaint);
        String[] lables = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
        int[] rectHeight = {10, 25, 25, 105, 180, 220, 85};
        int firstPoint = (int) (width * 0.1f + padding);//X坐标起始点
        int oneWidth = (int) (((width * 0.9f - width * 0.1f) / lables.length) - padding);//一个矩形的宽

        for (int i = 0; i < lables.length; i++) {
            Rect rect = new Rect();
            whitePaint.getTextBounds(lables[i], 0, lables[i].length(), rect);
            int w = rect.width();
//            int h = rect.height();
            canvas.drawText(lables[i], firstPoint + i * oneWidth + i * padding + (oneWidth / 2 - w / 2), height * 0.8f + 20, whitePaint);//text
            //rect
            RectF rectF = new RectF(firstPoint + i * oneWidth + i * padding, height * 0.8f - rectHeight[i], firstPoint + (i + 1) * oneWidth + i * padding, height * 0.8f);
            canvas.drawRect(rectF, greenPaint);
        }

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
    }
}

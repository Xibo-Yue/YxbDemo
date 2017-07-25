package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yuexibo.custom_view.R;


/**
 * Created by lvzishen on 17/7/21.
 */
public class Practice14BitmapAndText extends View {
    Paint circlePaint;
    Paint textPaint;
    String text = "lvzishen";

    public Practice14BitmapAndText(Context context) {
        this(context, null);
    }

    public Practice14BitmapAndText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice14BitmapAndText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sample_histogram);
        canvas.drawBitmap(bitmap, 200, 100, circlePaint);//第二个参数文字的左上角X轴坐标,第三个参数文字的左上角Y轴坐标

        textPaint.setTextSize(18);
        canvas.drawText(text, 100, 25, textPaint); //第二个参数文字的左上角X轴坐标,第三个参数文字的左上角Y轴坐标
        textPaint.setTextSize(36);
        canvas.drawText(text, 100, 70, textPaint);
        textPaint.setTextSize(60);
        canvas.drawText(text, 100, 145, textPaint);
        textPaint.setTextSize(84);
        canvas.drawText(text, 100, 240, textPaint);
    }
}

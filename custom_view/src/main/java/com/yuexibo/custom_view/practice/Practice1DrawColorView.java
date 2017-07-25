package com.yuexibo.custom_view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yuexibo.custom_view.R;


public class Practice1DrawColorView extends View {

    private Context context;
    Paint greenPaint;

    public Practice1DrawColorView(Context context) {
        this(context, null);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.YELLOW);
        canvas.drawColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        练习内容：使用 canvas.drawColor() 方法把 View 涂成黄色
//        黄色： Color.YELLOW
    }
}

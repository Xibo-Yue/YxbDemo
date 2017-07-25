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
public class Practice13PathCWCCW extends View {
    Paint circlePaint;

    public Practice13PathCWCCW(Context context) {
        this(context, null);
    }

    public Practice13PathCWCCW(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice13PathCWCCW(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//反锯齿
        circlePaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        RectF rectF = new RectF(20, 20, 100, 100);
        path.setFillType(Path.FillType.EVEN_ODD);
        //FillType EVEN_ODD
        //         WINDING
        //         INVERSE_EVEN_ODD 与EVEN_ODD相反效果
        //         INVERSE_WINDING  与WINDING相反效果
        path.addRoundRect(rectF, 10, 10, Path.Direction.CW);

        Path path1 = new Path();
        RectF rectF1 = new RectF(50, 50, 300, 300);
        path1.addRoundRect(rectF1, 10, 10, Path.Direction.CW);

        Path path2 = new Path();
        RectF rectF2 = new RectF(100, 100, 500, 500);
        path2.addRoundRect(rectF2, 10, 10, Path.Direction.CW);
        path.addPath(path1);
//        path.addPath(path3);
        canvas.drawPath(path, circlePaint);
        canvas.drawPath(path2, circlePaint);
    }
}

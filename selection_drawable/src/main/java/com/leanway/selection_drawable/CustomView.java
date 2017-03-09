package com.leanway.selection_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * 自定义View
 */
public class CustomView extends View   /*extends Button*/
{
    private static String TAG = "TackTextView";

    private Context mContext = null;

    private Drawable mBackground = null;
    private boolean mBGSizeChanged = true;;   //视图View布局(layout)大小是否发生变化

    public CustomView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
        initStateListDrawable(); // 初始化图片资源
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        mContext = context;
        initStateListDrawable(); // 初始化图片资源
    }

    public CustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        initStateListDrawable(); // 初始化图片资源
    }

    // 初始化图片资源
    private void initStateListDrawable()
    {
        //有两种方式获取我们的StateListDrawable对象：
        // 获取方式一、手动构建一个StateListDrawable对象
        StateListDrawable statelistDrawable = new StateListDrawable();

        int pressed = android.R.attr.state_pressed;
        int windowfocused = android.R.attr.state_window_focused;
        int enabled = android.R.attr.state_enabled;
        int stateFoucesd = android.R.attr.state_focused;
        //匹配状态时，是一种优先包含的关系。
        // "-"号表示该状态值为false .即不匹配
        statelistDrawable.addState(new int[] { pressed, windowfocused }, mContext.getResources().getDrawable(R.drawable.btn_power_on_pressed));
        statelistDrawable.addState(new int[]{ -pressed, windowfocused }, mContext.getResources().getDrawable(R.drawable.btn_power_on_nor));

        //更多其他状态属性
        //stateD.addState(new int[]{pressed , windowfocused },mContext.getResources().getDrawable(R.drawable.btn_dialog_selected) )
        //stateD.addState(View.EMPTY_STATE_SET, mContext.getResources().getDrawable(R.drawable.btn_dialog_normal));
        //statelistDrawable.addState(new int[] { stateFoucesd, windowfocused }, null);
        mBackground = statelistDrawable;

        //必须设置回调，当改变状态时，会回掉该View进行invalidate()刷新操作.
        mBackground.setCallback(this);
        //取消默认的背景图片，因为我们设置了自己的背景图片了，否则可能造成背景图片重叠。
        this.setBackgroundDrawable(null);

        // 获取方式二、、使用XML获取StateListDrawable对象
        // mBackground = mContext.getResources().getDrawable(R.drawable.view_background);

    }
    //    //在View类中setFrame是隐藏方法 @hide , 而在TexitView/Button类时可以继承该方法
    //    protected boolean setFrame(int l , int t , int r ,int b){
    //
    //        if(getLeft() != l || getTop() != t || getRight()!= r || getBottom() != b){
    //            mBGSizeChanged = true
    //        }
    //
    //        return super.setFrame(l ,  t ,  l , b);
    //    }

    protected void drawableStateChanged()
    {
        Log.i(TAG, "drawableStateChanged");
        Drawable d = mBackground;
        if (d != null && d.isStateful())
        {
            d.setState(getDrawableState());
            //Log.i(TAG, "drawableStateChanged  and is 111");
        }
        //         //打印各种状态值
        //        int[] states = d.getState();
        //
        //        for (int i = 0; i < states.length; i++)
        //        {
        //            System.out.println("states[" + i + "]" + states[i]);
        //        }
        //        Log.i(TAG, "drawableStateChanged  and is 222");
        super.drawableStateChanged();
    }
    //验证图片是否相等 , 在invalidateDrawable()会调用此方法，我们需要重写该方法。
    protected boolean verifyDrawable(Drawable who)
    {
        return who == mBackground || super.verifyDrawable(who);
    }
    //draw()过程，绘制背景图片...
    public void draw(Canvas canvas)
    {
        Log.i(TAG, " draw -----");
        if (mBackground != null)
        {
            if(mBGSizeChanged)
            {
                //设置边界范围
                mBackground.setBounds(0, 0, getRight() - getLeft(), getBottom() - getTop());
                mBGSizeChanged = false ;
            }
            if ((getScrollX() | getScrollY()) == 0)  //是否偏移
            {
                mBackground.draw(canvas); //绘制当前状态对应的图片
            }
            else
            {
                canvas.translate(getScrollX(), getScrollY());
                mBackground.draw(canvas); //绘制当前状态对应的图片
                canvas.translate(-getScrollX(), -getScrollY());
            }
        }
        super.draw(canvas);
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        Paint paint  = new Paint();
        paint.setTextSize(16);
        paint.setColor(Color.YELLOW);
        canvas.drawText("自定义View", 0, 20, paint);
        canvas.drawText("触摸我可以改变图片背景", 0, 40, paint);
        canvas.restore();
    }
}

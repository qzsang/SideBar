package com.example.sidebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.List;

public class SideBar extends View {
    // 触摸事件  
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;  
    private List<String> letters ;
    private int choose = -1;// 选中  
    private Paint paint = new Paint();

    int textSize = 10;
    int itemHeight = 15;//设置item高度
    String textColor = "#939393";
    String textSelectColor = "#FE644A";
    private boolean dataIsInit = false;//数据是否初始化

    float y0; //高度y 基点
    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init ();
    }  
  
    public SideBar(Context context, AttributeSet attrs) {  
        super(context, attrs);
        init ();
    }  
  
    public SideBar(Context context) {  
        super(context);
        init ();
    }


    public void setLetters(List<String> letters) {
        this.letters = letters;
        if (this.letters != null && this.letters.size() > 0) {
            dataIsInit = true;
        } else {
            dataIsInit = false;
        }
        invalidate();
    }

    public void init () {
        textSize = DensityUtil.dip2px(getContext(),textSize);
        itemHeight = DensityUtil.dip2px(getContext(),itemHeight);
    }


    /** 
     * 重写这个方法 
     */  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!dataIsInit) {
            return;
        }
        // 获取焦点改变背景颜色.  
        int height = getHeight();// 获取对应高度  
        int width = getWidth(); // 获取对应宽度
        y0 = (height - itemHeight * letters.size()) / 2 ;
        for (int i = 0; i < letters.size(); i ++) {
            paint.setColor(Color.parseColor(textColor));
            // paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(textSize);
            // 选中的状态
            if (i == choose) {
                paint.setColor(Color.parseColor(textSelectColor));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(letters.get(i)) / 2;
            float yPos = itemHeight * (i + 1) + y0;
            canvas.drawText(letters.get(i), xPos, yPos, paint);
            paint.reset();// 重置画笔
        }

       // int singleHeight = height / letters.size();// 获取每一个字母的高度

    }  
  
    @Override  
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!dataIsInit) {
            return false;
        }
        final int action = event.getAction();  
        final float y = event.getY();// 点击y坐标  
        final int oldChoose = choose;  
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;  
        final int c = (int) ((y - y0) / itemHeight);// 得到触摸位置的item position
  
        switch (action) {  
        case MotionEvent.ACTION_UP:  
            choose = -1;//
            invalidate();  
            break;
  
        default:  
            if (oldChoose != c) {
                if (c >= 0 && c < letters.size()) {  
                    if (listener != null) {  
                        listener.onTouchingLetterChanged(letters.get(c));  
                    }  
                    choose = c;
                    invalidate();  
                }  
            }  
  
            break;  
        }  
        return true;  
    }  
  
    /** 
     * 向外公开的方法 
     *  
     * @param onTouchingLetterChangedListener 
     */  
    public void setOnTouchingLetterChangedListener(  
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {  
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;  
    }  
  
    /** 
     * 接口 
     *  
     * @author coder 
     *  
     */  
    public interface OnTouchingLetterChangedListener {  
         void onTouchingLetterChanged(String s);
    }  
  
}  
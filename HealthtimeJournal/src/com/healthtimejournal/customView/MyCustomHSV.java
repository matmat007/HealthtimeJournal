package com.healthtimejournal.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class MyCustomHSV extends HorizontalScrollView{
	
	public MyCustomHSV(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public MyCustomHSV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCustomHSV(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        // remove the fading as the HSV looks better without it
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Do not allow touch events.
        return false;
    }
 
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Do not allow touch events.
        return false;
    }
    
    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b){
    	super.onLayout(changed, l, t, r, b);
        this.scrollTo(getWidth()/4*3, 0);
    }
}

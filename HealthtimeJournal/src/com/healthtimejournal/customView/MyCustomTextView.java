package com.healthtimejournal.customView;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyCustomTextView extends TextView {
    public MyCustomTextView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomTextView(Context context) {
        super(context);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
            Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused) {
            super.onWindowFocusChanged(focused);
        }
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}

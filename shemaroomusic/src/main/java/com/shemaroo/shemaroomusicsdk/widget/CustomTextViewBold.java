package com.shemaroo.shemaroomusicsdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class CustomTextViewBold extends TextView {

    public CustomTextViewBold(Context context) {
        super(context);
        setTextViewFont(context);
    }

    public CustomTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextViewFont(context);
    }

    public CustomTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTextViewFont(context);
    }

    public void setTextViewFont(Context context) {
        if (!isInEditMode()) {
            Typeface myFont = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Roboto-Bold.ttf");

            this.setTypeface(myFont);
        }
    }
}
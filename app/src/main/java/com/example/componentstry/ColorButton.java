package com.example.componentstry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.StyleableRes;

public class ColorButton extends LinearLayout {

    @StyleableRes
    int index0 = 0;

    Button btn;
    Boolean toggle;

    public ColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.sample_color_button, this);

        int[] sets = {R.attr.color};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence colorAttr = typedArray.getText(index0);
        typedArray.recycle();

        btn = (Button) findViewById(R.id.btn);
        btn.setBackgroundColor(Color.BLACK);

        if (colorAttr != null && colorAttr.length() != 0)
            toggle = false;
        btn.setText("Click Me!");

        btn.setOnClickListener((View view) -> {
            toggle = !toggle;
            btn.setText(toggle ? colorAttr : "Click Me!");
            btn.setBackgroundColor(toggle ? Color.parseColor(colorAttr.toString()) : Color.BLACK);
        });
    }
}
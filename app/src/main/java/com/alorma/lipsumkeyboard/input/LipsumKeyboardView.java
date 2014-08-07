package com.alorma.lipsumkeyboard.input;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

/**
 * Created by Bernat Borrás Paronella on 07/08/2014.
 */
public class LipsumKeyboardView extends KeyboardView {

    private int max;

    public LipsumKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPreviewEnabled(false);
    }

    public LipsumKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPreviewEnabled(false);
    }
}

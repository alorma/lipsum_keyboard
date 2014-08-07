package com.alorma.lipsumkeyboard.input;

import android.content.Context;
import android.inputmethodservice.Keyboard;

/**
 * Created by Bernat Borrás Paronella on 07/08/2014.
 */
public class LipsumKeyboard extends Keyboard{
    public LipsumKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);

    }

    public LipsumKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
    }

    public LipsumKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
    }

    public LipsumKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
    }


}

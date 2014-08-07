package com.alorma.lipsumkeyboard.input;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.SparseArray;

import java.util.List;

/**
 * Created by Bernat Borrás Paronella on 07/08/2014.
 */
public class LipsumKeyboard extends Keyboard{

    private SparseArray<Key> keyMap;
    private int show = 1;
    private int max;

    public LipsumKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
        init(context);

    }

    public LipsumKeyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        super(context, xmlLayoutResId, modeId, width, height);
        init(context);
    }

    public LipsumKeyboard(Context context, int xmlLayoutResId, int modeId) {
        super(context, xmlLayoutResId, modeId);
        init(context);
    }

    public LipsumKeyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        super(context, layoutTemplateResId, characters, columns, horizontalPadding);
        init(context);
    }

    public void init(Context context) {
        this.keyMap = new SparseArray<Key>();
        List<Key> keys = getKeys();
        for (Key key : keys) {
            int[] codes = key.codes;
            if (codes.length > 0) {
                keyMap.put(codes[0], key);
            }
        }
    }

    public void decrement() {
        if (show > 1) {
            show--;
        }

        updateKey();
    }

    public void increment() {
        if (show + 1 < max) {
            show++;
        }
        updateKey();
    }

    public void reset() {
        show = 1;
        updateKey();
    }

    private void updateKey() {
        Key key = keyMap.get(4);
        if (key != null) {
            key.label = String.valueOf(show);
        }
    }

    public int getShow() {
        return show;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}

package com.alorma.lipsumkeyboard.input;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.alorma.lipsumkeyboard.R;

/**
 * Created by Bernat Borrás Paronella on 07/08/2014.
 */
public class LipsumIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {


    private StringBuilder text = new StringBuilder(0);

    private LipsumKeyboard lipsumKeyboard = null;
    private LipsumKeyboardView lipsumKeyboardView = null;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onInitializeInterface() {
        lipsumKeyboard = new LipsumKeyboard(this, R.layout.lipsum);
    }

    @Override
    public View onCreateInputView() {
        lipsumKeyboardView = (LipsumKeyboardView) getLayoutInflater().inflate(R.layout.key, null);
        lipsumKeyboardView.setOnKeyboardActionListener(this);
        lipsumKeyboardView.setKeyboard(lipsumKeyboard);
        return lipsumKeyboardView;
    }

    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        text.setLength(0);
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        switch (primaryCode) {
            case 1:
                commitText(getString(R.string.lipsum_paragrah));
                break;
            case 2:
                commitText(getString(R.string.lipsum_word));
                break;

            default:
                break;
        }
    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {
        handleBackspace();
    }

    @Override
    public void swipeRight() {
        InputConnection ic = getCurrentInputConnection();
        ic.deleteSurroundingText(6, 0);
    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void commitText(String s) {
        InputConnection ic = getCurrentInputConnection();
        ic.commitText(s, 1);
        text.setLength(0);
    }

    private void handleBackspace() {
        if (text.length() == 0) {
            getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
        text.setLength(0);

    }
}

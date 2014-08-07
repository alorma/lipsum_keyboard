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

    private LipsumKeyboard lipsumKeyboard = null;
    private LipsumKeyboardView lipsumKeyboardView = null;
    private String[] paragrahs;

    @Override
    public void onCreate() {
        super.onCreate();

        String lipsumText = getString(R.string.lipsum);

        paragrahs = lipsumText.split("\n");

    }

    @Override
    public void onInitializeInterface() {
        lipsumKeyboard = new LipsumKeyboard(this, R.layout.lipsum);
        lipsumKeyboard.setMax(paragrahs.length);
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

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        switch (primaryCode) {
            case 1:
                commitParagprahs(lipsumKeyboard.getShow());
                break;
            case 2:
                commitWords(lipsumKeyboard.getShow());
                break;
            case 3:
                lipsumKeyboard.decrement();
                lipsumKeyboardView.invalidateAllKeys();
                break;
            case 4:

                break;
            case 5:
                lipsumKeyboard.increment();
                lipsumKeyboardView.invalidateAllKeys();
                break;

            default:
                break;
        }
    }

    private void commitParagprahs(int show) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < show; i++) {
            builder.append(paragrahs[i]);
            builder.append("\n");
            builder.append("\n");
        }
        commitText(builder.toString());
    }

    private void commitWords(int show) {
        int pLength = 0;
        boolean stop = false;
        int accumulateWords = 0;
        while (pLength < paragrahs.length && !stop) {
            String[] words = paragrahs[pLength++].split(" ");
            accumulateWords += words.length;
            if (accumulateWords >= show) {
                stop = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        int usedWords = 0;
        for (int i = 0; i < pLength; i++) {
            String[] words = paragrahs[i].split(" ");
            boolean fullParagraph = false;
            for (int i1 = 0; i1 < words.length; i1++) {
                if (usedWords++ < show) {
                    builder.append(words[i1]);
                    builder.append(" ");
                    if (i1 + 1 >= words.length) {
                        fullParagraph = true;
                    }
                } else {
                    break;
                }
            }
            if (fullParagraph) {
                builder.append("\n");
                builder.append("\n");
            }
        }
        commitText(builder.toString());
    }

    private void commitText(String s) {
        lipsumKeyboard.reset();
        lipsumKeyboardView.invalidateAllKeys();
        InputConnection ic = getCurrentInputConnection();
        ic.commitText(s, 1);

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

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}

package com.healthtimejournal.service;

import android.widget.MultiAutoCompleteTextView.Tokenizer;

public class TagTokenizer implements Tokenizer{

	@Override
    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;

        while (i > 0 && (text.charAt(i - 1) != '@') && text.charAt(i-1) != '#') {
            i--;
        }
        if (i>0)
            return i-1;
        else
            return i;
    }

    @Override
    public int findTokenEnd(CharSequence text, int cursor) {
        System.out.println("fte:" + text + "," + cursor);
        int i = cursor;
        int len = text.length();

        while (i < len) {
            if (text.charAt(i) == '@') {
                return i;
            } else {
                i++;
            }
        }

        return len;

    }

    @Override
    public CharSequence terminateToken(CharSequence text) {
        System.out.println("tto:" + text );
        String s = text.toString();
//        if (s.startsWith("@")) {
//            return s.substring(0, s.indexOf(',')) + " ";
//        }
//        return text + " ";
        return s + " ";
    }

}

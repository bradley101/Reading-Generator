package com.bradley.readinggenerator;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;

public class MyCustomePhrase extends Phrase {
    MyCustomePhrase(String text) {
        this(text, FontFactory.getFont(FontFactory.TIMES, 10));
    }

    MyCustomePhrase(String text, float size) {
        this(text, FontFactory.getFont(FontFactory.TIMES, size));
    }

    MyCustomePhrase(String text, Font font) {
        super(text, font);
    }
}

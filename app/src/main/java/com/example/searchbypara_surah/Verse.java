package com.example.searchbypara_surah;

import androidx.annotation.NonNull;

public class Verse {
    String translation;
    String text;

    public Verse(String text, String translation) {
        this.text = text;
        this.translation = translation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

}

package com.ergundenizbuyruk.geo_quiz;

import androidx.annotation.StringRes;

/*
 * Soruların strings.xmldeki idsini ve cevabını tutan Question Classı.
 */
public class Question {

    @StringRes
    private int textResourcesId;

    private boolean answer;

    public Question(int textResourcesId, boolean answer) {
        this.textResourcesId = textResourcesId;
        this.answer = answer;
    }

    public int getTextResourcesId() {
        return textResourcesId;
    }

    public boolean getAnswer() {
        return answer;
    }
}

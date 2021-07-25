package com.ey.datamonger;

public class QuestionCB {
    private int question_id;
    private int questionHeader;

    public QuestionCB(int question_id, int questionHeader) {
        this.question_id = question_id;
        this.questionHeader = questionHeader;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getQuestionHeader() {
        return questionHeader;
    }

    public void setQuestionHeader(int questionHeader) {
        this.questionHeader = questionHeader;
    }
}

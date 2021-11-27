package ru.otus.spring.model;

import com.opencsv.bean.CsvBindByPosition;

public class Question {

    @CsvBindByPosition(position = 0)
    private String question;

    @CsvBindByPosition(position = 1)
    private String answer;

    @CsvBindByPosition(position = 2)
    private String id;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

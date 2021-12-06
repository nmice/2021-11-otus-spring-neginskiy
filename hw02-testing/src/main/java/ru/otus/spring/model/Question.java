package ru.otus.spring.model;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class Question {

    public Question() {
    }

    public Question(String question, String answer, int id) {
        this.question = question;
        this.answer = answer;
        this.id = id;
    }

    @CsvBindByPosition(position = 0)
    private String question;

    @CsvBindByPosition(position = 1)
    private String answer;

    @CsvBindByPosition(position = 2)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id == question1.id &&
                Objects.equals(question, question1.question) &&
                Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer, id);
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

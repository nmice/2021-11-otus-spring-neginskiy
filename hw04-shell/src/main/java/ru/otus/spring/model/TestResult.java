package ru.otus.spring.model;

public class TestResult {

    private Student student;
    private int questionsSize;
    private int score;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getQuestionsSize() {
        return questionsSize;
    }

    public void setQuestionsSize(int questionsSize) {
        this.questionsSize = questionsSize;
    }

    public void apply(boolean isCorrectAnswer) {
        if (isCorrectAnswer) score++;
    }

    public TestResult(Student student, int questionsSize) {
        this.student = student;
        this.questionsSize = questionsSize;
    }

    public int getScore() {
        return score;
    }
}

package ru.otus.spring.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import ru.otus.spring.model.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoCsv implements QuestionDao {

    private final Resource questionsResource;

    public QuestionDaoCsv(Resource questionsResource) {
        this.questionsResource = questionsResource;
    }

    @Override
    public String findAnswerByQuestion(String question) {
        List<Question> questions = getQuestionsDataFromCsv();
        Question questionData = questions.stream()
                .filter(q -> question.equals(q.getQuestion()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("The answer to the question asked was not found."));
        return questionData.getAnswer();
    }

    @Override
    public List<String> findAllQuestions() {
        List<Question> questions = getQuestionsDataFromCsv();
        return questions.stream()
                .map(Question::getQuestion)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAnswers() {
        List<Question> questions = getQuestionsDataFromCsv();
        return questions.stream()
                .map(Question::getAnswer)
                .collect(Collectors.toList());
    }

    private List<Question> getQuestionsDataFromCsv() {
        try {
            System.out.println("questionsResource: "+questionsResource.toString());
            System.out.println("questionsResource.getFile(): "+questionsResource.getFile().toString());
            return (List<Question>) new CsvToBeanBuilder(new FileReader(questionsResource.getFile()))
                    .withType(Question.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from CSV file");
        }
    }
}

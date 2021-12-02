package ru.otus.spring.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import ru.otus.spring.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao {

    private final Resource questionsResource;

    public QuestionDaoCsv(Resource questionsResource) {
        this.questionsResource = questionsResource;
    }

    @Override
    public List<Question> findAllQuestions() {
        try {
            return new CsvToBeanBuilder<Question>(new InputStreamReader(questionsResource.getInputStream()))
                    .withType(Question.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read data from CSV file", e);
        }
    }
}

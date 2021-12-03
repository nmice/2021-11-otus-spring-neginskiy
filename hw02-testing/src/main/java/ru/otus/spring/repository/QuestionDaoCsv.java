package ru.otus.spring.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@PropertySource("classpath:app.properties")
@Repository("csvDao")
public class QuestionDaoCsv implements QuestionDao {

    private final Resource questionsResource;

    public QuestionDaoCsv(@Value("${csvfile.path}") Resource questionsResource) {
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

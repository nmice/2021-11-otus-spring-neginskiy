package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.FileNameProvider;
import ru.otus.spring.model.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final FileNameProvider fileNameProvider;

    public QuestionDaoCsv(FileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public List<Question> findAllQuestions() {
        String fileName = fileNameProvider.getFileName();
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        return new CsvToBeanBuilder<Question>(new InputStreamReader(is))
                .withType(Question.class)
                .build()
                .parse();
    }
}

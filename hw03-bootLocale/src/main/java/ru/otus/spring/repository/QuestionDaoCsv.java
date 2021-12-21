package ru.otus.spring.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.model.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final AppProps props;

    public QuestionDaoCsv(AppProps props) {
        this.props = props;
    }

    @Override
    public List<Question> findAllQuestions() {
        String fileName = props.getCsvfiles().get(props.getLocaleTag());
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        return new CsvToBeanBuilder<Question>(new InputStreamReader(is))
                .withType(Question.class)
                .build()
                .parse();
    }
}

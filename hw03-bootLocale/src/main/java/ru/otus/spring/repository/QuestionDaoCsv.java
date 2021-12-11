package ru.otus.spring.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.model.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final Locale localeProvider;
    private final AppProps props;

    public QuestionDaoCsv(Locale localeProvider, AppProps props) {
        this.localeProvider = localeProvider;
        this.props = props;
    }

    @Override
    public List<Question> findAllQuestions() {
        String localeName = localeProvider.getDisplayName();
        String fileName = props.getCsvfiles().get(localeName);
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        return new CsvToBeanBuilder<Question>(new InputStreamReader(is))
                .withType(Question.class)
                .build()
                .parse();
    }
}

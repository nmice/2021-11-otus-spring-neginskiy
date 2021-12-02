package ru.otus.spring.runner;

import org.springframework.stereotype.Component;
import ru.otus.spring.model.Question;
import ru.otus.spring.service.InputOutputService;
import ru.otus.spring.service.InputOutputServiceImpl;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RunnerImpl implements Runner {

    private final QuestionService service;

    public RunnerImpl(QuestionService service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            List<Question> questions = service.getAllQuestions();
            InputOutputService inOutService = new InputOutputServiceImpl(System.in, System.out);

            inOutService.output("Input your firstname:");
            String firstname = inOutService.input();
            inOutService.output("Input your secondname:");
            String secondname = inOutService.input();
            inOutService.output("LETS GO TEST");

            int score = 0;
            // Создаем упорядоченную по id коллекцию ответов
            List<String> answersWithId = questions.stream()
                    .map(q -> (q.getId() + ". " + q.getAnswer()))
                    .collect(Collectors.toList());
            // Перемешиваем вопросы
            Collections.shuffle(questions);
            // Проходим в случайном порядке все вопросы
            for (Question question : questions) {
                // Печатаем вопрос
                inOutService.output(question.getQuestion() + ". What movie is this scene from?");
                // Печатаем варианты ответов
                answersWithId.forEach(inOutService::output);
                // Запросить ввод ответа (id)
                int answerId = Integer.parseInt(inOutService.input());
                // Сверить ответ и вопрос по id
                if (answerId == question.getId()) {
                    // При совпадении защитать +балл
                    score++;
                }
            }
            // Вывести результат ФИ + количество верных ответов
            inOutService.output("YOUR RESULT:");
            inOutService.output(firstname + " " + secondname + ", You answered " +
                    (score == questions.size() ? "all the" : score) +
                    " questions correctly.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

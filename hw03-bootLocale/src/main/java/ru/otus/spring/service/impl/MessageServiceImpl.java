package ru.otus.spring.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;
import ru.otus.spring.service.MessageService;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    private final Locale locale;
    private final MessageSource messageSourceProvider;

    public MessageServiceImpl(AppProps props,
                              MessageSource messageSource) {
        this.messageSourceProvider = messageSource;
        this.locale = props.getLocale();
        System.out.println(locale);
    }

    public String getMessage(String message) {
        return messageSourceProvider.getMessage(message, null, locale);
    }

    public String getMessage(String message, Object... args) {
        return messageSourceProvider.getMessage(message, args, locale);
    }
}

package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.MessageService;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    private final Locale localeProvider;
    private final MessageSource messageSourceProvider;

    public MessageServiceImpl(@Qualifier("localeProvider") Locale locale,
                              @Qualifier("messageSourceProvider") MessageSource messageSource) {
        this.messageSourceProvider = messageSource;
        this.localeProvider = locale;
    }

    public String getMessage(String message) {
        return messageSourceProvider.getMessage(message, null, localeProvider);
    }

    public String getMessage(String message, Object... args) {
        return messageSourceProvider.getMessage(message, args, localeProvider);
    }
}

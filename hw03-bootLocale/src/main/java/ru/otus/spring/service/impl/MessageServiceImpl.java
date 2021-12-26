package ru.otus.spring.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.LocaleProvider;
import ru.otus.spring.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private final LocaleProvider localeProvider;
    private final MessageSource messageSourceProvider;

    public MessageServiceImpl(LocaleProvider localeProvider,
                              MessageSource messageSource) {
        this.messageSourceProvider = messageSource;
        this.localeProvider = localeProvider;
    }

    public String getMessage(String message) {
        return messageSourceProvider.getMessage(message, null, localeProvider.getLocale());
    }

    public String getMessage(String message, Object... args) {
        return messageSourceProvider.getMessage(message, args, localeProvider.getLocale());
    }
}

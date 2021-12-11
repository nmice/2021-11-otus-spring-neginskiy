package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MessageConfig {

    public static final String DEFAULT_LOCALE_TAG = "en_EN";

    @Bean
    public Locale localeProvider(@Value("${locale-tag}") String localeTag) {
        if (!localeTag.contains("_")) {
            localeTag = DEFAULT_LOCALE_TAG;
        }
        String[] localeParams = localeTag.split("_");
        return new Locale(localeParams[0], localeParams[1]);
    }

    @Bean
    public MessageSource messageSourceProvider() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

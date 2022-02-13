package ru.otus.spring.config;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private final AppProps props;

    public LocaleProviderImpl(AppProps props) {
        this.props = props;
    }

    @Override
    public Locale getLocale() {
        return props.getLocale();
    }
}

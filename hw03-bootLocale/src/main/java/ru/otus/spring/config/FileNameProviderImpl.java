package ru.otus.spring.config;

import org.springframework.stereotype.Component;

@Component
public class FileNameProviderImpl implements FileNameProvider {

    private final AppProps props;

    public FileNameProviderImpl(AppProps props) {
        this.props = props;
    }

    @Override
    public String getFileName() {
        return props.getCsvFiles().get(props.getLocaleTag());
    }
}

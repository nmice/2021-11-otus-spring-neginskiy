package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "testing")
@Component
public class AppProps {

    private Map<String, String> csvfiles;

    public Map<String, String> getCsvfiles() {
        return csvfiles;
    }

    public void setCsvfiles(Map<String, String> csvfiles) {
        this.csvfiles = csvfiles;
    }
}

package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "testing")
public class AppProps {

    private String localeTag;

    private Map<String, String> csvfiles;

    public String getLocaleTag() {
        return localeTag;
    }

    public void setLocaleTag(String localeTag) {
        this.localeTag = localeTag;
    }

    public Map<String, String> getCsvfiles() {
        return csvfiles;
    }

    public void setCsvfiles(Map<String, String> csvfiles) {
        this.csvfiles = csvfiles;
    }

    public Locale getLocale() {
        String[] localeParams = localeTag.split("_");
        return new Locale(localeParams[0], localeParams[1]);
    }
}

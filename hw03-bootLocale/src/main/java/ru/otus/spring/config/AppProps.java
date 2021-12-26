package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "testing")
public class AppProps {

    private String localeTag;

    private Map<String, String> csvFiles;

    public String getLocaleTag() {
        return localeTag;
    }

    public void setLocaleTag(String localeTag) {
        this.localeTag = localeTag;
    }

    public Map<String, String> getCsvFiles() {
        return csvFiles;
    }

    public void setCsvFiles(Map<String, String> csvFiles) {
        this.csvFiles = csvFiles;
    }

    public Locale getLocale() {
        return Locale.forLanguageTag(localeTag);
    }

    public String getFileName() {
        return csvFiles.get(localeTag);
    }
}

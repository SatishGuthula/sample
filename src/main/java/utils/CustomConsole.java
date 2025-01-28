package utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CustomConsole {
    private final String prefix;

    @Autowired
    public CustomConsole(@Value("${spring.application.name}") String applicationName) {
        this.prefix = String.format(
                "[%s][%s]",
                UUID.randomUUID().toString().substring(0, 5),
                applicationName
        );
    }

    public CustomConsole(String... metaData) {
        String metadataStr = metaData != null && metaData.length > 0
                ? String.join("][", metaData)
                : "";
        this.prefix = String.format("[%s][%s",
                UUID.randomUUID().toString().substring(0, 5),
                metadataStr
        );
    }

    public void info(String message, Object... args) {
        log.info(prefix + " - " + message, args);
    }

    public void error(String message, Object... args) {
        log.error(prefix + " - " + message, args);
    }

    public void error(String message, Throwable e) {
        log.error(prefix + " - " + message, e);
    }

    public void debug(String message, Object... args) {
        log.debug(prefix + " - " + message, args);
    }

    public void debug(String message, Throwable e) {
        log.debug(prefix + " - " + message, e);
    }

    public void warn(String message, Object... args) {
        log.warn(prefix + " - " + message, args);
    }

    public void warn(String message, Throwable e) {
        log.warn(prefix + " - " + message, e);
    }
}
package com.tfms.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupLogger.class);

    @Value("${server.port:8080}")
    private int port;

    @Value("${tfms.frontend.entry:/index.html}")
    private String entryPoint;

    @EventListener(ApplicationReadyEvent.class)
    public void logFrontendLink() {
        String url = String.format("http://localhost:%d%s", port, entryPoint);
        LOGGER.info("Transportation Fleet Management UI ready at {}", url);
    }
}



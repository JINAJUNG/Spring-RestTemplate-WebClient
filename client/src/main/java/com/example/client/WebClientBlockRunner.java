package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientBlockRunner implements ApplicationRunner {

    @Value("${hello.server}")
    private String url;

    private static final Logger logger = LoggerFactory.getLogger(WebClientBlockRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = WebClient.create();
        StopWatch stopWatch = new StopWatch();

        logger.debug("WebClient block start");
        stopWatch.start();

        String one = webClient
                .get()
                .uri(url + "?millis=5000")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.debug("api response one: {}", one);

        String two = webClient
                .get()
                .uri(url + "?millis=3000")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.debug("api response two: {}", two);

        stopWatch.stop();
        logger.debug("webclient block total sec: {}", stopWatch.getTotalTimeSeconds());
    }
}

package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientRunner implements ApplicationRunner {

    @Value("${hello.server}")
    private String url;

    private static final Logger logger = LoggerFactory.getLogger(WebClientRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = WebClient.create();
        StopWatch stopWatch = new StopWatch();

        logger.debug("WebClient start");
        stopWatch.start();

        String one = webClient
                .get()
                .uri(url + "?second=3000")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.debug("api response one: {}", one);

        String two = webClient
                .get()
                .uri(url + "?second=5000")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.debug("api response two: {}", two);

        stopWatch.stop();
        logger.debug("webclient total sec: {}", stopWatch.getTotalTimeSeconds());
    }
}

package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateRunner implements ApplicationRunner {

    @Value("${hello.server}")
    private String url;

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        RestTemplate restTemplate = new RestTemplate();
        StopWatch stopWatch = new StopWatch();

        logger.debug("RestTemplate start");
        stopWatch.start();

        String one = restTemplate.getForObject(url + "?millis=3000", String.class);
        logger.debug("api response one: {}", one);

        String two = restTemplate.getForObject(url + "?millis=5000", String.class);
        logger.debug("api response two: {}", two);

        stopWatch.stop();
        logger.debug("restTemplate total sec: {}", stopWatch.getTotalTimeSeconds());
    }
}

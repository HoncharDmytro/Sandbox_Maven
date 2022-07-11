package com.sandbox.remoting.amqp_boot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class WeatherServiceImp {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImp.class);

    @RabbitListener(queues="forecasts")
    public void getForecast(String stateCode) {

        if ("FL".equals(stateCode)) {
            logger.info("Hot");
        } else if ("MA".equals(stateCode)) {
            logger.info("Cold");
        } else {
            logger.info("Not available at this time");
        }
    }
}

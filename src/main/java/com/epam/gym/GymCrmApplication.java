package com.epam.gym;

import com.epam.gym.config.AppConfig;
import com.epam.gym.model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;


public class GymCrmApplication {
    private static final Logger log = LoggerFactory.getLogger(GymCrmApplication.class);

    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            log.info("Context started with {} beans",context.getBeanDefinitionCount());
            Map<Long, Trainee> traineeStorage = (Map<Long, Trainee>) context.getBean("traineeStorage");
            log.info("Loaded {} trainees into traineeStorage {}",traineeStorage.size(),traineeStorage);
        }
    }
}
package com.epam.gym;

import com.epam.gym.config.AppConfig;
import com.epam.gym.dao.TraineeDao;
import com.epam.gym.model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Map;


public class GymCrmApplication {
    private static final Logger log = LoggerFactory.getLogger(GymCrmApplication.class);

    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            log.info("Context started with {} beans",context.getBeanDefinitionCount());
            Map<Long, Trainee> traineeStorage = (Map<Long, Trainee>) context.getBean("traineeStorage");
            log.info("Loaded {} trainees into traineeStorage {}",traineeStorage.size(),traineeStorage);


            TraineeDao traineeDao = context.getBean(TraineeDao.class);
            log.info("list of all trainees {}",traineeDao.findAll().toString());

            Trainee trainee = new Trainee(null,"Jamal","Karimov", "jumanazar01","seshanbabek",true, LocalDate.now(),"qoshnisini qo'shnisi");

            traineeDao.create(trainee);

            log.info("list of updated trainees {}",traineeDao.findAll().toString());
        }
    }
}
package com.epam.gym.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class StorageInitializer implements BeanPostProcessor {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectMapper objectMapperForTime = objectMapper.registerModule(new JavaTimeModule());
    private static final Logger log = LoggerFactory.getLogger(StorageInitializer.class);
    private String initFilePath;

    @Value("${storage.init.file}")
    public void setInitFilePath(String initFilePath){
        this.initFilePath = initFilePath;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        log.info("bean name : "+ beanName+" bean class name is "+bean.getClass());

        try {
            InputStream inputStream = new ClassPathResource(initFilePath).getInputStream();
            InitData initData = objectMapperForTime.readValue(inputStream, InitData.class);
            
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return bean;
    }
}

package com.luv2code.springcoredemo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    //define an init method
    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println("In doMyStartupStuff(): "+getClass().getSimpleName());
    }
    //define a destroy method
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In doMyCleanUpStuff(): "+getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 minutes!!!";
    }
}

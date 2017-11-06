package com.gcp.services.mock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.apache.log4j.Logger;

@RestController
public class WorkloadController {
    private static Logger log = Logger.getLogger(WorkloadController.class.getName());

    @RequestMapping(value = "/workload", method = RequestMethod.GET)
    public String workload() {
        LocalDateTime timeBefore = LocalDateTime.now();

        String justAvoidingJIT = "";

        for(long i = 0; i < 1000000; i++){
            for(long j = 0; j < 10; j++){
                for(long k = 0; k < 1; k++){
                    long random1 = randomWithRange(1, 10);
                    long random2 = randomWithRange(1, 10);
                    long w = random1 * random2;
                    justAvoidingJIT = "" + w;
                }
            }
        }

        LocalDateTime timeAfter = LocalDateTime.now();

        String stringTimeBefore = timeBefore.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("Antes do Processamento: " + stringTimeBefore);

        String stringTimeAfter = timeAfter.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("Depois do Processamento: " + stringTimeAfter);

        long epochBefore = timeBefore.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long epochAfter = timeAfter.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long loadTime = epochAfter - epochBefore;
        System.out.println("Tempo Transcorrido: " + loadTime + " : " + justAvoidingJIT);

        return "Ante: " + stringTimeBefore +
               " Depois:  " + stringTimeAfter +
               " Tempo Transcorrido: " + loadTime;
    }

    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    @RequestMapping(value = "/ishealth", method = RequestMethod.GET)
    public String isHealth(){
        log.debug("TASKS LIST SERVICE IS HEALTH");
        return "ok";
    }
}

package br.com.olx.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "br.com.olx")
public class ChallengeApiApplication {

    public static void main( String[] args){
        SpringApplication.run(ChallengeApiApplication.class, args);
    }

    @PostConstruct
    static void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}

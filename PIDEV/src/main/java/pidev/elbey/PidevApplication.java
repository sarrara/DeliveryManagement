package pidev.elbey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pidev.elbey.Config.AppConfig;

import javax.annotation.PostConstruct;
@EnableScheduling
@SpringBootApplication
public class PidevApplication {
    @Autowired
    private AppConfig twilioConfig;
    @PostConstruct
    public void initTwilio(){

    }
    public static void main(String[] args) {
        SpringApplication.run(PidevApplication.class, args);
    }

}

package pidev.elbey.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pidev.elbey.Services.DeliveryService;
import pidev.elbey.Services.twilioService;

@Configuration

@ConfigurationProperties(prefix = "twilio")
@Data
public class AppConfig {


        @Bean
        public twilioService s() {
            return new twilioService();
        }

        @Bean
        public DeliveryService ds(twilioService twilioService) {
            return new DeliveryService(twilioService);
        }
    }




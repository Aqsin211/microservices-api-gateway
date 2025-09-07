package az.company.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProfileApplication.class, args);
    }

}

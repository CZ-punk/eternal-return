package demo.eternalreturn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"demo.eternalreturn"})
public class EternalReturnApplication {

    public static void main(String[] args) {
        SpringApplication.run(EternalReturnApplication.class, args);
    }

}

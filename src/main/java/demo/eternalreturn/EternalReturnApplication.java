package demo.eternalreturn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"demo.eternalreturn"})
@EnableJpaAuditing
public class EternalReturnApplication {

    public static void main(String[] args) {
        SpringApplication.run(EternalReturnApplication.class, args);
    }

}

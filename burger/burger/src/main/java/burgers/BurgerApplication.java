package burgers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"burgers","burgers.*"})
@EntityScan(basePackages = {"burgers.domain"})
@EnableJpaRepositories(basePackages = {"burgers.repo"})
public class BurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }

}

package burgers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"burgers","burgers.*"})
@EntityScan(basePackages = {"burgers.domain"})
@EnableR2dbcRepositories(basePackages = {"burgers.repo2"})
public class BurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }

}

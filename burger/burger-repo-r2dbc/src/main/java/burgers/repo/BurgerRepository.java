package burgers.repo;

import burgers.domain.Burger;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BurgerRepository extends ReactiveCrudRepository<Burger, Long> {
}

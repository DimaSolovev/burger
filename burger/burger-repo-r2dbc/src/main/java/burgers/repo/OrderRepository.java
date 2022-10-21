package burgers.repo;

import burgers.domain.BurgerOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository 
       extends ReactiveCrudRepository<BurgerOrder, Long> {
}

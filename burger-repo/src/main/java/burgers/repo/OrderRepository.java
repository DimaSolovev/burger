package burgers.repo;

import burgers.domain.BurgerOrder;
import burgers.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<BurgerOrder, Long> {
    List<BurgerOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}

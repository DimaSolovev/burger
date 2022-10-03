package burgers.repo;


import burgers.domain.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, Long> {
}

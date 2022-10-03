package burgers.repo;


import burgers.domain.Burger;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BurgerRepository extends PagingAndSortingRepository<Burger, Long> {
}

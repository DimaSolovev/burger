package com.example.burger.repo;

import com.example.burger.data.BurgerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<BurgerOrder, Long> {
}

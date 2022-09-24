package com.example.burger.repo;

import com.example.burger.data.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, Long> {
}

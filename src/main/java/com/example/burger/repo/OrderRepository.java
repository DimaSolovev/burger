package com.example.burger.repo;

import com.example.burger.data.BurgerOrder;
import com.example.burger.data.User;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderRepository extends CrudRepository<BurgerOrder, Long> {
    List<BurgerOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}

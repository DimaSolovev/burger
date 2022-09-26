//package com.example.burger.repo;
//
//import com.example.burger.data.Burger;
//import com.example.burger.data.BurgerOrder;
//import com.example.burger.data.Ingredient;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//class OrderRepositoryTest {
//
//    @Autowired
//    OrderRepository orderRepo;
//
//    @Test
//    public void saveOrderWithTwoBurgers() {
//        BurgerOrder burgerOrder = new BurgerOrder();
//        burgerOrder.setDeliveryName("Test");
//        burgerOrder.setDeliveryStreet("Test");
//        burgerOrder.setDeliveryCity("Test");
//        burgerOrder.setDeliveryState("Test");
//        burgerOrder.setDeliveryZip("Test");
//        burgerOrder.setCcNumber("4111111111111111");
//        burgerOrder.setCcExpiration("12/23");
//        burgerOrder.setCcCVV("234");
//        Burger burger1 = new Burger();
//        burger1.setCreatedAt(new Date());
//        burger1.setName("Test one");
//        burger1.addIngredient(new Ingredient("WHIT", "White bun", Ingredient.Type.BUN));
//        burger1.addIngredient(new Ingredient("BLAC", "Black bun", Ingredient.Type.BUN));
//        burgerOrder.addBurger(burger1);
//        Burger burger2 = new Burger();
//        burger2.setCreatedAt(new Date());
//        burger2.setName("Test two");
//        burger2.addIngredient(new Ingredient("WHIT", "White bun", Ingredient.Type.BUN));
//        burger2.addIngredient(new Ingredient("BLAC", "Black bun", Ingredient.Type.BUN));
//        burgerOrder.addBurger(burger2);
//
//        BurgerOrder savedBurger = orderRepo.save(burgerOrder);
//        assertThat(savedBurger.getId()).isNotNull();
//
//        BurgerOrder orderFromDB = orderRepo.findById(savedBurger.getId()).get();
//        Assertions.assertThat(orderFromDB.getDeliveryName()).isEqualTo("Test");
//        Assertions.assertThat(orderFromDB.getDeliveryStreet()).isEqualTo("Test");
//        Assertions.assertThat(orderFromDB.getDeliveryCity()).isEqualTo("Test");
//        Assertions.assertThat(orderFromDB.getDeliveryState()).isEqualTo("Test");
//        Assertions.assertThat(orderFromDB.getDeliveryZip()).isEqualTo("Test");
//        Assertions.assertThat(orderFromDB.getCcNumber()).isEqualTo("4111111111111111");
//        Assertions.assertThat(orderFromDB.getCcExpiration()).isEqualTo("12/23");
//        Assertions.assertThat(orderFromDB.getCcCVV()).isEqualTo("234");
//        Assertions.assertThat(orderFromDB.getDate().getTime()).isEqualTo(savedBurger.getDate().getTime());
//        List<Burger> burgers = orderFromDB.getBurgers();
//        Assertions.assertThat(burgers.size()).isEqualTo(2);
//        Assertions.assertThat(burgers).containsExactlyInAnyOrder(burger1, burger2);
//
//    }
//}
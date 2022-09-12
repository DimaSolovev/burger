package com.example.burger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BurgerOrder {

    private String deliveryName;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Burger> burgers = new ArrayList<>();
    public void addBurger(Burger burger){
        burgers.add(burger);
    }

}

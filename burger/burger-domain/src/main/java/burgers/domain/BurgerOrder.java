package burgers.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.*;

@Data
public class BurgerOrder{

    @Id
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private Set<Long> burgerIds = new LinkedHashSet<>();

    @Transient
    private transient List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger burger) {
        this.burgers.add(burger);
        if (burger.getId() != null) {
            this.burgerIds.add(burger.getId());
        }
    }

}

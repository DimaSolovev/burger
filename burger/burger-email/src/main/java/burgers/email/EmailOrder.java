package burgers.email;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailOrder {

    private final String email;
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger(Burger taco) {
        this.burgers.add(taco);
    }
}

package burgers.email;

import lombok.Data;

import java.util.List;

@Data
public class Burger {

    private final String name;
    private List<String> ingredients;

}

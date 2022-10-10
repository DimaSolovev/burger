package burgers;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Burger {

    private String name;

    private Date createdAt;

    private List<Ingredient> ingredients;

}
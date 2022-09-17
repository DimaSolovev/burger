package com.example.burger.web;

import com.example.burger.data.BurgerOrder;
import com.example.burger.repo.IngredientRepository;
import com.example.burger.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testOrderPageTest() throws Exception {
        mockMvc.perform(get("/orders/current").flashAttr("burgerOrder", new BurgerOrder()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(content().string(
                        containsString("Check your order")
                ));
    }

    @Test
    public void processOrderTest() throws Exception {
        mockMvc.perform(post("/orders").flashAttr("burgerOrder", new BurgerOrder())
                        .content(
                                "deliveryName=test" +
                                        "&deliveryCity=test" +
                                        "&deliveryStreet=test" +
                                        "&deliveryState=test" +
                                        "&deliveryZip=675645" +
                                        "&ccNumber=481776024360303" +
                                        "&ccExpiration=02/23" +
                                        "&ccCVV=267"
                        )
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void processOrderErrorTest() throws Exception {
        mockMvc.perform(post("/orders").flashAttr("burgerOrder", new BurgerOrder())
                        .content(
                                "deliveryName=" +
                                        "&deliveryCity=test" +
                                        "&deliveryStreet=test" +
                                        "&deliveryState=test" +
                                        "&deliveryZip=675645" +
                                        "&ccNumber=481776024360303" +
                                        "&ccExpiration=02/23" +
                                        "&ccCVV=267"
                        )
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(model().attributeHasFieldErrorCode("burgerOrder", "deliveryName", "NotBlank"));
    }



}
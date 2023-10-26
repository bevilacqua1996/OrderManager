package com.system.order.manager.web.tests;

import com.system.order.manager.controller.OrderSystemController;
import com.system.order.manager.model.Item;
import com.system.order.manager.model.Order;
import com.system.order.manager.service.ItemService;
import com.system.order.manager.service.OrderService;
import com.system.order.manager.service.StockMovementService;
import com.system.order.manager.service.UserService;
import com.system.order.manager.web.builders.ItemBuilder;
import com.system.order.manager.web.builders.OrderBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(OrderSystemController.class)
public class WebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private StockMovementService stockMovementService;

    @MockBean
    private UserService userService;

    @Test
    public void get_allItems_returnsOkWithListOfItems() throws Exception {
        List<Item> itemList = new ArrayList<>();

        Item item1 = ItemBuilder.item().withName("Notebook").withQuantity(10).now();
        Item item2 = ItemBuilder.item().withName("Battery").withQuantity(15).now();

        itemList.add(item1);
        itemList.add(item2);

        Mockito.when(itemService.listItems()).thenReturn(itemList);

        mockMvc.perform(MockMvcRequestBuilders.get("/order-system/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_allOrders_returnsOkWithListOfOrders() throws Exception {
        List<Order> orderList = new ArrayList<>();

        Order order1 = OrderBuilder.order().now();

        orderList.add(order1);

        Mockito.when(orderService.listOrders()).thenReturn(orderList);

        mockMvc.perform(MockMvcRequestBuilders.get("/order-system/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

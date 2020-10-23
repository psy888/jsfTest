package org.example.jsftest.service;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.example.jsftest.dao.OrderRepository;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.entity.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@SessionScope
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CoffeeTypeService coffeeTypeService;
    @Autowired
    private DozerBeanMapper mapper;

    /**
     * Get New OrderItemsDTO based on Available (marked isEnabled=true) CoffeeTypes
     *
     * @return list OrderItemDTO
     */
    private List<OrderItemDTO> getNewOrderItems()
    {
        return coffeeTypeService.getAllAvailableCoffeeTypes().stream().map(coffeeType -> OrderItemDTO.builder().coffeeType(coffeeType).build()).collect(Collectors.toList());
    }

    public OrderDTO getNewOrderDto()
    {
        return OrderDTO.builder().availableItems(getNewOrderItems()).orderDateTime(Date.from(Instant.now())).build();
    }

    public void submitOrder(final OrderDTO currentOrder) throws Exception
    {
        currentOrder.setOrderItems(currentOrder.getOrderedItemsDTO());
        CoffeeOrder coffeeOrder = new CoffeeOrder();
        mapper.map(currentOrder, coffeeOrder);
        coffeeOrder.getOrderItems().forEach(oi->oi.setCoffeeOrder(coffeeOrder));
        orderRepository.save(coffeeOrder);

        // throw new Exception("DB Error!");
        //todo map current order to entity and save it to db
        // System.out.println("ORDER SAVED");
        // currentOrderDto = null;
    }
}

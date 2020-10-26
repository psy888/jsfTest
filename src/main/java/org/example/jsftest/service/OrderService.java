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
        return OrderDTO.builder().availableItems(getNewOrderItems()).build();
    }

    /**
     * Map dto to Entity and save it to database
     *
     * @param currentOrder
     *
     * @throws Exception
     */
    public void submitOrder(final OrderDTO currentOrder) throws Exception
    {
        currentOrder.setOrderDateTime(new Date());
        CoffeeOrder orderEntity = mapToEntity(currentOrder);
        orderEntity.getOrderedItems().forEach(oi -> oi.setCoffeeOrder(orderEntity)); //set id to current order for each order item
        orderRepository.save(orderEntity);
    }

    /**
     * Map to entity
     *
     * @param orderDTO
     *
     * @return
     */
    private CoffeeOrder mapToEntity(OrderDTO orderDTO)
    {
        CoffeeOrder coffeeOrder = new CoffeeOrder();
        mapper.map(orderDTO, coffeeOrder);
        return coffeeOrder;
    }

    private OrderDTO mapToDTO(CoffeeOrder coffeeOrder)
    {
        return OrderDTO.builder().availableItems(coffeeOrder.getOrderedItems().stream().map(orderItem -> mapper.map(orderItem, OrderItemDTO.class)).collect(Collectors.toList())).deliveryAddress(coffeeOrder.getDeliveryAddress()).deliveryPerson(coffeeOrder.getDeliveryPerson()).totalSum(coffeeOrder.getTotalSum()).orderDateTime(coffeeOrder.getOrderDateTime()).build();
    }

    public List<OrderDTO> findAllOrders()
    {
        List<OrderDTO> orders = orderRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
        return orders;
    }

}

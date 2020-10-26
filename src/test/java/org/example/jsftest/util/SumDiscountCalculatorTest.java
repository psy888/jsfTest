package org.example.jsftest.util;

import lombok.Data;
import org.example.jsftest.dto.OrderDTO;
import org.example.jsftest.dto.OrderItemDTO;
import org.example.jsftest.entity.CoffeeType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = SumDiscountCalculatorTest.PropertyServiceConfig.class, loader = AnnotationConfigContextLoader.class)
@Data
class SumDiscountCalculatorTest
{

    private static final String DISCOUNTED_COFFEE = "coffee 123";
    private static final String REGULAR_COFFEE = "coffee 321";
    private static final double DELIVERY_PRICE = 100;
    private static final double DELIVERY_FREE_SUM = 100;
    private static final int FREE_CUP_NUM = 3;

    private CoffeeProperties coffeeProperties;
    private CoffeeType regular;
    private CoffeeType discounted;


    {
        CoffeeProperties cp = new CoffeeProperties();
        cp.setDiscountedCoffeeType(DISCOUNTED_COFFEE);
        cp.setDeliveryPrice(DELIVERY_PRICE);
        cp.setDeliveryFreeSum(DELIVERY_FREE_SUM);
        cp.setDiscountedCupNum(FREE_CUP_NUM);
        CoffeeProperties.setInstance(cp);

        coffeeProperties = cp;

        regular = mock(CoffeeType.class);
        when(regular.getPrice()).thenReturn(5.0);
        when(regular.getName()).thenReturn(REGULAR_COFFEE);

        discounted = mock(CoffeeType.class);
        when(discounted.getPrice()).thenReturn(5.0);
        when(discounted.getName()).thenReturn(DISCOUNTED_COFFEE);
    }


    @Test
    void getRegularItemCostSum()
    {
        OrderItemDTO item = OrderItemDTO.builder().quantity(FREE_CUP_NUM).coffeeType(regular).build();
        double regularSum = SumDiscountCalculator.getItemCostSum(item);
        Assert.assertTrue(regularSum == FREE_CUP_NUM * item.getCoffeeType().getPrice());
    }

    @Test
    void getDiscountedItemCostSum()
    {
        OrderItemDTO item = OrderItemDTO.builder().quantity(FREE_CUP_NUM).coffeeType(discounted).build();
        double discountedSum = SumDiscountCalculator.getItemCostSum(item);
        assertThat(discountedSum).isEqualTo(FREE_CUP_NUM * item.getCoffeeType().getPrice() - item.getCoffeeType().getPrice());
    }

    @Test
    void getTotalSumWODelivery()
    {
        OrderItemDTO item = OrderItemDTO.builder().quantity(FREE_CUP_NUM).coffeeType(regular).build();
        OrderItemDTO item2 = OrderItemDTO.builder().quantity(FREE_CUP_NUM).coffeeType(regular).build();
        OrderDTO order = mock(OrderDTO.class);
        when(order.getAvailableItems()).thenReturn(Arrays.asList(item, item2));

        double freeDeliverySum = SumDiscountCalculator.getTotal(order, false);
        assertThat(freeDeliverySum).isEqualTo(FREE_CUP_NUM * item.getCoffeeType().getPrice() * 2);
    }

    @Test
    void getFreeDelivery()
    {
        int many = 100;
        OrderItemDTO item = OrderItemDTO.builder().quantity(many).coffeeType(regular).build();
        OrderDTO order = mock(OrderDTO.class);
        when(order.getAvailableItems()).thenReturn(Arrays.asList(item));

        double freeDeliverySum = SumDiscountCalculator.getTotal(order, true);
        assertThat(freeDeliverySum).isEqualTo(many * item.getCoffeeType().getPrice());
    }

    @Test
    void getFullDeliveryWithDiscounted()
    {
        OrderItemDTO item = OrderItemDTO.builder().quantity(FREE_CUP_NUM).coffeeType(discounted).build();
        OrderDTO order = mock(OrderDTO.class);
        when(order.getAvailableItems()).thenReturn(Arrays.asList(item));

        double freeDeliverySum = SumDiscountCalculator.getTotal(order, true);
        assertThat(freeDeliverySum).isEqualTo(FREE_CUP_NUM * item.getCoffeeType().getPrice() + DELIVERY_PRICE - item.getCoffeeType().getPrice());
    }

}
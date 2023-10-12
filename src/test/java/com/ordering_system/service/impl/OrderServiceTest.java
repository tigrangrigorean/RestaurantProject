package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;

import com.ordering_system.model.dto.Order;

import com.ordering_system.repository.OrderRepository;

import com.ordering_system.service.converter.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest{
    @Mock
    private Converter converter;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void testGetAll_ReturnsListOfOrders() {
        OrderEntity orderEntity1 = new OrderEntity();
        OrderEntity orderEntity2 = new OrderEntity();
        List<OrderEntity> orderEntities = Arrays.asList(orderEntity1, orderEntity2);
        when(orderRepository.findAll()).thenReturn(orderEntities);
        Order order1 = new Order();
        Order order2 = new Order();
        when(converter.entityToOrderList(orderEntities)).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAll();

        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
        verify(orderRepository, times(1)).findAll();
        verify(converter, times(1)).entityToOrderList(orderEntities);
    }






    @Test
    void testDelete_ValidId_DeletesOrder() {
        long orderId = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        when(orderRepository.findOrderEntityById(orderId)).thenReturn(orderEntity);


        orderService.delete(orderId);

        verify(orderRepository, times(1)).findOrderEntityById(orderId);
        verify(orderRepository, times(1)).deleteByCondition(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testGetDiscount_ValidUserId_ReturnsDiscount() {
        long userId = 1L;
        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setTotalPrice(500.0);
        orderEntity1.setDate(LocalDate.now().minusDays(29).atStartOfDay());
        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setTotalPrice(700.0);
        orderEntity2.setDate(LocalDate.now().minusDays(31).atStartOfDay());
        List<OrderEntity> orders = Arrays.asList(orderEntity1, orderEntity2);
        when(orderRepository.findOrderEntitiesByUserId(userId)).thenReturn(orders);

        double discount = orderService.getDiscount(userId);

        assertEquals(0.0, discount);
        verify(orderRepository, times(1)).findOrderEntitiesByUserId(userId);
    }
}
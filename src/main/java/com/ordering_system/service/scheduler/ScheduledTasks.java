package com.ordering_system.service.scheduler;

import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {
    private final OrderRepository orderRepository;

    public ScheduledTasks(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void deleteOldData() {
        LocalDateTime date = LocalDateTime.now().minusDays(30);
        List<OrderEntity> orderEntity = orderRepository.findAllByDateBefore(date);
        for (OrderEntity order : orderEntity) {
            orderRepository.deleteByCondition(order.getId());
        }
        orderRepository.deleteAll(orderEntity);
    }
}

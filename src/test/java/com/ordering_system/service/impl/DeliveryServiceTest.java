package com.ordering_system.service.impl;

import com.ordering_system.model.dto.Delivery;
import com.ordering_system.service.DeliveryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class DeliveryServiceTest {

    @Mock
    private DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        long deliveryId = 1L;
        Delivery expectedDelivery = new Delivery();
        when(deliveryService.getById(deliveryId)).thenReturn(expectedDelivery);

        Delivery actualDelivery = deliveryService.getById(deliveryId);
        Assertions.assertEquals(expectedDelivery, actualDelivery);
    }

    @Test
    void testSave() {
        Delivery delivery = new Delivery();
        when(deliveryService.save(any(Delivery.class))).thenReturn(delivery);

        Delivery savedDelivery = deliveryService.save(delivery);
        Assertions.assertEquals(delivery, savedDelivery);
    }

    @Test
    void testUpdate() {
        long deliveryId = 1L;
        Delivery delivery = new Delivery();

        doNothing().when(deliveryService).update(eq(deliveryId), any(Delivery.class));

        Assertions.assertDoesNotThrow(() -> deliveryService.update(deliveryId, delivery));
    }

    @Test
    void testDelete() {
        long deliveryId = 1L;
        doNothing().when(deliveryService).delete(deliveryId);

        Assertions.assertDoesNotThrow(() -> deliveryService.delete(deliveryId));
    }

    @Test
    void testUpdateStatusToDelivered() {
        Delivery delivery = new Delivery();
        doNothing().when(deliveryService).updateStatusToDelivered(any(Delivery.class));

        Assertions.assertDoesNotThrow(() -> deliveryService.updateStatusToDelivered(delivery));
    }
}

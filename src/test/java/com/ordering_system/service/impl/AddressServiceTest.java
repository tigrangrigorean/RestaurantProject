package com.ordering_system.service.impl;

import com.ordering_system.model.domain.AddressEntity;
import com.ordering_system.model.dto.Address;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.service.converter.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        long id = 1;
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(id);
        when(addressRepository.findAddressEntityById(id)).thenReturn(addressEntity);

        Converter converter = Mockito.mock(Converter.class); // Mock the Converter dependency
        when(converter.entityToAddress(addressEntity)).thenReturn(new Address());

        AddressServiceImpl addressService = new AddressServiceImpl(addressRepository, converter);

        Address address = addressService.getById(id);

        assertNotNull(address);
        verify(addressRepository, times(1)).findAddressEntityById(id);
    }

    @Test
    public void testGetAll() {
        AddressEntity addressEntity1 = new AddressEntity();
        addressEntity1.setId(1);
        AddressEntity addressEntity2 = new AddressEntity();
        addressEntity2.setId(2);
        List<AddressEntity> addressEntities = Arrays.asList(addressEntity1, addressEntity2);
        when(addressRepository.findAll()).thenReturn(addressEntities);

        Converter converter = Mockito.mock(Converter.class); // Mock the Converter dependency
        List<Address> expectedAddresses = Arrays.asList(new Address(), new Address());
        when(converter.entityToAddressList(addressEntities)).thenReturn(expectedAddresses);

        AddressServiceImpl addressService = new AddressServiceImpl(addressRepository, converter);

        List<Address> addresses = addressService.getAll();

        assertEquals(addressEntities.size(), addresses.size());
        assertEquals(expectedAddresses, addresses);
        verify(addressRepository, times(1)).findAll();
    }


    @Test
    public void testSave() {
        Address address = new Address();
        AddressEntity savedAddressEntity = new AddressEntity();
        savedAddressEntity.setId(1);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(savedAddressEntity);

        Converter converter = Mockito.mock(Converter.class); // Mock the Converter dependency
        when(converter.addressToEntity(address)).thenReturn(new AddressEntity());

        AddressServiceImpl addressService = new AddressServiceImpl(addressRepository, converter);

        Address savedAddress = addressService.save(address);

        assertNotNull(savedAddress);
        verify(addressRepository, times(1)).save(any(AddressEntity.class));
    }

    @Test
    public void testUpdate() {

        long id = 1;
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(id);
        Address updatedAddress = new Address();
        updatedAddress.setCity("New City");
        when(addressRepository.findAddressEntityById(id)).thenReturn(addressEntity);

        addressService.update(id, updatedAddress);

        assertEquals(updatedAddress.getCity(), addressEntity.getCity());
        verify(addressRepository, times(1)).findAddressEntityById(id);
        verify(addressRepository, times(1)).save(addressEntity);
    }

    @Test
    public void testDelete() {
        long id = 1;
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(id);
        when(addressRepository.findAddressEntityById(id)).thenReturn(addressEntity);

        addressService.delete(id);


        verify(addressRepository, times(1)).findAddressEntityById(id);
        verify(addressRepository, times(1)).deleteById(id);


    }
}

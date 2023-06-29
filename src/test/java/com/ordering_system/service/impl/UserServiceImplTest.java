package com.ordering_system.service.impl;

import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.User;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.converter.Converter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private Converter converter;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_ValidEmail_ReturnsUserDetails() {
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword("password");
        userEntity.setRole(Role.USER);

        when(userRepository.findUserEntityByEmail(email)).thenReturn(userEntity);

        UserDetails userDetails = userService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(userEntity.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(userEntity.getRole().name())));

        verify(userRepository, times(1)).findUserEntityByEmail(email);
    }

    @Test
    void loadUserByUsername_InvalidEmail_ThrowsUsernameNotFoundException() {
        String email = "test@example.com";

        when(userRepository.findUserEntityByEmail(email)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.loadUserByUsername(email));

        verify(userRepository, times(1)).findUserEntityByEmail(email);
    }

    @Test
    void getByEmail_ExistingEmail_ReturnsUser() {
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        User user = new User();
        user.setEmail(email);

        when(userRepository.findUserEntityByEmail(email)).thenReturn(userEntity);
        when(converter.entityToUser(userEntity)).thenReturn(user);

        User result = userService.getByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());

        verify(userRepository, times(1)).findUserEntityByEmail(email);
        verify(converter, times(1)).entityToUser(userEntity);
    }



    @Test
    void update_NonExistingEmail_ThrowsEntityNotFoundException() {
        String email = "test@example.com";
        User user = new User();

        when(userRepository.findUserEntityByEmail(email)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.update(email, user));

        verify(userRepository, times(1)).findUserEntityByEmail(email);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

}





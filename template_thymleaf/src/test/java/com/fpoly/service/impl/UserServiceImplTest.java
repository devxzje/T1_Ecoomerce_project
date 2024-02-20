package com.fpoly.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.fpoly.exception.NotFoundException;
import com.fpoly.model.Users;
import com.fpoly.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_ExistingId_ReturnsUser() throws NotFoundException {
        // Arrange
        Users user = new Users();
        user.setId(1L);
        user.setFirstName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        Users foundUser = userService.findById(1);

        System.out.println(foundUser.toString());

        // Assert
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindById_NonExistingId_ThrowsNotFoundException() {
        // Arrange
        when(userRepository.findById(100)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            userService.findById(100);
        });
    }

    @Test
    public void findByID() throws NotFoundException {
        Optional<Users> userOptional = userRepository.findById(2); // Assuming ID 2 exists
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            System.out.println("User found: " + user.toString());
        } else {
            throw new NotFoundException("User not found with ID 2");
        }
    }

}

package org.softuni.mobilele.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.model.enums.UserRoleEnum;
import org.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailsServiceTest {

  private MobileleUserDetailsService serviceToTest;

  @Mock
  private UserRepository mockUserRepository;

  @BeforeEach
  void setUp() {
    serviceToTest = new MobileleUserDetailsService(
      mockUserRepository
    );
  }

  @Test
  void testUserNotFound() {
    assertThrows(
        UsernameNotFoundException.class,
        () -> serviceToTest.loadUserByUsername("pesho@softuni.bg")
    );
  }

  @Test
  void testUserFoundException() {
    // Arrange
    UserEntity testUserEntity = createTestUser();
    when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
        .thenReturn(Optional.of(testUserEntity));

    // Act
    UserDetails userDetails =
        serviceToTest.loadUserByUsername(testUserEntity.getEmail());

    // Assert
    assertNotNull(userDetails);
    assertEquals(
        testUserEntity.getEmail(),
        userDetails.getUsername(),
        "Username is not mapped to email.");

    assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
    assertEquals(2, userDetails.getAuthorities().size());
    assertTrue(
        containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
        "The user is not admin");
    assertTrue(
        containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
        "The user is not user");
  }

  private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
   return userDetails
        .getAuthorities()
        .stream()
        .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
  }

  private static UserEntity createTestUser() {
    return new UserEntity()
        .setFirstName("firstName")
        .setLastName("lastName")
        .setEmail("pesho@softuni.bg")
        .setActive(false)
        .setPassword("topsecret")
        .setRoles(List.of(
            new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
            new UserRoleEntity().setRole(UserRoleEnum.USER)
        ));
  }
}

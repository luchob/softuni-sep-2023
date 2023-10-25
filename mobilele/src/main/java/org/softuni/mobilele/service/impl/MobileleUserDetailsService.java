package org.softuni.mobilele.service.impl;

import java.util.List;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public MobileleUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
        .map(MobileleUserDetailsService::map)
        .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
  }

  private static UserDetails map(UserEntity userEntity) {
    return User
        .withUsername(userEntity.getEmail())
        .password(userEntity.getPassword())
        .authorities(userEntity.getRoles().stream().map(MobileleUserDetailsService::map).toList())
        .build();
  }

  private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
    return new SimpleGrantedAuthority(
        "ROLE_" + userRoleEntity.getRole().name()
    );
  }
}

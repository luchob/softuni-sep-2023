package org.softuni.mobilele.service.impl;

import java.util.List;
import org.softuni.mobilele.model.entity.UserEntity;
import org.softuni.mobilele.model.entity.UserRoleEntity;
import org.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;

public class ApplicationUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public ApplicationUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return
        userRepository.
            findByEmail(username).
            map(this::map).
            orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {
    return new User(
        userEntity.getEmail(),
        userEntity.getPassword(),
        extractAuthorities(userEntity)
    );
  }

  private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
    return userEntity.
        getRoles().
        stream().
        map(this::mapRole).
        toList();
  }

  private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
    return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
  }
}
package com.films.system.authservicebasic.services;

import com.films.system.authservicebasic.configuration.JwtService;
import com.films.system.authservicebasic.dto.AuthenticationRequest;
import com.films.system.authservicebasic.dto.AuthenticationResponse;
import com.films.system.authservicebasic.dto.RegisterRequest;
import com.films.system.authservicebasic.models.User;
import com.films.system.authservicebasic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    User user =
        User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .enabled(true)
            .build();
    User savedUser = userRepository.save(user);
    String token = jwtService.generateToken(savedUser);
    return AuthenticationResponse.builder().accessToken(token).build();
  }

  public AuthenticationResponse login(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    String token = jwtService.generateToken(user);
    return AuthenticationResponse.builder().accessToken(token).build();
  }
}

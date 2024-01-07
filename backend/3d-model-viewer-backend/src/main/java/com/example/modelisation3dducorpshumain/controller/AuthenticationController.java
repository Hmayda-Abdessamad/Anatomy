package com.example.modelisation3dducorpshumain.controller;


import com.example.modelisation3dducorpshumain.beans.User;
import com.example.modelisation3dducorpshumain.dto.AuthenticationRequest;
import com.example.modelisation3dducorpshumain.dto.AuthenticationResponse;
import com.example.modelisation3dducorpshumain.service.AuthenticationService;
import com.example.modelisation3dducorpshumain.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.example.modelisation3dducorpshumain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {
  private final AuthenticationService service;
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }


  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") int id) {
    return ResponseEntity.ok(service.getUser(id));
  }

  @PostMapping("/AddAdmin")
  public ResponseEntity<AuthenticationResponse> AddAdmin(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.AddAdmin(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}

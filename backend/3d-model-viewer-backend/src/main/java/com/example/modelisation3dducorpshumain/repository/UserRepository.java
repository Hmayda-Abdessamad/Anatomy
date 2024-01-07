package com.example.modelisation3dducorpshumain.repository;

import com.example.modelisation3dducorpshumain.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Optional<User> findById(int id);
  public void deleteUserById(int id);
  public User findUserById(int id);

}

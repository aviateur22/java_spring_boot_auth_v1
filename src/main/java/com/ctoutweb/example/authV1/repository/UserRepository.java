package com.ctoutweb.example.authV1.repository;

import java.util.Optional;

import com.ctoutweb.example.authV1.model.RegisterRequest;
import com.ctoutweb.example.authV1.model.User;

public interface UserRepository {
	Optional<User> findUserByEmail(String email);
	Long saveUser(RegisterRequest request);
}

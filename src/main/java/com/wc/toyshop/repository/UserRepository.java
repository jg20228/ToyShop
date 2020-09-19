package com.wc.toyshop.repository;

import java.util.Optional;

import com.wc.toyshop.model.User;

public interface UserRepository {
	public Optional<User> findByUsername(String username);
	public void save(User user);
}

package com.wc.toyshop.repository;

import com.wc.toyshop.model.User;

public interface UserRepository {
	public User findByUsername(String username);
	public void save(User user);
}

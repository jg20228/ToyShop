package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.model.Product;
import com.wc.toyshop.model.User;

public interface AdminRepository {
	public List<User> findAll();
}

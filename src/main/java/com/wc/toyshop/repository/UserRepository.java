package com.wc.toyshop.repository;

import java.util.Optional;

import com.wc.toyshop.controller.respdto.UpdateRespDto;
import com.wc.toyshop.model.User;

public interface UserRepository {
	public User findByProviderAndProviderId(String provider, String providerId);
	public Optional<User> findByUsername(String username);
	public void save(User user);
	public UpdateRespDto updateForm(int userId);
}

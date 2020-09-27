package com.wc.toyshop.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wc.toyshop.controller.dto.UserUpdateReqDto;
import com.wc.toyshop.controller.respdto.UpdateRespDto;
import com.wc.toyshop.model.User;
import com.wc.toyshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
	public UpdateRespDto 회원수정이동(int userId) {
		UpdateRespDto user = userRepository.updateForm(userId);
		System.out.println(user);
		return user;
	}
	
	public void 회원수정(UserUpdateReqDto dto) {
		Optional<User> user = userRepository.findByUsername(dto.getUsername());
		User userEntity = user.get();
		if(userEntity.getId() == dto.getId()) {
			userEntity.setAddress(dto.getAddress());
			userEntity.setEmail(dto.getEmail());
			userEntity.setGender(dto.getGender());
			userEntity.setName(dto.getName());
			String rawPassword = dto.getPassword();
			String encPassword = bCryptPasswordEncoder.encode(rawPassword);
			if(encPassword != userEntity.getPassword()) {
				userEntity.setPassword(encPassword);
			}
			userEntity.setPhone(dto.getPhone());
			userRepository.update(userEntity);
		}
	}
}

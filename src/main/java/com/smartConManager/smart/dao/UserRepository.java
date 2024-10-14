package com.smartConManager.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartConManager.smart.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

} 

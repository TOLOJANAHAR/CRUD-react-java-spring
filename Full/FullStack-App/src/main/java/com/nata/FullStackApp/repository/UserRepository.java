package com.nata.FullStackApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nata.FullStackApp.model.User;

public interface UserRepository extends JpaRepository<User,String>{

}

package com.nata.FullStackApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nata.FullStackApp.exception.UserNotFoundException;
import com.nata.FullStackApp.model.User;
import com.nata.FullStackApp.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	@GetMapping("/user/{username}")
	User getUserByUsername(@PathVariable String username) {
		return userRepository.findById(username).orElseThrow(()->new UserNotFoundException(username));
	}
	
	@PutMapping("/user/{username}")
	User updateUser(@RequestBody User newUser,@PathVariable String username) {
		return userRepository.findById(username)
				.map(user->{
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setMail(newUser.getMail());
					return userRepository.save(user);
				}).orElseThrow(()->new UserNotFoundException(username));
	}
	@DeleteMapping("/user/{username}")
	String deleteUser(@PathVariable String username) {
		if(!userRepository.existsById(username)) {
			throw new UserNotFoundException(username);
		}
		userRepository.deleteById(username);
		return username + " a est supprimer";
	}
}

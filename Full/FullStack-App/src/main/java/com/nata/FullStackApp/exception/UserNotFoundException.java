package com.nata.FullStackApp.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String username) {
		super(username + " est introuvable");
	}
}

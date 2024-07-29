package com.booklib.booklib.security.service;


import com.booklib.booklib.Entities.User;
import com.booklib.booklib.security.dto.AuthenticatedUserDto;
import com.booklib.booklib.security.dto.RegistrationRequest;
import com.booklib.booklib.security.dto.RegistrationResponse;

public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}

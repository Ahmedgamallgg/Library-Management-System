package com.booklib.booklib.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class RegistrationRequest {

	@NotEmpty(message = "{registration_name_not_empty}")
	public String name;

	@Email(message = "{registration_email_is_not_valid}")
	@NotEmpty(message = "{registration_email_not_empty}")
	public String email;

	@NotEmpty(message = "{registration_username_not_empty}")
	public String username;

	@NotEmpty(message = "{registration_password_not_empty}")
	public String password;

}

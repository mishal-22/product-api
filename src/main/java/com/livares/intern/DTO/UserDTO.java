package com.livares.intern.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UserDTO {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
}

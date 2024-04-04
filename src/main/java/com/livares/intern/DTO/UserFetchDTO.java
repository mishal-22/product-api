package com.livares.intern.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFetchDTO {

	long id;
	private String firstName;
	private String lastName;
	private String username;
}

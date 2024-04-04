package com.livares.intern.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {

	String message;
	int status;
	Object responseObj;
	public CustomResponse(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	
}

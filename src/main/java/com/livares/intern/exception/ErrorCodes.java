package com.livares.intern.exception;

public enum ErrorCodes {

	INTERNAL_SERVER_ERROR(500), NOT_FOUND(404), FORBIDDEN(403), BAD_REQUEST(400), UNAUTHORIZED(401),
	METHOD_NOT_SUPPORTED(405), INVALID_OPERATION(412), CONFLICT(409);

	private int code;

	ErrorCodes(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

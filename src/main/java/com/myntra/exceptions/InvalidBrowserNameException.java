package com.myntra.exceptions;

public class InvalidBrowserNameException extends RuntimeException {
	public String browserName;

	public InvalidBrowserNameException(String browserName) {
		this.browserName = browserName;
	}

	public String getMessage() {
		return this.browserName + "browser is not supported.";
	}
}

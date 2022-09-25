package com.example.payment.model;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInput {

	public StringBuffer getConsoleInput() {
		return consoleInput;
	}

	public void setConsoleInput(StringBuffer consoleInput) {
		this.consoleInput = consoleInput;
	}

	StringBuffer consoleInput;
	
	
}

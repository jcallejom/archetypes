package com.archetype.cqrsev.core.exceptions;

public class AggregateNotFoundException extends RuntimeException {

	public AggregateNotFoundException(String message) {
		super(message);
		
	}

}

package com.archetype.architectural.saga.service;

public class WorkflowException extends RuntimeException {

    public WorkflowException(String message) {
        super(message);
    }

}
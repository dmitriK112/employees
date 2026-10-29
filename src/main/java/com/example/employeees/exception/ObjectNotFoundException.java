package com.example.employeees.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectType, Object id) {
        super(objectType + " with ID " + id + " was not found");
    }
}

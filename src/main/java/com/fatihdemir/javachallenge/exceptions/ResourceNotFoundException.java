package com.fatihdemir.javachallenge.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String field;
    UUID fieldId;

    public ResourceNotFoundException(String resourceName, String field, UUID fieldId) {
        super(String.format("%s not found with %s: %s", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

}

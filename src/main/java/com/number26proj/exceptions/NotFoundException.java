package com.number26proj.exceptions;

import com.number26proj.enums.ErrorRepository;

/**
 * This exception is raised when object is not Found
 * Created by NrapendraKumar on 06-03-2016.
 */
public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException() {
        super();
    }

    public NotFoundException(ErrorRepository errorRepository) {
        super(errorRepository.getDescription());
        this.message = errorRepository.getDescription();
    }

    @Override
    public String toString() {
        return message;
    }
}

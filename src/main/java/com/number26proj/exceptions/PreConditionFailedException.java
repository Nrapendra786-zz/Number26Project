package com.number26proj.exceptions;

import com.number26proj.enums.ErrorRepository;

/**
 * This exception is raised when precondition is failed
 * Created by NrapendraKumar on 06-03-2016.
 */
public class PreConditionFailedException extends RuntimeException {

    private String message;

    public PreConditionFailedException() {
        super();
    }

    public PreConditionFailedException(ErrorRepository errorRepository) {
        super(errorRepository.getDescription());
        this.message = errorRepository.getDescription();
    }

    @Override
    public String toString() {
        return message;
    }
}
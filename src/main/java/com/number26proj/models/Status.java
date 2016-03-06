package com.number26proj.models;

import org.springframework.stereotype.Component;

/**
 * Created by NrapendraKumar on 06-03-2016.
 */
@Component
public class Status {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

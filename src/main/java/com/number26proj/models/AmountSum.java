package com.number26proj.models;

import org.springframework.stereotype.Component;

/**
 * Created by NrapendraKumar on 05-03-2016.
 */
@Component
public class AmountSum {
    private double sum;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

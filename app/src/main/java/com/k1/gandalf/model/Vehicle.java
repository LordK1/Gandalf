package com.k1.gandalf.model;

import javax.inject.Inject;

/**
 * Created by k1 on 3/11/16.
 */
public class Vehicle {

    private final Motor motor;

    @Inject
    public Vehicle(Motor motor) {
        this.motor = motor;
    }
}

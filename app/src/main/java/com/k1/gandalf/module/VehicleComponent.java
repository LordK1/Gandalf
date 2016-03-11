package com.k1.gandalf.module;

import com.k1.gandalf.model.Vehicle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by k1 on 3/11/16.
 */
@Singleton
@Component(modules = VehicleModule.class)
public interface VehicleComponent {

    Vehicle provideVehicle();

}

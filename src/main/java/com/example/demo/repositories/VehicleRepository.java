package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}

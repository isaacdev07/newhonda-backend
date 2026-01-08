package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Vehicle;
import com.example.demo.enums.TypeVehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	List<Vehicle> findByTypeVehicle(TypeVehicle typeVehicle);

}

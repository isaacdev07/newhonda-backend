package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Vehicle;
import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	//listar veiculos de determidados tipos (carro ou moto)
	List<Vehicle> findByTypeVehicle(TypeVehicle typeVehicle);
	
	//listar veiculos de determinados estados (novo ou usado)
	List<Vehicle> findByStateVehicle(StateVehicle stateVehicle);

	//combinar os filtros para buscar dois tipos ao mesmo tempo
	List<Vehicle> findByTypeVehicleAndStateVehicle(TypeVehicle typeVehicle,StateVehicle stateVehicle);
}

package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.repositories.VehicleRepository;

@Service
public class VehicleService {

	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Vehicle createNewVehicle(VehicleDTO vehicleDTO) {
		
		Vehicle vehicle = new Vehicle();
		
		vehicle.setNameVehicle(vehicleDTO.getNameVehicle());
		vehicle.setKmsDriven(vehicleDTO.getKmsDriven());
		vehicle.setStateVehicle(vehicleDTO.getStateVehicle());
		vehicle.setTypeVehicle(vehicleDTO.getTypeVehicle());
		vehicle.setYear(vehicleDTO.getYear());
		vehicle.setVehicleImage(vehicleDTO.getVehicleImage());
		
		
		
		
		return vehicleRepository.save(vehicle);
		
		
		
	}
	
	
}

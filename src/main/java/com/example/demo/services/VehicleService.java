package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.enums.TypeVehicle;
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
	
	public List<Vehicle> getAllVehicles(String typeVehicle){
		
		if(typeVehicle != null && !typeVehicle.isEmpty()) {
			
			try {
				//trasforma o string no enum para o banco poder ler
				TypeVehicle tipoConvertido = TypeVehicle.valueOf(typeVehicle.toUpperCase());
				//chama o enum correto para o banco
				return vehicleRepository.findByTypeVehicle(tipoConvertido);
				//se chega um valor nao existente retorna uma lista vazia
			} catch (IllegalArgumentException e) {
				return Collections.emptyList();
			}
			
		}
		//caso contrario retorna tudo
		return vehicleRepository.findAll();
	}
	
	
}

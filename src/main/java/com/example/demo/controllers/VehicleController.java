package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.services.VehicleService;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/create")
	public ResponseEntity<Vehicle> createNewUser(@RequestBody VehicleDTO vehicleDTO){
		Vehicle newVehicle = vehicleService.createNewVehicle(vehicleDTO);	
		return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
		
	}

}

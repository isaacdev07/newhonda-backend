package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/create")
	public ResponseEntity<Vehicle> createNewUser(@Valid @RequestBody VehicleDTO vehicleDTO){
		Vehicle newVehicle = vehicleService.createNewVehicle(vehicleDTO);	
		return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<Vehicle>> getAllVehicles(@RequestParam(required = false) String typeVehicle,
														@RequestParam(required = false) String stateVehicle,
														@PageableDefault(size = 10, sort = "nameVehicle") Pageable pageable){
		Page<Vehicle> vehicles = vehicleService.getAllVehicles(typeVehicle, stateVehicle, pageable);
		return ResponseEntity.ok(vehicles);
	}
}

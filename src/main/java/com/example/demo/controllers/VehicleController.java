package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.example.demo.dtos.VehicleDTO;
import com.example.demo.dtos.VehicleStatsDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/create")
	public ResponseEntity<Vehicle> createNewUser(@Valid @RequestBody VehicleDTO vehicleDTO) {
		Vehicle newVehicle = vehicleService.createNewVehicle(vehicleDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(newVehicle);

	}

	@GetMapping
	public ResponseEntity<Page<Vehicle>> getAllVehicles(@RequestParam(required = false) String typeVehicle,
			@RequestParam(required = false) String stateVehicle,
			@PageableDefault(size = 10, sort = "nameVehicle") Pageable pageable) {
		Page<Vehicle> vehicles = vehicleService.getAllVehicles(typeVehicle, stateVehicle, pageable);
		return ResponseEntity.ok(vehicles);
	}

	// update de veiculos
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {

		Vehicle updateVehicle = vehicleService.updateVehicle(id, vehicle);

		return ResponseEntity.ok(updateVehicle);

	}
	
	//deletar veiculo
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
		 vehicleService.deleteVehicle(id);
		return ResponseEntity.noContent().build();
	}
	
	//get statistics
	@GetMapping("/stats")
	public ResponseEntity<VehicleStatsDTO> getStatistics(){
		VehicleStatsDTO stats = vehicleService.getVehicleStatistics();
		return ResponseEntity.ok(stats);
	}
	
	//post de vender veiculos
	@PostMapping("/{id}/sold")
	public ResponseEntity<VehicleDTO> markAsSold(@PathVariable Long id,
			@RequestBody VehicleDTO dto){
		Long clientId = dto.getUserId();
		Vehicle soldVehicle = vehicleService.shellVehicle(id, clientId);
		return ResponseEntity.ok(new VehicleDTO(soldVehicle));
	}
	
	//get my-vehicles
	@GetMapping("/my-vehicles/{userId}")
	public ResponseEntity<Page<VehicleDTO>> getMyVehicles(@PathVariable Long userId,
			@PageableDefault(size = 10, sort = "saleDate", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<VehicleDTO> myGarage = vehicleService.getVehiclesByClient(userId, pageable);
		
		return ResponseEntity.ok(myGarage);
		
	}
	
}

package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.entities.Vehicle;
import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;
import com.example.demo.exceptions.BusinessException;
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
	
	public List<Vehicle> getAllVehicles(String typeVehicle, String stateVehicle){
		
		//checa o estado e o tipo
		if(typeVehicle != null && !typeVehicle.isEmpty() && stateVehicle != null && !stateVehicle.isEmpty()) {
			
			try {
				
			
			//mesma logica do tipo e estado
			TypeVehicle tipoConvertido = TypeVehicle.valueOf(typeVehicle.toUpperCase());
			StateVehicle stateEnum = StateVehicle.valueOf(stateVehicle.toUpperCase());
			//retorna os veiculos correspondentes
			return vehicleRepository.findByTypeVehicleAndStateVehicle(tipoConvertido, stateEnum);
			
			//caso nao chegue um enum correto ele acusa o erro
			}catch(IllegalArgumentException e) {
				throw  new BusinessException("Parametros inválidos, verifique se o typeVehicle (CAR/MOTO) e/ou stateVehicle (NEW, USED) estão corretos!");
			}
			
		}
		
		
		
		//checa somente o tipo
		if(typeVehicle != null && !typeVehicle.isEmpty()) {
			
			try {
				//trasforma o string no enum para o banco poder ler
				TypeVehicle tipoConvertido = TypeVehicle.valueOf(typeVehicle.toUpperCase());
				//chama o enum correto para o banco
				return vehicleRepository.findByTypeVehicle(tipoConvertido);
				//se chega um valor errado ele acusa o erro e ajuda o usuario
			} catch (IllegalArgumentException e) {
				throw  new BusinessException("Tipo de veículo inválido! Verifique se está correto. (CAR, MOTO)");
			}
				
		}
		//checa somente o estado
		if(stateVehicle != null && !stateVehicle.isEmpty()) {
			
			try {
				//aqui a lógica é igual ao de cima (typeVehicle)
				StateVehicle stateEnum = StateVehicle.valueOf(stateVehicle.toUpperCase());
				return vehicleRepository.findByStateVehicle(stateEnum);
				//mesma coisa, se nao chegar o enum correto retorna uma lista vazia
			} catch (IllegalArgumentException e) {
				throw  new BusinessException("Estado de veículo inválido! Verifique se está correto. (NEW, USED)");
			}
		}
		//caso contrario retorna tudo
		return vehicleRepository.findAll();
	}
	
	
}

package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.exceptions.ResourceNotFoundException;
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
	
	public Page<Vehicle> getAllVehicles(String typeVehicle, String stateVehicle, Pageable pageable){
		
		//checa o estado e o tipo
		if(typeVehicle != null && !typeVehicle.isEmpty() && stateVehicle != null && !stateVehicle.isEmpty()) {
			
			try {
			//mesma logica do tipo e estado
			TypeVehicle tipoConvertido = TypeVehicle.valueOf(typeVehicle.toUpperCase());
			StateVehicle stateEnum = StateVehicle.valueOf(stateVehicle.toUpperCase());
			//retorna os veiculos correspondentes
			return vehicleRepository.findByTypeVehicleAndStateVehicle(tipoConvertido, stateEnum, pageable);
			
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
				return vehicleRepository.findByTypeVehicle(tipoConvertido, pageable);
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
				return vehicleRepository.findByStateVehicle(stateEnum, pageable);
				//mesma coisa, se nao chegar o enum correto retorna uma lista vazia
			} catch (IllegalArgumentException e) {
				throw  new BusinessException("Estado de veículo inválido! Verifique se está correto. (NEW, USED)");
			}
		}
		//caso contrario retorna tudo
		return vehicleRepository.findAll(pageable);
	}
	
	//atualizar veiculo
	public Vehicle updateVehicle(Long id, Vehicle vehicleData) {
		//verifica se o veiculo existe
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com ID: " + id));
        
        //atualiza o veiculo
		updateData(existingVehicle, vehicleData);
		
		//salva o veiculo atualizado
		return vehicleRepository.save(existingVehicle);
		
	}
	
	//deletar veiculo
	public void deleteVehicle(Long id) {
		//verifica se o veiculo existe pelo id
		if(!vehicleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Veículo não encontrado para deletar com ID: " + id);	
		}
		vehicleRepository.deleteById(id);
	}
	
	//metodo auxiliar para nao perder o veiculo (id)
	private void updateData(Vehicle existing, Vehicle newData) {
        existing.setNameVehicle(newData.getNameVehicle());
        existing.setYear(newData.getYear());
        existing.setKmsDriven(newData.getKmsDriven());
        existing.setTypeVehicle(newData.getTypeVehicle());
        existing.setStateVehicle(newData.getStateVehicle());
        existing.setVehicleImage(newData.getVehicleImage());
    }
	
}

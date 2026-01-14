package com.example.demo.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Vehicle;
import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	//listar veiculos de determidados tipos (carro ou moto)
	Page<Vehicle> findByTypeVehicle(TypeVehicle typeVehicle, Pageable pageable);
	
	//listar veiculos de determinados estados (novo ou usado)
	Page<Vehicle> findByStateVehicle(StateVehicle stateVehicle, Pageable pageable);

	//combinar os filtros para buscar dois tipos ao mesmo tempo
	Page<Vehicle> findByTypeVehicleAndStateVehicle(TypeVehicle typeVehicle,StateVehicle stateVehicle, Pageable pageable);
	
	//connta quantos veiculos tem com aquele tipo especifico
	long countByTypeVehicle(TypeVehicle typeVehicle);
	
	//soma todos os preços de veiculos com o state sold
	//o coalesce serve para retornar - ao inves de null caso nao tenha nenhum
	@Query("SELECT COALESCE(SUM(v.price), 0) FROM Vehicle v WHERE v.stateVehicle = 'SOLD'")
    BigDecimal getTotalRevenue();
}

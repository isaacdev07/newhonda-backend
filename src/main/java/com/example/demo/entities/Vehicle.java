package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "O nome do veículo é obrigatório")
	private String nameVehicle;
	@NotNull(message = "O ano é obrigatório")
    @Min(value = 1886, message = "O ano deve ser superior a 1886")//ano do primeiro carro
	private int year;
	@NotBlank(message = "A quilometragem é obrigatória")
	private String kmsDriven;
	@NotNull(message = "O tipo do veículo é obrigatório")
	@Enumerated(EnumType.STRING)
	private TypeVehicle typeVehicle;
	@NotNull(message = "O estado do veículo é obrigatório")
	@Enumerated(EnumType.STRING)
	private StateVehicle stateVehicle;
	@NotNull(message = "o preço do veiculo é obrigatório")
	private BigDecimal price;
	private LocalDateTime saleDate;
	private String vehicleImage;
	
	
	public Vehicle(Long id, String nameVehicle, int year, String kmsDriven, String vehicleImage, TypeVehicle typeVehicle,
			StateVehicle stateVehicle, BigDecimal price, LocalDateTime saleDate) {
		this.id = id;
		this.nameVehicle = nameVehicle;
		this.year = year;
		this.kmsDriven = kmsDriven;
		this.typeVehicle = typeVehicle;
		this.stateVehicle = stateVehicle;
		this.vehicleImage = vehicleImage;
		this.price = price;
		this.saleDate = saleDate;
	}


	public Vehicle() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public LocalDateTime getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}
	
	public void setVehicleImage(String vehicleImage) {
		this.vehicleImage = vehicleImage;
	}


	public String getVehicleImage() {
		return vehicleImage;
	}


	public String getNameVehicle() {
		return nameVehicle;
	}


	public void setNameVehicle(String nameVehicle) {
		this.nameVehicle = nameVehicle;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getKmsDriven() {
		return kmsDriven;
	}


	public void setKmsDriven(String kmsDriven) {
		this.kmsDriven = kmsDriven;
	}


	public TypeVehicle getTypeVehicle() {
		return typeVehicle;
	}


	public void setTypeVehicle(TypeVehicle typeVehicle) {
		this.typeVehicle = typeVehicle;
	}


	public StateVehicle getStateVehicle() {
		return stateVehicle;
	}


	public void setStateVehicle(StateVehicle stateVehicle) {
		this.stateVehicle = stateVehicle;
	}
	
	
	

}

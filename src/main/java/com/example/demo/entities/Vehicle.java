package com.example.demo.entities;

import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameVehicle;
	private int year;
	private String kmsDriven;
	private TypeVehicle typeVehicle;
	private StateVehicle stateVehicle;
	private String vehicleImage;
	
	
	public Vehicle(Long id, String nameVehicle, int year, String kmsDriven, String vehicleImage, TypeVehicle typeVehicle,
			StateVehicle stateVehicle) {
		this.id = id;
		this.nameVehicle = nameVehicle;
		this.year = year;
		this.kmsDriven = kmsDriven;
		this.typeVehicle = typeVehicle;
		this.stateVehicle = stateVehicle;
		this.vehicleImage = vehicleImage;
	}


	public Vehicle() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

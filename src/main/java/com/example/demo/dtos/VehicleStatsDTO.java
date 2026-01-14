package com.example.demo.dtos;

public class VehicleStatsDTO {
	
	private long totalVehicles;
	private long totalCars;
	private long totalMotos;
	
	
	public VehicleStatsDTO(long totalVehicles, long totalCars, long totalMotos) {
		this.totalVehicles = totalVehicles;
		this.totalCars = totalCars;
		this.totalMotos = totalMotos;
	}


	public long getTotalVehicles() {
		return totalVehicles;
	}


	public void setTotalVehicles(long totalVehicles) {
		this.totalVehicles = totalVehicles;
	}


	public long getTotalCars() {
		return totalCars;
	}


	public void setTotalCars(long totalCars) {
		this.totalCars = totalCars;
	}


	public long getTotalMotos() {
		return totalMotos;
	}


	public void setTotalMotos(long totalMotos) {
		this.totalMotos = totalMotos;
	}
	
	

}

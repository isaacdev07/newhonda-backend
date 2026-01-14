package com.example.demo.dtos;

import java.math.BigDecimal;

public class VehicleStatsDTO {
	
	private long totalVehicles;
	private long totalCars;
	private long totalMotos;
	private BigDecimal totalRevenue;
	
	
	public VehicleStatsDTO(long totalVehicles, long totalCars, long totalMotos, BigDecimal totalRevenue) {
		this.totalVehicles = totalVehicles;
		this.totalCars = totalCars;
		this.totalMotos = totalMotos;
		this.totalRevenue = totalRevenue;
	}


	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}


	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
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

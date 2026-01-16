package com.example.demo.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.entities.Vehicle;
import com.example.demo.enums.StateVehicle;
import com.example.demo.enums.TypeVehicle;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleDTO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ClientDTO client;
	
	//relacionamento
	private Long userId;
	
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
	
	
	public VehicleDTO(Vehicle entity) {
		this.id = entity.getId();
		this.nameVehicle = entity.getNameVehicle();
		this.year = entity.getYear();
		this.kmsDriven = entity.getKmsDriven();
		this.typeVehicle = entity.getTypeVehicle();
		this.stateVehicle = entity.getStateVehicle();
		this.vehicleImage = entity.getVehicleImage();
		this.price = entity.getPrice();
		this.saleDate = entity.getSaleDate();
		if(entity.getClient() != null ) {
			this.client = new ClientDTO(entity.getClient());
		}
	}


	public VehicleDTO() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public ClientDTO getClient() {
		return client;
	}


	public void setClient(ClientDTO client) {
		this.client = client;
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

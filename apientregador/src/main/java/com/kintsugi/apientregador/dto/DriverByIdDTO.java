package com.kintsugi.apientregador.dto;

import com.kintsugi.apientregador.model.Driver;

public class DriverByIdDTO {
	private Integer id;

	private String name;

	private String phoneNumber;

	private String email;

	private Double latitude;
	private Double longitude;

    public DriverByIdDTO(Driver driver){
        this.id = driver.getId();
        this.name = driver.getName();
        this.phoneNumber = driver.getPhoneNumber();
        this.email = driver.getEmail();
        this.latitude = driver.getLatitude();
        this.longitude = driver.getLongitude();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    
}

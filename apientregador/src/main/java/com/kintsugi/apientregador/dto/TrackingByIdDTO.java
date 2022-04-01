package com.kintsugi.apientregador.dto;

import com.kintsugi.apientregador.model.Tracking;

public class TrackingByIdDTO {

    private Integer id;
    private String timestamp;
    private Double latitude;
    private Double longitude;
    private Integer orderId;

    public TrackingByIdDTO(Tracking tracking) {
        this.id = tracking.getId();
        this.timestamp = tracking.getTimestamp();
        this.latitude = tracking.getLatitude();
        this.longitude = tracking.getLongitude();
        this.orderId = tracking.getOrder().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}

package com.kintsugi.apientregador.dto;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kintsugi.apientregador.model.Client;
import com.kintsugi.apientregador.model.Order;
import com.kintsugi.apientregador.model.Tracking;
import com.kintsugi.apientregador.utils.OrderStatusEnum;

public class OrderByIdDTO {

    private Integer orderId;
    private Double value;
    private String timestamp;
    private Integer status;
    private Optional<OrderStatusEnum> statusName;
    private Client client;
    private Integer driverId;
    @JsonIgnoreProperties("order")
    private List<Tracking> trackingList;

    public OrderByIdDTO(Order order) {
        this.orderId = order.getId();
        this.value = order.getValue();
        this.timestamp = order.getTimestamp();
        this.status = order.getStatus();
        this.statusName = OrderStatusEnum.getKey(order.getStatus());
        this.client = order.getClient();
        this.driverId = order.getDriver() != null ? order.getDriver().getId() : null;
        this.trackingList = order.getTrackingList();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Optional<OrderStatusEnum> getStatusName() {
        return statusName;
    }

    public void setStatusName(Optional<OrderStatusEnum> statusName) {
        this.statusName = statusName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public List<Tracking> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<Tracking> trackingList) {
        this.trackingList = trackingList;
    }

}

package com.kintsugi.apientregador.dto;

import java.util.Optional;

import com.kintsugi.apientregador.model.Order;
import com.kintsugi.apientregador.utils.OrderStatusEnum;

public class OrderListagemDTO {

    private Integer orderId;
    private Double value;
    private String timestamp;
    private Integer status;
    private Optional<OrderStatusEnum> statusName;
    private Integer driverId;

    public OrderListagemDTO(Order order) {
        this.orderId = order.getId();
        this.value = order.getValue();
        this.timestamp = order.getTimestamp();
        this.status = order.getStatus();
        this.statusName = OrderStatusEnum.getKey(this.status);

        if (order.getDriver() != null) {
            this.driverId = order.getDriver().getId();
        }

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

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

}

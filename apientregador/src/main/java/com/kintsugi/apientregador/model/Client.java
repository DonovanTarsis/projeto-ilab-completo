package com.kintsugi.apientregador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 200, nullable = false)
	private String adress;

	// @OneToMany(mappedBy="client")
	// @JsonIgnoreProperties("client")
	// private List<Order> ordersList;

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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	// public List<Order> getOrdersList() {
	// return ordersList;
	// }

	// public void setOrdersList(List<Order> ordersList) {
	// this.ordersList = ordersList;
	// }

}

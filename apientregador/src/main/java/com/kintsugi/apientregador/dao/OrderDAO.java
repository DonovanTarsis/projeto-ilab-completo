package com.kintsugi.apientregador.dao;

import java.util.List;

import com.kintsugi.apientregador.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDAO extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT * FROM orders o " +
			"WHERE o.status = :status " +
			"OR o.driver_id = :id AND o.status = 2", nativeQuery = true)
	public List<Order> listOrdersFiltered(Integer status, Integer id);

}

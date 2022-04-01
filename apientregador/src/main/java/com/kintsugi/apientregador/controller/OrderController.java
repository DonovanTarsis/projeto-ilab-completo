package com.kintsugi.apientregador.controller;

import com.kintsugi.apientregador.model.Tracking;
import com.kintsugi.apientregador.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	// listar pedidos em aberto ou em transito pelo id do driver
	@GetMapping("/orders")
	public ResponseEntity<?> listarPedidosEmAberto(@RequestParam("driver_id") Integer idDriver) {
		return orderService.listarPedidosEmAberto(idDriver);
	}

	// iniciar o tracking do pedido
	@PutMapping("/orders/{id}/start-tracking")
	public ResponseEntity<?> startTracking(@PathVariable("id") Integer id,
			@RequestParam("driver_id") Integer driverId) {
		return orderService.startOrderTracking(id, driverId);
	}

	// busca pedido pelo id
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> pegarOrderPeloId(@PathVariable("id") Integer id) {
		return orderService.pegarOrderPeloId(id);
	}

	// Atualiza o status do pedido
	@PutMapping("/orders/{id}/status/{status}")
	public ResponseEntity<?> atualizarStatusPedido(@PathVariable("id") Integer id,
			@PathVariable("status") Integer status) {
		return orderService.atualizarStatusPedido(id, status);
	}

	@PutMapping("/orders/{orderid}/tracking")
	public ResponseEntity<?> criarOrderTracking(@PathVariable("orderid") Integer orderId,
			@RequestBody Tracking tracking) {
		return orderService.criarOrderTracking(orderId, tracking);
	}

}

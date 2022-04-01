package com.kintsugi.apientregador.services;

import java.util.List;

import com.kintsugi.apientregador.dao.DriverDAO;
import com.kintsugi.apientregador.dao.OrderDAO;
import com.kintsugi.apientregador.dao.TrackingDAO;
import com.kintsugi.apientregador.dto.OrderByIdDTO;
import com.kintsugi.apientregador.dto.OrderListagemDTO;
import com.kintsugi.apientregador.dto.TrackingByIdDTO;
import com.kintsugi.apientregador.model.Driver;
import com.kintsugi.apientregador.model.Order;
import com.kintsugi.apientregador.model.Tracking;
import com.kintsugi.apientregador.utils.OrderStatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    DriverDAO driverDAO;
    @Autowired
    TrackingDAO trackingDAO;

    // Lista pedidos em aberto ou transito na pagina de pedidos
    public ResponseEntity<?> listarPedidosEmAberto(Integer idDriver) {
        try {
            Integer status = OrderStatusEnum.EM_ESPERA.getStatus();
            List<Order> list = orderDAO.listOrdersFiltered(status, idDriver);

            return ResponseEntity.status(200).body(list.stream().map(o -> new OrderListagemDTO(o)));

        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }

    // Inicia o tracking do pedido alterando o status para 2 (em transito)
    public ResponseEntity<?> startOrderTracking(Integer id, Integer driverId) {
        try {

            Order order = orderDAO.findById(id).orElse(null);

            if (order == null) {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }

            order.setStatus(OrderStatusEnum.EM_TRANSITO.getStatus());

            Driver driver = driverDAO.findById(driverId).get();

            order.setDriver(driver);

            orderDAO.save(order);

            OrderByIdDTO order2 = new OrderByIdDTO(order);

            return ResponseEntity.status(200).body(order2);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }

    // Busca pedido pelo id
    public ResponseEntity<?> pegarOrderPeloId(Integer id) {
        try {
            Order order = orderDAO.findById(id).orElse(null);

            if (order == null) {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }

            return ResponseEntity.status(200).body(new OrderByIdDTO(order));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }

    // Atualiza o status do pedido
    public ResponseEntity<?> atualizarStatusPedido(Integer id, Integer status) {
        try {
            Order order = orderDAO.findById(id).orElse(null);

            if (order == null) {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }

            if (status < 1 || status > OrderStatusEnum.values().length) {
                return ResponseEntity.status(400).body("Status inválido");
            }

            if (status == OrderStatusEnum.EM_ESPERA.getStatus()) {
                order.setDriver(null);
            }

            order.setStatus(status);

            orderDAO.save(order);

            return ResponseEntity.status(201).body(new OrderByIdDTO(order));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }

    // Cria um tracking para um order
    public ResponseEntity<?> criarOrderTracking(Integer orderId, Tracking tracking) {
        try {
            Order order = orderDAO.findById(orderId).orElse(null);

            if (order == null) {
                return ResponseEntity.status(404).body("Pedido não encontrado");
            }

            Tracking newTracking = new Tracking();
            newTracking.setTimestamp(tracking.getTimestamp());
            newTracking.setLatitude(tracking.getLatitude());
            newTracking.setLongitude(tracking.getLongitude());
            newTracking.setOrder(order);

            TrackingByIdDTO trackingByIdDTO = new TrackingByIdDTO(trackingDAO.save(newTracking));

            return ResponseEntity.status(200).body(trackingByIdDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }
}

package com.kintsugi.apientregador.controller;

import com.kintsugi.apientregador.model.Driver;
import com.kintsugi.apientregador.services.DriverServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    @Autowired
    private DriverServiceImpl driverService;

    @PutMapping("/drivers/create")
    public ResponseEntity<?> criarDriverPeloId(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<?> pegarDriverPeloId(@PathVariable("id") Integer id) {
        return driverService.pegarDriverPeloId(id);
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<?> atualizarDriverPeloId(@PathVariable("id") Integer id, @RequestBody Driver driverBody) {
        return driverService.atualizarDriverPeloId(id, driverBody);
    }

}

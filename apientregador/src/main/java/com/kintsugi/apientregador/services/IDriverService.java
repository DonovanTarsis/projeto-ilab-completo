package com.kintsugi.apientregador.services;

import com.kintsugi.apientregador.dto.DriverLoginDTO;
import com.kintsugi.apientregador.model.Driver;
import com.kintsugi.apientregador.security.Token;

import org.springframework.http.ResponseEntity;

public interface IDriverService {
    public Token gerarTokenDeDriverLogado(DriverLoginDTO dadosLogin);

    public ResponseEntity<?> createDriver(Driver driver);
}
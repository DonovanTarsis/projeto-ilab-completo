package com.kintsugi.apientregador.services;

import com.kintsugi.apientregador.dao.DriverDAO;
import com.kintsugi.apientregador.dto.DriverByIdDTO;
import com.kintsugi.apientregador.dto.DriverDTO;
import com.kintsugi.apientregador.dto.DriverLoginDTO;
import com.kintsugi.apientregador.model.Driver;
import com.kintsugi.apientregador.security.ApiCrypto;
import com.kintsugi.apientregador.security.Token;
import com.kintsugi.apientregador.security.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DriverServiceImpl implements IDriverService {

    @Autowired
    private DriverDAO dao;

    @Override
    public Token gerarTokenDeDriverLogado(DriverLoginDTO dadosLogin) {
        Driver driver = dao.findByEmailEquals(dadosLogin.getEmail());
        if (driver != null) {
            boolean passwordIsValid = ApiCrypto.comparePassword( dadosLogin.getPassword(), driver.getPassword());

            if (passwordIsValid) {
                return new Token(TokenUtil.createToken(driver));
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<?> createDriver(Driver driver) {
        driver.setPassword(ApiCrypto.encryptToSave(driver.getPassword()));
        Driver driverSave = dao.save(driver);
        DriverDTO driverReturn = new DriverDTO(driverSave.getId(), driverSave.getName(), driverSave.getEmail());
        return ResponseEntity.status(201).body(driverReturn);

    }

    public ResponseEntity<?> pegarDriverPeloId(Integer id) {
        try {
            Driver driver = dao.findById(id).orElse(null);
            if (driver == null) {
                return ResponseEntity.status(404).body("driver não encontrado");

            }
            DriverByIdDTO driverByIdDTO = new DriverByIdDTO(driver);
            return ResponseEntity.status(200).body(driverByIdDTO);

        } catch (RuntimeException e) {

            return ResponseEntity.status(500).body("Erro no servidor");
        }

    }

    public ResponseEntity<?> atualizarDriverPeloId(Integer id, Driver driverBody) {
        try {
            Driver driver = dao.findById(id).orElse(null);
            if (driver == null) {
                return ResponseEntity.status(404).body("Driver não encontrado");
            }

            Driver buscaEmail = dao.findByEmailEquals(driverBody.getEmail());
            if (buscaEmail != null && buscaEmail.getId() != driver.getId()) {
                return ResponseEntity.status(400).body("Email já existente");
            }
            if (driverBody.getEmail() != null) {
                driver.setEmail(driverBody.getEmail());
            }

            if (driverBody.getName() != null) {
                driver.setName(driverBody.getName());
            }
            if (driverBody.getPassword() != null) {
                driver.setPassword(driverBody.getPassword());
            }
            if (driverBody.getLatitude() != null) {
                driver.setLatitude(driverBody.getLatitude());
            }
            if (driverBody.getLongitude() != null) {
                driver.setLongitude(driverBody.getLongitude());
            }
            if (driverBody.getPhoneNumber() != null) {
                driver.setPhoneNumber(driverBody.getPhoneNumber());
            }

            dao.save(driver);

            return ResponseEntity.status(200).body("Driver atualizado com sucesso");

        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erro no servidor");
        }
    }

}

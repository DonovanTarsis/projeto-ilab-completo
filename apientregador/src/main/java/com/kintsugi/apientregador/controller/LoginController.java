package com.kintsugi.apientregador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kintsugi.apientregador.dto.DriverLoginDTO;
import com.kintsugi.apientregador.security.Token;
import com.kintsugi.apientregador.services.IDriverService;

@RestController
@CrossOrigin("*")
public class LoginController {
    

	
	@Autowired
	private IDriverService service;
	
	@PostMapping("/login")
	public ResponseEntity<Token> realizarLogin(@RequestBody DriverLoginDTO dadosLogin ){
		Token token = service.gerarTokenDeDriverLogado(dadosLogin);
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(401).build();
	}
	

}

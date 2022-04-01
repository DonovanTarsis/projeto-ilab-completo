package com.kintsugi.apientregador.dao;

import com.kintsugi.apientregador.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {

}

package com.kintsugi.apientregador.dao;


import com.kintsugi.apientregador.model.Driver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDAO extends JpaRepository<Driver, Integer> {

    public Driver findByEmailEquals(String email);
}

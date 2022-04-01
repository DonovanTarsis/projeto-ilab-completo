package com.kintsugi.apientregador.dao;

import com.kintsugi.apientregador.model.Tracking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingDAO extends JpaRepository<Tracking, Integer> {

}

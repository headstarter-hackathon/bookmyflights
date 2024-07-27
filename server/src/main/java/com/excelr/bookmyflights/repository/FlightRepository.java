package com.excelr.bookmyflights.repository;

import com.excelr.bookmyflights.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, String> {
    List<Flight> findBySourceAndDestination(String source, String destination);
}
package com.excelr.bookmyflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.bookmyflights.service.FlightService;
import com.excelr.bookmyflights.exceptions.ResourceNotFoundException;
import com.excelr.bookmyflights.model.Flight;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	FlightService service;

	@GetMapping("/getFlights")
	public List<Flight> getFlights() {
		return service.getFlights();
	}

	@GetMapping("/getFlight/{id}")
	public Flight getFlightById(@PathVariable String id) {
		Flight flight = service.getFlightById(id);
		if (flight == null) {
			throw new ResourceNotFoundException("Flight not found");
		}
		return flight;
	}

	@PostMapping("/saveFlight")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Flight saveFlight(@RequestBody Flight fl) {
		return service.save(fl);
	}

	@PutMapping("/decrementSeats/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	public Flight decrementSeats(@PathVariable String id) {
		Flight flight = service.decrementSeats(id);
		if (flight == null) {
			throw new ResourceNotFoundException("Flight not found");
		}
		return flight;
	}

	@PutMapping("/incrementSeats/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
	public Flight incrementSeats(@PathVariable String id) {
		Flight flight = service.incrementSeats(id);
		if (flight == null) {
			throw new ResourceNotFoundException("Flight not found");
		}
		return flight;
	}

	@PutMapping("/updateFlight")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Flight updateFlight(@RequestBody Flight fl) {
		Flight flight = service.save(fl);
		if (flight == null) {
			throw new ResourceNotFoundException("Flight not found");
		}
		return flight;
	}

	@DeleteMapping("/deleteFlight/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Flight deleteFlight(@PathVariable String id) {
		Flight fl = service.delete(id);
		if (fl == null) {
			throw new ResourceNotFoundException("Flight not found");
		}
		return fl;
	}

	@GetMapping("/getFlightsBySourceAndDestination/{source}/{destination}")
	public List<Flight> getFlightsSourceAndDestination(@PathVariable String source, @PathVariable String destination) {
		return service.findBySourceAndDestination(source, destination);
	}
}

package com.excelr.bookmyflights.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.bookmyflights.repository.FlightRepository;
import com.excelr.bookmyflights.model.Flight;

@Service
public class FlightService {
	@Autowired
	FlightRepository repo;

	public Flight save(Flight fl) {
		repo.save(fl);
		return fl;
	}

	public Flight getFlightById(String id) {
		Optional<Flight> flightOpt = repo.findById(id);
		if (flightOpt.isPresent()) {
			return flightOpt.get();
		} else {
			return null;
		}
	}

	public List<Flight> getFlights() {
		return repo.findAll();
	}

	public Flight delete(String id) {
		Optional<Flight> flightOpt = repo.findById(id);
		if (flightOpt.isPresent()) {
			Flight flightToDelete = flightOpt.get();
			repo.deleteById(id);
			return flightToDelete;
		} else {
			return null;
		}
	}

	public Flight decrementSeats(String id) {
		Optional<Flight> flightOpt = repo.findById(id);
		if (flightOpt.isPresent()) {
			Flight flight = flightOpt.get();
			if (flight.getSeatsAvailable() > 0) {
				flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
				repo.save(flight);
				return flight;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Flight incrementSeats(String id) {
		Optional<Flight> flightOpt = repo.findById(id);
		if (flightOpt.isPresent()) {
			Flight flight = flightOpt.get();
			if (flight.getSeatsAvailable() > 0) {
				flight.setSeatsAvailable(flight.getSeatsAvailable() + 1);
				repo.save(flight);
				return flight;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Flight update(Flight f1) {
		Optional<Flight> flightOpt = repo.findById(f1.getFlightId());
		if (flightOpt.isPresent()) {
			Flight existingFlight = flightOpt.get();

			if (f1.getAirlines() != null) {
				existingFlight.setAirlines(f1.getAirlines());
			}
			if (f1.getFlightNumbers() != null) {
				existingFlight.setFlightNumbers(f1.getFlightNumbers());
			}
			if (f1.getFlightDurations() != null) {
				existingFlight.setFlightDurations(f1.getFlightDurations());
			}
			if (f1.getSource() != null) {
				existingFlight.setSource(f1.getSource());
			}
			if (f1.getDestination() != null) {
				existingFlight.setDestination(f1.getDestination());
			}
			if (f1.getDepartureTime() != null) {
				existingFlight.setDepartureTime(f1.getDepartureTime());
			}
			if (f1.getArrivalTime() != null) {
				existingFlight.setArrivalTime(f1.getArrivalTime());
			}
			if (f1.getSeatsAvailable() != 0) {
				existingFlight.setSeatsAvailable(f1.getSeatsAvailable());
			}
			if (f1.getLayovers() != null) {
				existingFlight.setLayovers(f1.getLayovers());
			}
			if (f1.getLayoverDurations() != null) {
				existingFlight.setLayoverDurations(f1.getLayoverDurations());
			}

			repo.save(existingFlight);
			return repo.findById(f1.getFlightId()).orElse(null);
		} else {
			return null;
		}
	}

	public List<Flight> findBySourceAndDestination(String source, String destination) {
		return repo.findBySourceAndDestination(source, destination);
	}

}

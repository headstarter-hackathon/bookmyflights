package com.excelr.bookmyflights.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.bookmyflights.repository.TicketRepository;
import com.excelr.bookmyflights.model.Ticket;

@Service
public class TicketService {
	@Autowired
	TicketRepository repo;
	
	public Ticket saveTicket(Ticket t1) {
		repo.save(t1);
		return t1;
	}
	public Ticket deleteTicket(String id) {
		Optional<Ticket> ticketToDelete = repo.findById(id);	
		if(ticketToDelete.isPresent()) {
			Ticket tk = ticketToDelete.get();
			repo.deleteById(id);
			return tk;
		}
		else {
			return null;
		}
	}
	public List<Ticket> getTickets(){
		return repo.findAll();
	}
	public Ticket getTicketById(String id) {
		Optional<Ticket> tk = repo.findById(id);
		if(tk.isPresent()) {
			return tk.get();
		}
		else {
			return null;
		}
	}

}

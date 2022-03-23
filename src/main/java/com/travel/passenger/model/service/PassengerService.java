package com.travel.passenger.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.passenger.model.model.Passenger;
import com.travel.passenger.model.passengerRepository.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	PassengerRepository passengerRepository;

	public Passenger getPassengerById(int ticketId) {
		return passengerRepository.findById(ticketId).get();
	}

	public void saveOrUpdate(Passenger passenger) {
		passengerRepository.save(passenger);
	}

	public void delete(int ticketId) {
		passengerRepository.deleteById(ticketId);
	}

	// updating a record
	public void update(Passenger passenger, int ticketId) {
		passengerRepository.save(passenger);
	}
	
	public List<Passenger> getAllPassengers() {
		List<Passenger> passengerList =(List<Passenger>) passengerRepository.findAll();
		return passengerList;
	}

}

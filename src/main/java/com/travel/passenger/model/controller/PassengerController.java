package com.travel.passenger.model.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.passenger.model.model.Passenger;
import com.travel.passenger.model.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	PassengerService passengerService;

	@GetMapping(value = "/getPassengerInfo/{ticket_Id}", produces = { "application/json" })
	public ResponseEntity<String> getPassenger(@PathVariable("ticket_Id") int ticket_Id) {
		try {
			return new ResponseEntity(passengerService.getPassengerById(ticket_Id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/savePassengerInfo")
	public void savePassenger(@RequestBody Passenger passenger) {
		passengerService.saveOrUpdate(passenger);
	}

	@PutMapping(value = "/updatePassengerInfo/{ticket_id}")
	public ResponseEntity updatePassenger(@PathVariable("ticket_id") int ticket_id, @RequestBody Passenger passenger) {

		Passenger passengerResponse;
		try {
			passengerResponse = passengerService.getPassengerById(ticket_id);
		} catch (Exception e) {
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		}
		if (passengerResponse != null) {
			passengerResponse.setAge(passenger.getAge());
			passengerResponse.setDestination(passenger.getDestination());
			passengerResponse.setDoj(passenger.getDoj());
			passengerResponse.setFirst_Name(passenger.getFirst_Name());
			passengerResponse.setLast_Name(passenger.getLast_Name());
			passengerResponse.setSource(passenger.getSource());
			passengerResponse.setTicket_Id(passenger.getTicket_Id());

			savePassenger(passengerResponse);
		}
		return new ResponseEntity(passengerService.getPassengerById(passengerResponse.getTicket_Id()), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deletePassenger/{ticket_id}")
	public ResponseEntity<String> deletePassengerbyId(@PathVariable("ticket_id") int ticket_id) {
		try {
			passengerService.delete(ticket_id);
		} catch (Exception e) {
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity("Passenger details deleted", HttpStatus.OK);
	}

	@GetMapping(value = "/getAllTickets")
	public List<Passenger> getAllTickets() {
		return passengerService.getAllPassengers();
	}

	@GetMapping(value = "/getAllPassengersSortedByDOJ")
	public List<Passenger> getAllPassengersSortedByDOJ() {
		List<Passenger> sortedList = passengerService.getAllPassengers();
		Collections.sort(sortedList);
		return sortedList;
	}

	@GetMapping(value = "/getPassengerListByFlightNo/{flightNo}")
	public ResponseEntity<String> getPassengerListByFlightNo(@PathVariable("flightNo") long flightNo) {
		if (flightNo < 0)
			return new ResponseEntity("Invalid FlightNo", HttpStatus.NOT_FOUND);

		List<Passenger> passengerList = passengerService.getPassengerListByFlightNo(flightNo);
		List<Passenger> sortedPassengerList = passengerList.stream()
				.sorted(Comparator.comparing(Passenger::getFirst_Name)).collect(Collectors.toList());
		return new ResponseEntity(sortedPassengerList, HttpStatus.NOT_FOUND);
	}
}

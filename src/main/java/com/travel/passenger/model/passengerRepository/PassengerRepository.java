package com.travel.passenger.model.passengerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.travel.passenger.model.model.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
	
	@Query(value="select * from passenger e where e.flight_no = ?1", nativeQuery = true)
	List<Passenger> findAllPassengerByFlightNo(long flightNo);
}

package com.travel.passenger.model.passengerRepository;
import org.springframework.data.repository.CrudRepository;

import com.travel.passenger.model.model.Passenger;  

public interface PassengerRepository extends CrudRepository<Passenger,Integer>{

}

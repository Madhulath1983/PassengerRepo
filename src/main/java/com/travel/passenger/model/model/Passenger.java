package com.travel.passenger.model.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
@Data
public class Passenger implements Comparable<Passenger> {

	@Id
	@JsonProperty()
	public int ticket_Id;
	@JsonProperty()
	public String first_Name;
	@JsonProperty()
	public String last_Name;
	@JsonProperty()
	public int age;
	@JsonProperty()
	public String source;
	@JsonProperty()
	public String destination;
	@JsonProperty()
	public Date doj;
	@JsonProperty()
	public long flightNo;

	public int getTicket_Id() {
		return ticket_Id;
	}

	public void setTicket_Id(int ticket_Id) {
		this.ticket_Id = ticket_Id;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public long getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(long flightNo) {
		this.flightNo = flightNo;
	}

	@Override
	public int compareTo(Passenger passenger) {
		if (getDoj() == null || passenger.getDoj() == null) {
			return 0;
		}
		return getDoj().compareTo(passenger.getDoj());
	}

}

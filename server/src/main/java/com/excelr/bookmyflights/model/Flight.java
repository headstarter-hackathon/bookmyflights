package com.excelr.bookmyflights.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "flights")
public class Flight {
    @Id
    private String flightId;
    private String [] airlines;
    private String [] flightNumbers;
    private String [] flightDurations;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int seatsAvailable;
    private String [] layovers;
    private String [] layoverDurations;
    private String [] classes;
    private double [] prices;
}
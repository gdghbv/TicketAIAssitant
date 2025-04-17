package org.example.dickseek.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class Ticket {
    private String ticketId;
    private String aircraftId;
    private String departure;
    private String destination;
    private int price;
    private Time departureTime;
    private Time arrivalTime;
    private int firstSeat;
    private int secondSeat;
    private int thirdSeat;
    private Date departureDate;
}

package com.example.reservationservice.exceptions;

public class ReservationCreationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9028355906362889569L;

	public ReservationCreationException(String message) {
        super(message);
    }
}

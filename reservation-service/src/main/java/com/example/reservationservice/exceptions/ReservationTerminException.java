package com.example.reservationservice.exceptions;

public class ReservationTerminException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8293219051685451475L;

	public ReservationTerminException(String message) {
        super(message);
    }
}

package com.example.reservationservice.exceptions;

public class AccommodationUnavailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5834533756556192603L;

	public AccommodationUnavailableException(String message) {
        super(message);
    }
}

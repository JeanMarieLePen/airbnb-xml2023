package com.example.reservationservice.exceptions;

public class PriceCalculationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 692773833694771524L;

	public PriceCalculationException(String message) {
        super(message);
    }
}

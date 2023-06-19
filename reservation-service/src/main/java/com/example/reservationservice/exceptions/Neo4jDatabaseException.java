package com.example.reservationservice.exceptions;

public class Neo4jDatabaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335743368363960393L;

	public Neo4jDatabaseException(String message) {
        super(message);
    }
}

package com.example.mockitoTutorials.exception;

public class InsufficientStockException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientStockException(int existingItems, int requestedItems) {
		super("There are " + existingItems + " items of this product in stock but you request " + requestedItems
				+ " items!");
	}
}

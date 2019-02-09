package com.example.mockitoTutorials.controller.request;

public class AddProductItemsRequest {
	private Long productId;
	private int items;

	public AddProductItemsRequest() {
		super();
	}

	public AddProductItemsRequest(Long productId, int items) {
		super();
		this.productId = productId;
		this.items = items;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

}

package com.example.mockitoTutorials.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Long productId;
    private int items;

    public Purchase(){

    }

    public Purchase(Long customerId, Long productId, int items) {
        this.customerId = customerId;
        this.productId = productId;
        this.items = items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public Long getId() {

        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

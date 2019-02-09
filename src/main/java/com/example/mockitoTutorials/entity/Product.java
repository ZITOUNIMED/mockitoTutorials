package com.example.mockitoTutorials.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String ref;
    private double unitPrice;
    private int items;

    public Product(){

    }

    public Product(String ref, double unitPrice, int items) {
        this.ref = ref;
        this.unitPrice = unitPrice;
        this.items = items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public Long getId() {

        return id;
    }

    public String getRef() {
        return ref;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getItems() {
        return items;
    }
}

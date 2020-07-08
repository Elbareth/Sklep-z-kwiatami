package com.example.sklepZKwiatami.dto;

import java.util.Arrays;
import java.util.Objects;

public class FlowerDTO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Byte[] barcode;
    private Integer quantity;

    public FlowerDTO(Integer id, String name, String description, Float price, Byte[] barcode, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public FlowerDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Byte[] getBarcode() {
        return barcode;
    }

    public void setBarcode(Byte[] barcode) {
        this.barcode = barcode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowerDTO)) return false;
        FlowerDTO flowerDTO = (FlowerDTO) o;
        return getId().equals(flowerDTO.getId()) &&
                getName().equals(flowerDTO.getName()) &&
                getDescription().equals(flowerDTO.getDescription()) &&
                getPrice().equals(flowerDTO.getPrice()) &&
                Arrays.equals(getBarcode(), flowerDTO.getBarcode()) &&
                getQuantity().equals(flowerDTO.getQuantity());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getDescription(), getPrice(), getQuantity());
        result = 31 * result + Arrays.hashCode(getBarcode());
        return result;
    }

    @Override
    public String toString() {
        return "FlowerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", barcode=" + Arrays.toString(barcode) +
                ", quantity=" + quantity +
                '}';
    }
}

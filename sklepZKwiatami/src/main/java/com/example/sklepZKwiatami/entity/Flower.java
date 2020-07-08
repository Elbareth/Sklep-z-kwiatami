package com.example.sklepZKwiatami.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "d_flower")
public class Flower {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Float price;
    @NotNull
    private Byte[] barcode;
    @NotNull
    private Integer quantity;

    public Flower(Integer id, String name, String description, Float price, Byte[] barcode, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public Flower(String name, String description, Float price, Byte[] barcode, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public Flower() {
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
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return getId().equals(flower.getId()) &&
                getName().equals(flower.getName()) &&
                getDescription().equals(flower.getDescription()) &&
                getPrice().equals(flower.getPrice()) &&
                Arrays.equals(getBarcode(), flower.getBarcode()) &&
                getQuantity().equals(flower.getQuantity());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getDescription(), getPrice(), getQuantity());
        result = 31 * result + Arrays.hashCode(getBarcode());
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", barcode=" + Arrays.toString(barcode) +
                ", quantity=" + quantity +
                '}';
    }
}

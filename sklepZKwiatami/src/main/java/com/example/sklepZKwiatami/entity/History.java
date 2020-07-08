package com.example.sklepZKwiatami.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "d_history")
public class History {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;
    @NotNull
    @ManyToOne
    @JoinColumn(name="flower", referencedColumnName = "id")
    private Flower flower;
    @NotNull
    private Integer quantity;
    @NotNull
    private LocalDateTime localDateTime;

    public History(Integer id, User user, Flower flower, Integer quantity, LocalDateTime localDateTime) {
        this.id = id;
        this.user = user;
        this.flower = flower;
        this.quantity = quantity;
        this.localDateTime = localDateTime;
    }

    public History(User user, Flower flower, Integer quantity, LocalDateTime localDateTime) {
        this.user = user;
        this.flower = flower;
        this.quantity = quantity;
        this.localDateTime = localDateTime;
    }

    public History() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof History)) return false;
        History history = (History) o;
        return getId().equals(history.getId()) &&
                getUser().equals(history.getUser()) &&
                getFlower().equals(history.getFlower()) &&
                getQuantity().equals(history.getQuantity()) &&
                getLocalDateTime().equals(history.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getFlower(), getQuantity(), getLocalDateTime());
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user=" + user +
                ", flower=" + flower +
                ", quantity=" + quantity +
                ", localDateTime=" + localDateTime +
                '}';
    }
}


package com.example.sklepZKwiatami.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class HistoryDTO {
    private Integer id;
    private Integer user;
    private Integer flower;
    private Integer quantity;
    private LocalDateTime localDateTime;

    public HistoryDTO(Integer id, Integer user, Integer flower, Integer quantity, LocalDateTime localDateTime) {
        this.id = id;
        this.user = user;
        this.flower = flower;
        this.quantity = quantity;
        this.localDateTime = localDateTime;
    }

    public HistoryDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getFlower() {
        return flower;
    }

    public void setFlower(Integer flower) {
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
        if (!(o instanceof HistoryDTO)) return false;
        HistoryDTO that = (HistoryDTO) o;
        return getId().equals(that.getId()) &&
                getUser().equals(that.getUser()) &&
                getFlower().equals(that.getFlower()) &&
                getQuantity().equals(that.getQuantity()) &&
                getLocalDateTime().equals(that.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getFlower(), getQuantity(), getLocalDateTime());
    }

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "id=" + id +
                ", user=" + user +
                ", flower=" + flower +
                ", quantity=" + quantity +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
